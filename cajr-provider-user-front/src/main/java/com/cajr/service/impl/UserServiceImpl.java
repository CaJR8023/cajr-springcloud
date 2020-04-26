package com.cajr.service.impl;

import com.cajr.mapper.UserMapper;
import com.cajr.service.IModuleService;
import com.cajr.service.UserPrefService;
import com.cajr.service.UserService;
import com.cajr.util.CustomHashMap;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.Module;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.cajr.vo.user.UserPref;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/26 2:06 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private IModuleService iModuleService;

    @Autowired
    private UserPrefService userPrefService;

    @Value("${news.recommend.activeDays}")
    private int activeDays;

    @Override
    public Optional<User> getOneUser(int id) {
        return Optional.of(this.userMapper.selectByPrimaryKey(id));
    }

    @Override
    public UserOther getOneUserOther(int id) {
        User user = this.userMapper.selectByPrimaryKey(id);
        UserOther userOther = new UserOther();
        if (user!= null){
            userOther.setId(user.getId());
            userOther.setUsername(user.getUsername());
            if (user.getUserInfo() != null){
                userOther.setAvatar(user.getUserInfo().getAvatar());
                userOther.setSignature(user.getUserInfo().getSignature());
            }
        }
        return userOther;
    }

    @Override
    public User getUser(int id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByTel(String tel) {
        return this.userMapper.selectByTel(tel);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userMapper.selectByUserName(userName);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public Optional<Integer> add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        this.userMapper.insertSelective(user);
        //同时初始化用户喜好关键词
        UserPref userPref = new UserPref();
        userPref.setUserId(user.getId());
        userPref.setPrefList(getDefaultUserPref());
        userPref.setStatus(1);
        userPref.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userPref.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userPrefService.add(userPref);


        return Optional.of(user.getId());
    }

    @Override
    public Integer addNewsInit(User user) {
        user.setTel(" ");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userMapper.insertSelective(user);
        return user.getId();
    }

    private String getDefaultUserPref() {
        List<Module> modules = this.iModuleService.getAllModule();
        if (modules.isEmpty()){
            return "{}";
        }
        StringBuilder userPrefList = new StringBuilder("{");
        for (Module module : modules) {
            userPrefList.append("\"").append(module.getId()).append("\":").append("{}").append(",");
        }

        return userPrefList.substring(0,userPrefList.length()-1) + "}";
    }

    @Override
    public Optional<Integer> update(User user) {

        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return Optional.of(this.userMapper.updateByPrimaryKey(user));
    }

    @Override
    public Optional<Integer> deleteByStatus(int id) {
        return Optional.of(this.userMapper.deleteByPrimaryKey(id));
    }

    @Override
    public Optional<Integer> checkIsExistByTel(String tel) {
        return Optional.of(this.userMapper.checkIsExistsByTel(tel));
    }

    @Override
    public Optional<Integer> checkIsExistByUserName(String userName) {
        return Optional.of(this.userMapper.checkIsExistsByUserName(userName));
    }

    @Override
    public Optional<Integer> checkIsExistById(int id) {
        return Optional.of(this.userMapper.checkIsExistsById(id));
    }

    @Override
    public List<Integer> findAllUserId() {
        return this.userMapper.findAllUserId();
    }

    @Override
    public List<User> findSection(List<Integer> userIds) {
        return null;
    }

    @Override
    public List<Integer> findAllActiveUserId() {
        List<User> users = this.userMapper.findAll();
        if (users.isEmpty()){
            return null;
        }
        List<Integer> activeUserIds = new ArrayList<>();
        users.forEach(user -> {
            if (user.getUserLog().getLatestLoginTime().after(TimeUtil.getInRecTimestamp(activeDays))){
                activeUserIds.add(user.getId());
            }
        });
        return activeUserIds;
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(this.userMapper.findAll());
    }
}

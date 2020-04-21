package com.cajr.mapper;

import com.cajr.vo.user.User;

import java.util.List;

/**
 * @author CAJR
 * @date 2019/11/25 7:22 下午
 */
public interface UserMapper {
    int deleteByPrimaryKey(int id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);

    User selectByTel(String tel);

    User selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkIsExistsByTel(String tel);

    int checkIsExistsByUserName(String userName);

    int checkIsExistsById(int id);

    List<Integer> findAllUserId();

    List<User> findAll();

    List<User> findSection(List<Integer> userIds);
}
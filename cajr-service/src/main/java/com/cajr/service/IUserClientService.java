package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IUserClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.SearchPage;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.cajr.vo.user.UserPref;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author CAJR
 * @date 2019/11/26 6:09 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
fallbackFactory = IUserClientServiceFallBackFactory.class)
public interface IUserClientService {
    @GetMapping("/user/{id}")
    public Result getOneUser(@PathVariable("id") int id);

    @GetMapping("/user/by_name")
    public Result getOneUserByTel(@RequestParam("tel") String tel);

    @GetMapping("/user/one")
    public User getUser(@RequestParam("userId") int userId);

    @PostMapping("/user/")
    public Result addOneUser(@RequestBody User user);

    @PostMapping("/user/news_init")
    public Result addOneUserNewsInit(@RequestBody User user);

    @GetMapping("/user/page")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/user/all_user_id")
    public List<Integer> findAllUserId();

    @GetMapping("/user/active")
    public List<Integer> findActiveUserId();

    @GetMapping("/user/user_other")
    public UserOther getOneUserOther(@RequestParam("userId") int userId);

    @GetMapping("/user/section")
    public List<User> findSectionUserId(@RequestParam("userIds") List<Integer> userIds);

    @PostMapping("/user_pref/")
    public Result addOne(@RequestBody UserPref userPref);

    @PutMapping("/user_pref/")
    public Result updateUserPref(@RequestBody UserPref userPref);

    @GetMapping("/user/search")
    public SearchPage searchUsers(@RequestParam("keyWord") String keyWord,
                                  @RequestParam(value = "page",defaultValue = "1") int page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);
}

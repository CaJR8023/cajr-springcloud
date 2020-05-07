package com.cajr.mapper;

import com.cajr.vo.user.User;

/**
 * @author CAJR
 * @date 2020/4/23 6:51 下午
 */
public interface UserInitMapper {

    int insertNewsUser(User record);

    int checkIsExistsByUserName(String userName);

    int getUserIdByUserName(String userName);
}

package com.cajr.mapper;

import com.cajr.vo.user.User;

/**
 * @author CAJR
 * @date 2019/11/25 7:22 下午
 */
public interface UserMapper {
    int deleteByPrimaryKey(int id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkIsExistsByTel(String tel);

    int checkIsExistsById(int id);
}
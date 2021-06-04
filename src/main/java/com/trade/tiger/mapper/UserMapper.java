package com.trade.tiger.mapper;

import com.trade.tiger.domain.User;
import java.util.List;

/**
 * @description: ${description}
 * @author: Luozhi
 * @create: 2021-06-03 22:51
 **/
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBatchSelective(List<User> list);
}
package com.trade.tiger.mapper;

import com.trade.tiger.domain.UserTrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: ${description}
 *
 * @author: Luozhi
 *
 * @create: 2021-06-03 23:01
 **/
@Mapper
public interface UserTradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTrade record);

    int insertOrUpdate(UserTrade record);

    int insertOrUpdateSelective(UserTrade record);

    int insertSelective(UserTrade record);

    UserTrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTrade record);

    int updateByPrimaryKey(UserTrade record);

    int updateBatchSelective(List<UserTrade> list);
}
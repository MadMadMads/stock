package com.trade.tiger.mapper;

import com.trade.tiger.domain.TradeUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: ${description}
 *
 * @author: Luozhi
 *
 * @create: 2021-05-19 18:56
 **/
@Mapper
public interface TradeUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeUser record);

    int insertOrUpdate(TradeUser record);

    int insertOrUpdateSelective(TradeUser record);

    int insertSelective(TradeUser record);

    TradeUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeUser record);

    int updateByPrimaryKey(TradeUser record);

    int updateBatchSelective(List<TradeUser> list);
}
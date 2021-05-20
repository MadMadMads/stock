package com.trade.tiger.mapper;

import com.trade.tiger.domain.TradeRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: ${description}
 *
 * @author: Luozhi
 *
 * @create: 2021-05-19 18:56
 **/
@Mapper
public interface TradeRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserIdAndStockCode(@Param("userId")Integer userId,@Param("stockCode")String stockCode);

    int insert(TradeRule record);

    int insertOrUpdate(TradeRule record);

    int insertOrUpdateSelective(TradeRule record);

    int insertSelective(TradeRule record);

    TradeRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRule record);

    int updateByPrimaryKey(TradeRule record);

    int updateBatchSelective(List<TradeRule> list);

    List<TradeRule> selectAllByUserId(@Param("userId")Integer userId);

}
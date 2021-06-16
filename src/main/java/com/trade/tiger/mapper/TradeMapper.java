package com.trade.tiger.mapper;

import com.trade.tiger.domain.Trade;
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
public interface TradeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserIdAndStockCode(@Param("userId")Integer userId,@Param("stockCode")String stockCode);

    int insert(Trade record);

    int insertOrUpdate(Trade record);

    int insertOrUpdateSelective(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Integer id);

    List<Trade> selectByUserIdAndStockCode(@Param("userId")Integer userId,@Param("stockCode")String stockCode);

    int updateByStockCodeAndUserId(@Param("updated")Trade updated,@Param("stockCode")String stockCode,@Param("userId")Integer userId);



    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);

    int updateBatchSelective(List<Trade> list);

    List<Trade> selectAllByUserId(@Param("userId")Integer userId);
}
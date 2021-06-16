package com.trade.tiger.mapper;
import org.apache.ibatis.annotations.Param;

import com.trade.tiger.domain.StockInfo;
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
public interface StockInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockInfo record);

    int insertOrUpdate(StockInfo record);

    int insertOrUpdateSelective(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);

    int updateBatchSelective(List<StockInfo> list);

    List<StockInfo> selectByCode(@Param("code")String code);



}
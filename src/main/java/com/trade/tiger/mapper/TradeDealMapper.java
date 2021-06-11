package com.trade.tiger.mapper;

import com.trade.tiger.domain.TradeDeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: ${description}
 * @author: Luozhi
 * @create: 2021-06-03 23:23
 **/
@Mapper
public interface TradeDealMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(TradeDeal record);

    int insertOrUpdate(TradeDeal record);

    int insertOrUpdateSelective(TradeDeal record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(TradeDeal record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    TradeDeal selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TradeDeal record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TradeDeal record);

    int updateBatchSelective(List<TradeDeal> list);
}
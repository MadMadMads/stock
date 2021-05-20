package com.trade.tiger.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: ${description}
 *
 * @author: Luozhi
 *
 * @create: 2021-05-19 18:56
 **/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {
    private Integer id;

    private String code;

    private String name;

    /**
    * 交易所
    */
    private String exchange;

    /**
    * 缩写
    */
    private String abbreviation;

    private Byte state;

    /**
    * 类型股票
    */
    private Byte type;

    private Date createTime;

    private Date updateTime;

    private String description;
}
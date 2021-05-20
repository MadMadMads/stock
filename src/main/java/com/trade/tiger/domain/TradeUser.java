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
public class TradeUser {
    private Integer id;

    private String name;

    private String accountId;

    private String cookie;

    private String validateKey;

    private Byte state;

    private Date createTime;

    private Date updateTime;

    /**
    * 关联用户表信息
    */
    private Integer userId;

    /**
    * 交易用户的类别（老虎等）
    */
    private Integer tradeType;
}
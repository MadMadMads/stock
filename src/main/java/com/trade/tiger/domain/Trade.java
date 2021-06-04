package com.trade.tiger.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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
public class Trade {
    private Integer id;

    /**
    * 股票代码
    */
    @NotNull
    @ApiModelProperty(value="股票代码",name="stockCode",required = true)
    private String stockCode;

    /**
    * 算法策略
    */
    private Integer strategyId;

    /**
    * 买入用户
    */
    @NotNull
    @ApiModelProperty(value="买入用户",name="userId",required = true)
    private Integer userId;

    /**
    * 买入的机构（老虎证券等）
    */
    private Integer type;

    /**
    * 买入价
    */
    private BigDecimal value;

    /**
    * 买入量
    */
    private Integer volume;

    /**
    * 初始价格
    */
    private BigDecimal openPrice;

    /**
    * 最高价格
    */
    private BigDecimal highestPrice;

    /**
    * 最低价格
    */
    private BigDecimal lowestPrice;

    private Byte state;

    /**
    * 自定义描述
    */
    private String description;

    private Date createTime;

    private Date updateTime;
}
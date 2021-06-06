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
public class TradeVo {
    private Integer id;

    /**
    * 股票代码
    */
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
    @ApiModelProperty(value="买入的机构 先只有1",name="type")
    private String type;

    /**
    * 买入价
    */
    @ApiModelProperty(value="买入价",name="value")
    private BigDecimal value;

    /**
    * 买入量
    */
    @ApiModelProperty(value="买入量",name="volume")
    private Integer volume;

    private BigDecimal total;

    /**
     * 买入价
     */
    @ApiModelProperty(value="最新价",name="price")
    private BigDecimal price;

    /**
     * 买入价
     */
    @ApiModelProperty(value="买入价",name="value")
    private BigDecimal upsAnddowns;

    private Byte state;

    /**
    * 自定义描述
    */
    private String description;

    private Date createTime;

    private Date updateTime;

    private Integer pageIndex;

    private Integer pageSize;
}
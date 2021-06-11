package com.trade.tiger.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: ${description}
 * @author: Luozhi
 * @create: 2021-06-03 23:23
 **/
@ApiModel(value = "com-trade-tiger-domain-TradeDeal")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDeal {
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码")
    private String stockCode;

    @ApiModelProperty(value = "")
    private BigDecimal price;

    @ApiModelProperty(value = "")
    private Integer volume;

    /**
     * 通知类型
     */
    @ApiModelProperty(value = "通知类型")
    private String messageType;

    /**
     * 通知时间
     */
    @ApiModelProperty(value = "通知时间")
    private Date tradeTime;

    @ApiModelProperty(value = "")
    private Integer userId;

    @ApiModelProperty(value = "")
    private Date createTime;

    @ApiModelProperty(value = "")
    private Date updateTime;

    /**
     * 通知内容
     */
    @ApiModelProperty(value = "通知内容")
    private String content;

    /**
     * 通知状态
     */
    @ApiModelProperty(value = "通知状态")
    private String state;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "交易状态")
    private Integer type;
}
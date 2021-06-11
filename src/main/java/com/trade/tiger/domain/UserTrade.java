package com.trade.tiger.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @create: 2021-06-03 23:01
 **/
@ApiModel(value="com-trade-tiger-domain-UserTrade")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTrade {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="")
    private Integer tradeId;
}
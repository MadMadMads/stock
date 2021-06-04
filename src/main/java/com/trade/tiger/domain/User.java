package com.trade.tiger.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: ${description}
 * @author: Luozhi
 * @create: 2021-06-03 22:51
 **/
@ApiModel(value = "com-trade-tiger-domain-User")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String username;

    @ApiModelProperty(value = "")
    private String password;

    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private String mobile;

    @ApiModelProperty(value = "")
    private String email;

    @ApiModelProperty(value = "")
    private Date createTime;

    @ApiModelProperty(value = "")
    private Date updateTime;

    @ApiModelProperty(value = "")
    private BigDecimal fundAccount;

    @ApiModelProperty(value = "")
    private BigDecimal transactionAccount;
}
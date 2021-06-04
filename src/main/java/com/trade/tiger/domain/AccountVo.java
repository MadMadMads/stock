package com.trade.tiger.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: Luozhi
 * @create: 2021-06-01 19:55
 **/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo {
    BigDecimal fundAccount;
    BigDecimal transactionAccount;
    BigDecimal totalAccount;
    BigDecimal availableAccount;
    @ApiModelProperty(value="每个机构的存款",name="money",example="{}")
    List<TradeVo> money;
}

package com.trade.tiger.controller;

import com.trade.tiger.common.resultbean.ResultMsg;
import com.trade.tiger.domain.TradeRule;
import com.trade.tiger.domain.TradeRuleVo;
import com.trade.tiger.domain.User;
import com.trade.tiger.service.SimulationTradeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @description: 模拟仓
 * @author: Luozhi
 * @create: 2021-05-19 21:25
 **/
@RestController
@RequestMapping("/trade/web")
@Slf4j
public class SimulationTradeController {
    @Resource
    SimulationTradeService simulationTradeService;

    @PostMapping("/add")
    @ApiOperation(value = "模拟仓添加股票",notes = "必须传入股票代码和用户Id")
    public ResultMsg<Boolean> addSimulationTrade(@RequestBody @Validated TradeRule tradeRule) {
        ResultMsg<Boolean> result = ResultMsg.build();
        try {
            simulationTradeService.addTrade(tradeRule);
        } catch (Exception e) {
            log.error("[addSimulationTrade] 遇到异常e:{}",e);
        }
        return result;
    }
    @ApiOperation(value = "模拟仓删除股票",notes = "必须传入股票代码和用户Id")
    @PostMapping("/del")
    public ResultMsg<Boolean> delSimulationTrade(@RequestBody @Validated TradeRule tradeRule) {
        ResultMsg<Boolean> result = ResultMsg.build();
        simulationTradeService.deleteTrade(tradeRule);
        return result;
    }
    @ApiOperation(value = "获取用户的股票",notes = "必须传入用户Id，分页参数(pageIndex,PageSize)")
    @PostMapping("/query")
    public ResultMsg<Boolean> querySimulationTrade(@RequestBody @Validated TradeRuleVo tradeRule) {
        ResultMsg<Boolean> result = ResultMsg.build();
        simulationTradeService.selectTrade(tradeRule.getUserId(),tradeRule.getPageIndex(),tradeRule.getPageSize());
        return result;
    }
    @ApiOperation(value = "获取模拟仓账户的资产信息",notes = "必须传入用户Id,返回的key为老虎等证券结构，value为拥有的资产")
    @PostMapping("/money")
    public ResultMsg<Object> delSimulationTrade(@RequestBody @Validated User user) {
        ResultMsg<Object> result = ResultMsg.build();
        Map<String, BigDecimal> money = simulationTradeService.getMoney(user);
        result.setData(money);
        return result;
    }

}

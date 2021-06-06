package com.trade.tiger.controller;

import com.trade.tiger.common.enums.TradeEnums;
import com.trade.tiger.common.resultbean.ResultMsg;
import com.trade.tiger.domain.*;
import com.trade.tiger.mapper.UserMapper;
import com.trade.tiger.mapper.UserTradeMapper;
import com.trade.tiger.service.SimulationTradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    UserMapper userMapper;

    @Resource
    UserTradeMapper userTradeMapper;

    @PostMapping("/add")
    @ApiOperation(value = "模拟仓买入股票", notes = "必须传入股票代码和用户Id")
    public ResultMsg<Boolean> addSimulationTrade(@RequestBody @Validated Trade trade) {
        ResultMsg<Boolean> result = ResultMsg.build();
        try {
            simulationTradeService.addTrade(trade);
        } catch (Exception e) {
            log.error("[addSimulationTrade] 遇到异常e:{}", e);
        }
        return result;
    }

    @ApiOperation(value = "模拟仓卖出股票", notes = "必须传入股票代码和用户Id")
    @ApiResponse(code = 200, message = "@ApiResponses")
    @PostMapping("/del")
    public ResultMsg<Boolean> delSimulationTrade(@RequestBody @Validated Trade trade) {
        ResultMsg<Boolean> result = ResultMsg.build();
        boolean b = simulationTradeService.deleteTrade(trade);
        result.setData(b);
        return result;
    }

    @ApiOperation(value = "获取特定股票持有情况", notes = "必须传入股票Id,用户ID")
    @PostMapping("/query/one/stock")
    public ResultMsg<TradeVo> queryOneStock(@RequestBody @Validated TradeVo Trade) {
        ResultMsg<TradeVo> result = ResultMsg.build();
        User user = userMapper.selectByPrimaryKey(Trade.getUserId());
        List<TradeVo> money = simulationTradeService.getMoney(user);
        TradeVo tradeVo1 = new TradeVo();
        for (TradeVo tradeVo : money) {
            if (tradeVo.getStockCode().equals(Trade.getStockCode())) {
                tradeVo1 = tradeVo;
            }
        }
        result.setData(tradeVo1);
        return result;
    }

    @ApiOperation(value = "获取用户的股票", notes = "必须传入用户ID")
    @PostMapping("/query")
    public ResultMsg<List<Trade>> querySimulationTrade(@RequestBody @Validated User user) {
        ResultMsg<List<Trade>> result = ResultMsg.build();
        List<Trade> Trades = simulationTradeService.selectTrade(user.getId(), 0, Integer.MAX_VALUE);
        result.setData(Trades);
        return result;
    }

    @ApiOperation(value = "增加自选股票", notes = "必须传入用户ID,股票id")
    @GetMapping("/add/optional/stock")
    public ResultMsg<List<TradeVo>> addTrade(@PathParam("uid") Integer uid, @PathParam("trade_id") Integer trade_id) {
        ResultMsg<List<TradeVo>> result = ResultMsg.build();
        List<TradeVo> tradeVos = new ArrayList<>();
        UserTrade userTrade = new UserTrade();
        userTrade.setUserId(uid);
        userTrade.setTradeId(trade_id);
        userTradeMapper.insert(userTrade);
        for (int i = 0; i < 20 ;i++) {
            TradeVo tradeVo = new TradeVo();
            tradeVo.setType(TradeEnums.getDescByCode(1));
            tradeVo.setValue(new BigDecimal("1"));
            tradeVo.setVolume(123);
            tradeVo.setStockCode("123");
            tradeVo.setPrice(new BigDecimal("1"));
            tradeVo.setUpsAnddowns(new BigDecimal("0.12"));
            tradeVos.add(tradeVo);
        }
        result.setData(tradeVos);
        return result;
    }

    @ApiOperation(value = "获取模拟仓账户的资产信息", notes = "必须传入用户Id,返回的key为老虎等证券结构，value为拥有的资产")
    @PostMapping("/money")
    public ResultMsg<AccountVo> getSimulationTrade(@RequestBody @Validated User user) {
        ResultMsg<AccountVo> result = ResultMsg.build();
        AccountVo accountVo = new AccountVo();
        BigDecimal fundAccount = new BigDecimal(0);
        fundAccount = userMapper.selectByPrimaryKey(user.getId()).getFundAccount();
        BigDecimal transactionAccount = new BigDecimal(0);
        transactionAccount = userMapper.selectByPrimaryKey(user.getId()).getTransactionAccount();
        List<TradeVo> money = simulationTradeService.getMoney(user);
        accountVo.setFundAccount(fundAccount);
        accountVo.setTransactionAccount(transactionAccount);
        accountVo.setMoney(money);
        accountVo.setTotalAccount(fundAccount.add(transactionAccount));
        BigDecimal divide = transactionAccount.divide(fundAccount);
        result.setData(accountVo);
        return result;
    }

    @ApiOperation(value = "修改用户的股票", notes = "股票信息")
    @PostMapping("/edit")
    public ResultMsg<Boolean> editTrade(@RequestBody @Validated Trade trade) {
        ResultMsg<Boolean> result = ResultMsg.build();
        result.setData(simulationTradeService.editTrade(trade));
        return result;
    }

    public static void main(String[] args) {
        Integer i = 20;
        BigDecimal bigDecimal = new BigDecimal(5);
        BigDecimal divide = bigDecimal.divide(BigDecimal.valueOf(i));
        System.out.println(bigDecimal.toString());
    }
}

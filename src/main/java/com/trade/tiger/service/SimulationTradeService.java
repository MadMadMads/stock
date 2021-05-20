package com.trade.tiger.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trade.tiger.common.enums.TradeEnums;
import com.trade.tiger.domain.TradeRule;
import com.trade.tiger.domain.User;
import com.trade.tiger.mapper.TradeRuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 模拟交易
 * @author: Luozhi
 * @create: 2021-05-19 19:02
 **/
@Service
public class SimulationTradeService {
    @Resource
    TradeRuleMapper ruleMapper;

    public boolean addTrade(TradeRule tradeRule) {
        tradeRule.setType(TradeEnums.getCodeByDesc("tiger"));
        tradeRule.setCreateTime(new Date());
        tradeRule.setUpdateTime(new Date());
        return ruleMapper.insert(tradeRule) > 0 ? true : false;
    }

    public boolean deleteTrade(TradeRule tradeRule) {
        return ruleMapper.deleteByUserIdAndStockCode(tradeRule.getUserId(),tradeRule.getStockCode()) > 0 ? true : false;
    }

    public List<TradeRule> selectTrade(Integer uid,Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo<TradeRule> tradeRules = new PageInfo<TradeRule>(ruleMapper.selectAllByUserId(uid));
        return tradeRules.getList();
    }

    public Map<String,BigDecimal> getMoney(User user) {
        Map<String, BigDecimal> map = new HashMap<>();
        List<TradeRule> tradeRules = selectTrade(user.getId(), 0, Integer.MAX_VALUE);
        for (TradeRule tradeRule : tradeRules) {
            map.putIfAbsent(TradeEnums.getDescByCode(tradeRule.getType()),tradeRule.getValue().multiply(BigDecimal.valueOf(tradeRule.getVolume())));
        }
        return map;
    }
}

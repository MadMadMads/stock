package com.trade.tiger.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trade.tiger.common.enums.TradeEnums;
import com.trade.tiger.domain.Trade;
import com.trade.tiger.domain.TradeDeal;
import com.trade.tiger.domain.TradeVo;
import com.trade.tiger.domain.User;
import com.trade.tiger.mapper.TradeDealMapper;
import com.trade.tiger.mapper.TradeMapper;
import com.trade.tiger.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description: 模拟交易
 * @author: Luozhi
 * @create: 2021-05-19 19:02
 **/
@Service
public class SimulationTradeService {
    @Resource
    TradeMapper tradeMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    MessageService messageService;

    @Resource
    TradeDealMapper tradeDealMapper;

    public boolean addTrade(Trade trade) throws Exception {
        trade.setCreateTime(new Date());
        trade.setUpdateTime(new Date());
        User user = userMapper.selectByPrimaryKey(trade.getUserId());
        user.setTransactionAccount(user.getTransactionAccount().divide(trade.getValue().multiply(new BigDecimal(trade.getVolume()))));
        TradeDeal tradeDeal = TradeDeal.builder().tradeTime(new Date()).createTime(new Date()).price(trade.getValue())
                .stockCode(trade.getStockCode()).volume(trade.getVolume())
                .updateTime(new Date()).type(0).build();
        tradeDealMapper.insert(tradeDeal);
        Trade trade1 = tradeMapper.selectByPrimaryKey(trade.getId());
        if (trade1 == null) {
            tradeMapper.insert(trade);
        } else {
            trade1.setVolume(trade1.getVolume() + trade.getVolume());
            tradeMapper.updateByPrimaryKey(trade1);
        }
//        messageService.send(user.getMobile(), "5375", null);
        return tradeMapper.insert(trade) > 0 ? true : false;
    }

    public boolean deleteTrade(Trade trade) {
        User user = userMapper.selectByPrimaryKey(trade.getUserId());
        user.setTransactionAccount(user.getTransactionAccount().add(trade.getValue().multiply(new BigDecimal(trade.getVolume()))));
        userMapper.updateByPrimaryKey(user);
        TradeDeal tradeDeal = tradeDealMapper.selectByPrimaryKey(trade.getId());
        tradeDeal.setType(1);
        tradeDealMapper.updateByPrimaryKey(tradeDeal);
        Trade trade1 = tradeMapper.selectByPrimaryKey(trade.getId());
        trade1.setVolume(trade1.getVolume() - trade.getVolume());
        tradeMapper.updateByPrimaryKey(trade1);
        return true;
    }

    public List<Trade> selectTrade(Integer uid, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        PageInfo<Trade> Trades = new PageInfo<Trade>(tradeMapper.selectAllByUserId(uid));
        return Trades.getList();
    }

    public List<TradeVo> getMoney(User user) {
        List<TradeVo> list = new ArrayList<>();
        List<Trade> trades = selectTrade(user.getId(), 0, Integer.MAX_VALUE);
        Set<String> repeatCode = new HashSet<>();
        for (int i = 0; i < trades.size(); i++) {
            TradeVo tradeVo = new TradeVo();
            tradeVo.setType(TradeEnums.getDescByCode(trades.get(i).getType()));
            tradeVo.setStockCode(trades.get(i).getStockCode());
            if (repeatCode.contains(tradeVo.getStockCode())) {
                continue;
            }
            repeatCode.add(tradeVo.getStockCode());
            BigDecimal total = trades.get(i).getValue().multiply(new BigDecimal(trades.get(i).getVolume()));
            Integer amount = trades.get(i).getVolume();
            for (int j = i + 1; j < trades.size(); j++) {
                Trade trade = trades.get(j);
                if (trade.getStockCode().equals(tradeVo.getStockCode())) {
                    total = total.add(trade.getValue().multiply(new BigDecimal(trade.getVolume())));
                    amount = amount + trade.getVolume();
                }
            }
            tradeVo.setTotal(total);
            tradeVo.setVolume(amount);
            if (amount != 0) {
                tradeVo.setValue(total.divide(new BigDecimal(amount)));
            } else {
                tradeVo.setValue(new BigDecimal(0));
            }
            list.add(tradeVo);
        }
        return list;
    }

    public boolean editTrade(Trade trade) {
        int i = tradeMapper.updateByPrimaryKey(trade);
        return i > 0 ? true : false;
    }
}

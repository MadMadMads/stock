package com.trade.tiger.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trade.tiger.common.enums.TradeEnums;
import com.trade.tiger.domain.*;
import com.trade.tiger.mapper.StockInfoMapper;
import com.trade.tiger.mapper.TradeDealMapper;
import com.trade.tiger.mapper.TradeMapper;
import com.trade.tiger.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.nio.cs.FastCharsetProvider;

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
    StockInfoMapper stockInfoMapper;

    @Resource
    TradeDealMapper tradeDealMapper;

    @Transactional(rollbackFor=Exception.class)
    public boolean addTrade(Trade trade) throws Exception {
        trade.setCreateTime(new Date());
        trade.setUpdateTime(new Date());
        User user = userMapper.selectByPrimaryKey(trade.getUserId());
        BigDecimal subtract = user.getTransactionAccount().subtract(trade.getValue().multiply(new BigDecimal(trade.getVolume())));
        if (subtract.compareTo(new BigDecimal(0)) < 0) {
            return false;
        }
        user.setTransactionAccount(subtract);
        userMapper.updateByPrimaryKey(user);
        TradeDeal tradeDeal = TradeDeal.builder().tradeTime(new Date()).createTime(new Date()).price(trade.getValue())
                .stockCode(trade.getStockCode()).volume(trade.getVolume())
                .updateTime(new Date()).type(0).userId(trade.getUserId()).build();
        tradeDealMapper.insert(tradeDeal);
        Trade trade1 = tradeMapper.selectByUserIdAndStockCode(trade.getUserId(),trade.getStockCode()).stream().findAny().orElse(null);
        if (trade1 == null) {
            tradeMapper.insert(trade);
        } else {
            trade1.setVolume(trade1.getVolume() + trade.getVolume());
            tradeMapper.updateByPrimaryKey(trade1);
        }
//        messageService.send(user.getMobile(), "5375", null);
        return true;
    }

    @Transactional(rollbackFor=Exception.class)
    public boolean deleteTrade(Trade trade) {
        Trade trade1 = tradeMapper.selectByUserIdAndStockCode(trade.getUserId(),trade.getStockCode()).stream().findAny().orElse(null);
        if (trade1 == null) {
            return false;
        }
        Long volume = trade1.getVolume() - trade.getVolume();
        if (volume < 0) {
            return false;
        }
        trade1.setVolume(volume);
        User user = userMapper.selectByPrimaryKey(trade.getUserId());
        //todo 股票现价乘以数量
        user.setTransactionAccount(user.getTransactionAccount().add(trade.getValue().multiply(new BigDecimal(trade.getVolume()))));
        userMapper.updateByPrimaryKey(user);
        TradeDeal tradeDeal = TradeDeal.builder().tradeTime(new Date()).createTime(new Date()).price(trade.getValue())
                .stockCode(trade.getStockCode()).volume(trade.getVolume())
                .updateTime(new Date()).type(1).userId(trade.getUserId()).build();
        tradeDealMapper.insert(tradeDeal);
        if (trade1.getVolume() > 0) {
            tradeMapper.updateByStockCodeAndUserId(trade1,trade1.getStockCode(),trade1.getUserId());
        } else {
            tradeMapper.deleteByUserIdAndStockCode(trade1.getUserId(),trade1.getStockCode());
        }
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
            Optional<StockInfo> any = stockInfoMapper.selectByCode(trades.get(i).getStockCode()).stream().findAny();
            tradeVo.setStockName(any.orElse(new StockInfo()).getName());
            tradeVo.setType(TradeEnums.getDescByCode(trades.get(i).getType()));
            tradeVo.setStockCode(trades.get(i).getStockCode());
            if (repeatCode.contains(tradeVo.getStockCode())) {
                continue;
            }
            repeatCode.add(tradeVo.getStockCode());
            BigDecimal total = trades.get(i).getValue().multiply(new BigDecimal(trades.get(i).getVolume()));
            Long amount = trades.get(i).getVolume();
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

    @Transactional(rollbackFor=Exception.class)
    public boolean editTrade(Trade trade) {
        int i = tradeMapper.updateByStockCodeAndUserId(trade,trade.getStockCode(),trade.getUserId());
        return i > 0 ? true : false;
    }
}

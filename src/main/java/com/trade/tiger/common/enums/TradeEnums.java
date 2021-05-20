package com.trade.tiger.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 交易枚举类
 * @author: Luozhi
 * @create: 2021-05-19 20:21
 **/
public enum  TradeEnums {
    TIGER(1,"老虎证券");


    private Integer code;

    private String desc;

    TradeEnums(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }
    private static Map<Integer, TradeEnums> map = new HashMap<Integer, TradeEnums>();
    private static Map<String, TradeEnums> desMap = new HashMap<String, TradeEnums>();
    static {
        for(TradeEnums TradeEnums : TradeEnums.values()){
            map.put(TradeEnums.code,TradeEnums);
            desMap.put(TradeEnums.desc, TradeEnums);
        }
    }

    public Integer getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

    public static  Integer getCodeByDesc(String desc){

        TradeEnums TradeEnums=desMap.get(desc);
        if (TradeEnums==null){
            return null;
        }
        return TradeEnums.code;
    }
    public static String getDescByCode(Integer code){

        TradeEnums TradeEnums=map.get(code);
        if (TradeEnums==null){
            return null;
        }
        return TradeEnums.desc;
    }
}

package com.trade.tiger.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 通知服务
 * @author: Luozhi
 * @create: 2021-05-17 18:11
 **/
@Service
public interface MessageService {
    public String send(String tel, String templateId, List<Object> templateParams) throws Exception;

}

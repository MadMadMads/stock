package com.trade.tiger.service;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Luozhi
 * @create: 2021-05-17 18:11
 **/
@Service
public class MessageServiceImpl implements MessageService {



    ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "109131", "8b1e1f94-4387-49ad-8d36-1b730970b683");

    @Override
    public String send(String tel, String templateId, List<Object> templateParams) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", tel);
        params.put("templateId", templateId);
        String[] templateParams1 = new String[2];
        templateParams1[0] = "3421";
        templateParams1[1] = "5分钟";
        params.put("templateParams", templateParams1);
        String result = client.send(params);
        return result;
    }
}

package com.trade.tiger.service;

import com.aliyun.teaopenapi.models.Config;


/**
 * @description:
 * @author: Luozhi
 * @create: 2021-05-17 18:11
 **/
public class MessageServiceImpl implements MessageService {


    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    @Override
    public String send() {
        return null;
    }
}

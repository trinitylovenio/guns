package com.dhxh.guns.modular.dhxh.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.dhxh.guns.modular.dhxh.service.Impl.BusinessServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author huli
 * @time 2019-04-17
 * @description
 */
public class SmsUtil {

    @Value("${aliyun.accessKeyId}")
    private static String accessKeyId;

    @Value("${aliyun.accessSecret}")
    private static String accessSecret;

    @Value("${aliyun.regionId}")
    private static String regionId;

    private static final Logger LOG = LoggerFactory.getLogger(SmsUtil.class);
    /**
     * 发送验证码
     *
     * @param accessKeyId   阿里云的accessKeyId
     * @param accessSecret  阿里云的accessSecret
     * @param regionId      regionId默认是default
     * @param templateParam 模版信息 带参数
     * @param signName      短信签名
     * @param templateCode  短信模版code SMS_163720402
     * @param phoneNumbers  需要发送的手机号 多个用逗号分割
     * @return 结果示例：
     * {
     * "Message": "OK",
     * "RequestId": "027C307A-2E86-497C-9BFB-FCFCD874511A",
     * "BizId": "697514455552331733^0",
     * "Code": "OK"
     * }
     */
    public static JSONObject sendVerificationCode(String accessKeyId, String accessSecret, String regionId,
                                                  String templateParam, String signName, String templateCode,
                                                  String phoneNumbers) throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("TemplateParam", templateParam);
        CommonResponse response = client.getCommonResponse(request);
        LOG.info("发送短信结果"+response.getData());
        return JSON.parseObject(response.getData());
    }

}



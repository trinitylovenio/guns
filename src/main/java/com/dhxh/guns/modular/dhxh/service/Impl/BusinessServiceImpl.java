package com.dhxh.guns.modular.dhxh.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.dhxh.guns.modular.dhxh.dao.BusinessDao;
import com.dhxh.guns.modular.dhxh.service.BusinessService;
import com.dhxh.guns.modular.dhxh.utils.MD5Util;
import com.dhxh.guns.modular.dhxh.utils.SmsUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:20
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Value("${aliyun.regionId}")
    private String regionId;

    @Value("${aliyun.signName.dx}")
    private String signName;

    @Value("${aliyun.templateCode.dx}")
    private String templateCode;


    @Resource
    BusinessDao businessDao;

    private static final Logger LOG = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Override
    public Object queryBusinessInfoList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> orderList= businessDao.queryBusinessInfoList(map);
            int orderListNum= businessDao.queryBusinessInfoListNum(map);
            resMap.put("rows",orderList);
            resMap.put("total",orderListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取企业用户列表异常 {}",e);
            resMap.put("msg","获取企业用户列表异常");
        }
        return resMap;
    }

    @Override
    public Object queryMSList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> orderList= businessDao.queryMSList(map);
            int orderListNum= businessDao.queryMSListNum(map);
            resMap.put("rows",orderList);
            resMap.put("total",orderListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取企业包月列表异常 {}",e);
            resMap.put("msg","获取企业包月列表异常");
        }
        return resMap;
    }

    @Override
    public Object addSubmit(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try{
            if(map.get("telephone")!=null && !"".equals(map.get("telephone").toString()) && map.get("telephone").toString().length()==11){
                String telephone = map.get("telephone").toString();
                String business_name = map.get("business_name").toString();
                //发送用密码
                String PWD = RandomStringUtils.random(6, "0123456789qwertyuiopasdfghjklzxcvbnm");
                //入库用密码
                String password = MD5Util.md5(PWD);
                map.put("password",password);
                int count= 0;
                count = businessDao.updateBusinessInfo(map);
                if(count>0){
                    String code = RandomStringUtils.random(4, "0123456789");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("telephone", telephone);
                    jsonObject.put("name", business_name);
                    jsonObject.put("password", PWD);
                    //添加账户成功，给用户发短信通知密码
                    SmsUtil.sendVerificationCode(accessKeyId, accessSecret, regionId, jsonObject.toJSONString(), signName, templateCode,
                            map.get("telephone").toString());
                    resMap.put("code",1);
                    resMap.put("msg","创建成功，短信已发");
                }else {
                    resMap.put("code",-2);
                    resMap.put("msg","创建账户入库失败");
                }
            }else {
                resMap.put("code",-3);
                resMap.put("msg","手机号格式错误");
            }
        }catch (ClientException e){
            e.printStackTrace();
            resMap.put("code",-1);
            resMap.put("msg","创建账户失败:"+e.getMessage());
        }
        return resMap;
    }

    @Override
    public Object updateBusinessInfo(HashMap map) {
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        try{
            int count = 0;
            count = businessDao.updateBusinessInfo(map);
            if (count > 0) {
                String code = RandomStringUtils.random(4, "0123456789");
                JSONObject jsonObject = new JSONObject();
                resMap.put("code", 1);
                resMap.put("msg", "修改账户成功");
            } else {
                resMap.put("code", -2);
                resMap.put("msg", "修改账户失败,入库失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code",-1);
            resMap.put("msg","修改账户失败:"+e.getMessage());
        }

        return resMap;
    }
}

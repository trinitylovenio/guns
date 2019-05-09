package com.dhxh.guns.modular.dhxh.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhxh.guns.modular.dhxh.bean.ShunfengOrderParam;
import com.dhxh.guns.modular.dhxh.dao.OrderDao;
import com.dhxh.guns.modular.dhxh.dao.ShunfengDao;
import com.dhxh.guns.modular.dhxh.service.OrderService;
import com.dhxh.guns.modular.dhxh.service.ShunfengService;
import com.dhxh.guns.modular.dhxh.utils.BeanUtil;
import com.dhxh.guns.modular.dhxh.utils.ShunfengUtil;
import com.dhxh.guns.modular.dhxh.utils.SignUtil;
import com.dhxh.guns.modular.dhxh.utils.SmallTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.print.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;


/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:20
 */
@Service
public class ShunfengServiceImpl implements ShunfengService {

    @Value("${shunfeng.dev_id}")
    private int dev_id;
    @Value("${shunfeng.dev_key}")
    private String dev_key;
    @Value("${shunfeng.shop_id}")
    private String shop_id;
    @Value("${shunfeng.shop_type}")
    private int shop_type;
    @Value("${shunfeng.order_source}")
    private String order_source;
    @Value("${shunfeng.lbs_type}")
    private int lbs_type;
    @Value("${shunfeng.pay_type}")
    private int pay_type;
    @Value("${shunfeng.is_appoint}")
    private int is_appoint;
    @Value("${shunfeng.is_insured}")
    private int is_insured;
    @Value("${shunfeng.rider_pick_method}")
    private int rider_pick_method;
    @Value("${shunfeng.version}")
    private int version;
    @Value("${shunfeng.orderUrl}")
    private String orderUrl;

    @Resource
    ShunfengDao shunfengDao;

    private static final Logger LOG = LoggerFactory.getLogger(ShunfengServiceImpl.class);

    @Override
    public Object shunfengList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> shufengOrderList = shunfengDao.queryShunfengOrderList(map);
            int shunfengOrderListNum= shunfengDao.queryShunfengOrderListNum(map);
            resMap.put("rows",shufengOrderList);
            resMap.put("total",shunfengOrderListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取订单列表异常 {}",e);
            resMap.put("msg","获取订单列表异常");
        }
        return resMap;
    }

    @Override
    public Object ShunfengOrder(HashMap map) {
        List<HashMap<String,Object>> shufengOrderList = shunfengDao.queryShunfengOrderList(map);
        if(shufengOrderList!=null && shufengOrderList.size()>0){
            for(HashMap shunfengOrder:shufengOrderList){
                JSONObject back = CreateShunfengOrder(shunfengOrder);
                if(back!=null && back.get("result")!=null){
                    JSONObject result = (JSONObject) back.get("result");
                    if("0".equals(back.get("error_code").toString())){
                        shunfengOrder.put("sf_order_id",result.get("sf_order_id").toString());
                        shunfengOrder.put("order_status",1);
                        shunfengDao.updateShunfengOrder(shunfengOrder);
                    }
                }
            }
        }
        return null;
    }


    public JSONObject CreateShunfengOrder(HashMap map){
        ShunfengOrderParam shunfengOrderParam = new ShunfengOrderParam();

        shunfengOrderParam.setDev_id(dev_id);
        shunfengOrderParam.setShop_id(shop_id);
        shunfengOrderParam.setShop_type(shop_type);
        shunfengOrderParam.setShop_order_id(map.get("shunfeng_id").toString());
        shunfengOrderParam.setOrder_source(order_source);
        shunfengOrderParam.setLbs_type(lbs_type);
        shunfengOrderParam.setPay_type(pay_type);
        long order_time = System.currentTimeMillis()/1000;
        shunfengOrderParam.setOrder_time((int) order_time);
        shunfengOrderParam.setIs_appoint(is_appoint);
        long expect_time = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            expect_time =sdf.parse(map.get("expect_time").toString()).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        shunfengOrderParam.setExpect_time((int) expect_time);
        shunfengOrderParam.setIs_insured(is_insured);
        shunfengOrderParam.setRider_pick_method(rider_pick_method);
        //当前订单推送到顺丰的时间误差与当前时间不能超过180秒
        shunfengOrderParam.setPush_time((int) order_time);
        shunfengOrderParam.setVersion(version);

        //拼装Receive
        JSONObject shunfengReceive = new JSONObject();
        shunfengReceive.put("user_name",map.get("user_name").toString());
        shunfengReceive.put("user_phone",map.get("user_phone").toString());
        shunfengReceive.put("user_address",map.get("user_address").toString());
        shunfengReceive.put("user_lng",map.get("user_lng").toString());
        shunfengReceive.put("user_lat",map.get("user_lat").toString());
        shunfengOrderParam.setReceive(shunfengReceive);

        //拼装order_detail
        JSONObject shunfengOrderDetail = new JSONObject();
        shunfengOrderDetail.put("total_price",0);
        shunfengOrderDetail.put("product_type",1);
        //每份500g
        shunfengOrderDetail.put("weight_gram",500*Integer.parseInt(map.get("product_num").toString()));
        shunfengOrderDetail.put("product_num",Integer.parseInt(map.get("product_num").toString()));
        shunfengOrderDetail.put("product_type_num",1);
        //拼装product_detail
        JSONArray product_detailArray = new JSONArray();
        JSONObject shunfengProductDetail = new JSONObject();
        shunfengProductDetail.put("product_name","大哈小哈营养早餐");
        shunfengProductDetail.put("product_num",Integer.parseInt(map.get("product_num").toString()));
        product_detailArray.add(shunfengProductDetail);
        shunfengOrderDetail.put("product_detail",product_detailArray);

        shunfengOrderParam.setOrder_detail(shunfengOrderDetail);

        Map<String, Object> data = BeanUtil.object2Map(shunfengOrderParam); // 参数列表
        String sign = SignUtil.sign(SmallTools.MapToJSONObject(data).toJSONString(),String.valueOf(dev_id),dev_key);
        String resultStr = ShunfengUtil.postXml(orderUrl,SmallTools.MapToJSONObject(data).toJSONString(),sign);

        JSONObject resutl = JSONObject.parseObject(resultStr);
        return resutl;
    }

}

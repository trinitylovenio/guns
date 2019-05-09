package com.dhxh.guns.modular.dhxh.service.Impl;

import com.dhxh.guns.modular.dhxh.dao.OrderDao;
import com.dhxh.guns.modular.dhxh.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.print.*;
import java.util.*;
import java.util.List;


/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:20
 */
@Service
public class OrderServiceImpl implements OrderService {

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
    OrderDao orderDao;

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 获取订单列表
     */
    @Override
    public Object queryorderList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> personalServiceList= orderDao.queryPersonalServiceList(map);
            int personalServiceListNum= orderDao.queryPersonalServiceListNum(map);
            resMap.put("rows",personalServiceList);
            resMap.put("total",personalServiceListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取包月信息列表异常 {}",e);
            resMap.put("msg","获取包月信息列表异常");
        }
        return resMap;
    }

    /**
     * 获取订单汇总列表
     */
    @Override
    public Object queryOrderCountList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> orderCountList= orderDao.queryOrderCountList(map);
            for(HashMap<String,Object> orderCount : orderCountList){
                String a=orderCount.get("COUNT_NUM").toString();
                orderCount.replace("COUNT_NUM",a);
            }
            resMap.put("rows",orderCountList);
            resMap.put("total",orderCountList.size());
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取订单汇总列表异常 {}",e);
            resMap.put("msg","获取订单汇总列表异常");
        }
        return resMap;
    }


    @Override
    public List<HashMap<String, Object>> exportExcel(HashMap map) {
        try {
            List<HashMap<String,Object>> orderList = orderDao.queryOrderList(map);
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取订单列表异常 {}",e);
            return null;
        }
    }

    @Override
    public Object prientOrder(HashMap map){
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> orderList = orderDao.queryOrderList(map);
            int day =1;
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= 13) {
                day = 2;
            }
            String serviceDate = "";
            serviceDate= orderDao.getServiceDate(day);
            if(orderList!=null && orderList.size()>0){
                // 通俗理解就是书、文档
                // 打印格式
                PageFormat pf = new PageFormat();
                pf.setOrientation(PageFormat.REVERSE_LANDSCAPE);
                // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
                Paper p = new Paper();
                p.setSize(210, 150);
                p.setImageableArea(2, -5, 190, 145);
                pf.setPaper(p);
                PrinterJob job = PrinterJob.getPrinterJob();
                for(HashMap<String,Object> order : orderList){
                    Book book = new Book();
                    // 把 PageFormat 和 Printable 添加到书中，组成一个页面
                    if(order.get("NAME5")!=null && order.get("USER_NAME")!=null
                            && order.get("TELEPHONE")!=null && order.get("ACCURATE_ADDRESS")!=null){
                        String finalServiceDate = serviceDate;
                        Printable printable = new Printable() {
                            @Override
                            public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                                if (pageIndex > 0) {
                                    return NO_SUCH_PAGE;
                                }

                                Graphics2D g2d = (Graphics2D) g;
                                g2d.setFont(new Font("Default", Font.PLAIN, 7));
                                g2d.drawString("大哈小哈营养早餐", 40, 25);
                                g2d.setFont(new Font("Default", Font.PLAIN, 12));
                                g2d.drawString("-----------------------------------------------------------------------------", 0, 35);
                                g2d.drawString(order.get("USER_NAME").toString()+":"+order.get("TELEPHONE").toString(), 15, 45);
                                g2d.setFont(new Font("Default", Font.PLAIN, 11));
                                g2d.drawString(order.get("NAME5").toString(), 15, 55);
                                if(order.get("ACCURATE_ADDRESS").toString().length()<=12){
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString(), 10, 95);
                                }else if(order.get("ACCURATE_ADDRESS").toString().length()<=24) {
                                    int length = order.get("ACCURATE_ADDRESS").toString().length();
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(0,11),10, 81);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(11,length), 10, 107);
                                }else if(order.get("ACCURATE_ADDRESS").toString().length()<=36){
                                    int length = order.get("ACCURATE_ADDRESS").toString().length();
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(0,11), 10, 75);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(11,23), 10, 95);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(23,length), 10, 115);
                                }else {
                                    int length = order.get("ACCURATE_ADDRESS").toString().length();
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(0,11), 10, 71);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(11,23), 10, 87);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(23,35), 10, 104);
                                    g2d.drawString(order.get("ACCURATE_ADDRESS").toString().substring(35,length), 10, 120);
                                }
                                g2d.drawString("----------------"+order.get("sf_id").toString()+"---------------------", 0, 135);
                                g2d.setFont(new Font("Default", Font.PLAIN, 7));
                                g2d.drawString(" 序号                   明细", 15, 140);
                                if(order.get("STAPLE_FOOD_1_NAME")!=null){
                                    g2d.drawString(order.get("STAPLE_FOOD_1").toString()+"     "+order.get("STAPLE_FOOD_1_NAME").toString(), 15, 149);
                                }
                                if(order.get("NON_STAPLE_FOOD_1_NAME")!=null){
                                    g2d.drawString(order.get("NON_STAPLE_FOOD_1").toString()+"     "+order.get("NON_STAPLE_FOOD_1_NAME").toString(), 15, 158);
                                }
                                if(order.get("NON_STAPLE_FOOD_1_NAME")!=null){
                                    g2d.drawString(order.get("NON_STAPLE_FOOD_2").toString()+"     "+order.get("NON_STAPLE_FOOD_2_NAME").toString(), 15, 167);
                                }
                                if(order.get("DRINKS_1_NAME")!=null){
                                    g2d.drawString(order.get("DRINKS_1").toString()+"     "+order.get("DRINKS_1_NAME").toString(), 15, 176);
                                }
                                g2d.drawString("-----------------------------------------------------------------------------", 0, 185);
                                g2d.setFont(new Font("Default", Font.PLAIN, 7));
                                if(order.get("SERVICE_TIME")!=null){
                                    g2d.drawString("送达时间   "+ finalServiceDate +" "+order.get("SERVICE_TIME").toString(), 20, 194);
                                }
                                return PAGE_EXISTS;
                            }
                        };
                        book.append(printable, pf);
                        // 获取打印服务对象
                        job.setPageable(book);
                        try {
                            job.print();
                            //打印完成,修改当前订单状态为配送中 将订单打印状态修改为已打印
                            orderDao.updateOrderStatus(order.get("ORDER_ID").toString());
                            orderDao.updateOrderItemStatus(order.get("ORDER_ID").toString());
                        } catch (PrinterException e) {
                            System.out.println("================打印出现异常");
                            resMap.put("code",-2);
                            resMap.put("msg","打印出现异常");
                            return resMap;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取订单列表异常 {}",e);
            resMap.put("code",-1);
            resMap.put("msg","获取订单列表异常");
            return resMap;
        }
        resMap.put("code",1);
        resMap.put("msg","操作成功");
        return resMap;
    }


    @Override
    public List<HashMap<String, Object>> orderCountExportExcel(HashMap map){
        try {
            List<HashMap<String,Object>> orderList = orderDao.queryOrderCountList(map);
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取订单汇总信息列表异常 {}",e);
            return null;
        }
    }

    @Override
    public Object queryGroupServiceList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> groupServiceList = orderDao.queryGroupServiceList(map);
            int groupServiceListNum = orderDao.queryGroupServiceListNum(map);
            resMap.put("rows",groupServiceList);
            resMap.put("total",groupServiceListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取团购列表异常 {}",e);
            resMap.put("msg","获取团购列表异常");
        }
        return resMap;
    }

    @Override
    public Object queryGroupServiceInfoList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> groupServiceInfoList = orderDao.queryGroupServiceInfoList(map);
            int groupServiceInfoListNum = orderDao.queryGroupServiceInfoListNum(map);
            resMap.put("rows",groupServiceInfoList);
            resMap.put("total",groupServiceInfoListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取团购详情列表异常 {}",e);
            resMap.put("msg","获取团购详情列表异常");
        }
        return resMap;
    }
}

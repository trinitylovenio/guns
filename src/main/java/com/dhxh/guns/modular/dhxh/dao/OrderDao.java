package com.dhxh.guns.modular.dhxh.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 管理员的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface OrderDao {

    /**
     * 查询订单列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryOrderList(HashMap map);

    /**
     * 查询订单总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryOrderListNum(HashMap map);

    /**
     * 查询订单汇总列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryOrderCountList(HashMap map);

    /**
     * 打印配送单后修改订单状态
     *
     * @param order_id
     * @date 2019/03/22
     */
    void updateOrderStatus(String order_id);
    /**
     * 打印配送单后修改订单详情状态
     *
     * @param order_id
     * @date 2019/03/22
     */
    void updateOrderItemStatus(String order_id);

    /**
     * 打印时获取服务日期
     * @return
     */
    String getServiceDate(int day);

    /**
     * 查询团购信息列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryGroupServiceList(HashMap map);

    /**
     * 查询团购信息总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryGroupServiceListNum(HashMap map);

    /**
     * 查询团购详情信息列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryGroupServiceInfoList(HashMap map);

    /**
     * 查询团购信息总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryGroupServiceInfoListNum(HashMap map);


    /**
     * 查询包月信息列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryPersonalServiceList(HashMap map);

    /**
     * 查询包月信息总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryPersonalServiceListNum(HashMap map);
}

package com.dhxh.guns.modular.dhxh.service;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface OrderService {

    /**
     * 按条件获取配送信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryorderList(HashMap map);

    /**
     * 按条件获取订单汇总信息（厨房用）
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryOrderCountList(HashMap map);

    /**
     * 导出订单信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    List<HashMap<String, Object>> exportExcel(HashMap map);

    /**
     * 导出订单汇总信息（厨房用）
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    List<HashMap<String, Object>> orderCountExportExcel(HashMap map);

    /**
     * 打印订单
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object prientOrder(HashMap map);


    /**
     * 按条件获取团购
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryGroupServiceList(HashMap map);

    /**
     * 获取团购详情
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryGroupServiceInfoList(HashMap map);

}

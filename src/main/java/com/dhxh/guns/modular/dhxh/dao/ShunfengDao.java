package com.dhxh.guns.modular.dhxh.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 顺丰订单
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface ShunfengDao {

    /**
     * 查询订单列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryShunfengOrderList(HashMap map);

    /**
     * 查询订单总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryShunfengOrderListNum(HashMap map);

    /**
     * 修改顺丰订单
     *
     * @param map
     * @date 2019/03/22
     */
    int updateShunfengOrder(HashMap map);


}

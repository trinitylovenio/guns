package com.dhxh.guns.modular.dhxh.service;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface FoodService {

    /**
     * 获取食物列表
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryFoodList(HashMap map);

    /**
     * 获取菜单列表
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryCarteList(HashMap map);

    /**
     * 获取推荐餐列表
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryRecommendList(HashMap map);

    /**
     * 添加食物
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object mergeFood(HashMap map);

    /**
     * 添加菜单
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object mergeCarte(HashMap map);

    /**
     * 添加套餐
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object mergeStandardRecommend(HashMap map);

    /**
     * 检查日期
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object checkDate(HashMap map);

}

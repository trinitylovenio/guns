package com.dhxh.guns.modular.dhxh.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 管理员的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface FoodDao {

    /**
     * 查询food列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryFoodList(HashMap map);

    /**
     * 查询food总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryFoodListNum(HashMap map);

    /**
     * 查询菜单列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryCarteList(HashMap map);

    /**
     * 查询菜单列表总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryCarteListNum(HashMap map);

    /**
     * 查询推荐餐列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryRecommendList(HashMap map);

    /**
     * 查询制定推荐餐列表
     *
     * @param STANDARD_ID
     * @date 2019/03/22
     */
    HashMap<String,Object> queryRecommendListByID(String STANDARD_ID);

    /**
     * 查询推荐餐总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryRecommendListNum(HashMap map);

    /**
     * 插入food
     *
     * @param map
     * @date 2019/03/22
     */
    int mergeFood(HashMap map);

    /**
     * 获取当前最大FOOD_ID
     * @date 2019/03/22
     */
    String getMaxFoodID();

    HashMap getFoodByFoodID(String FOOD_ID);

    /**
     * 获取当前最大CARTE_ID
     * @date 2019/03/22
     */
    String getMaxCarteID();

    /**
     * 插入Carte
     *
     * @param map
     * @date 2019/03/22
     */
    int mergeCarte(HashMap map);


    /**
     * 获取当前最大STANDARD_ID
     * @date 2019/03/22
     */
    String getMaxStandardID();

    /**
     * 插入StandardRecommend
     *
     * @param map
     * @date 2019/03/22
     */
    int mergeStandardRecommend(HashMap map);

    /**
     * 检查日期
     *
     * @param map
     * @date 2019/03/22
     */
    int checkDate(HashMap map);
}

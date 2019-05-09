package com.dhxh.guns.modular.dhxh.service.Impl;

import com.dhxh.guns.modular.dhxh.dao.FoodDao;
import com.dhxh.guns.modular.dhxh.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FoodServiceImpl implements FoodService {

    @Resource
    FoodDao foodDao;

    private static final Logger LOG = LoggerFactory.getLogger(FoodServiceImpl.class);

    @Override
    public Object queryFoodList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> foodList= foodDao.queryFoodList(map);
            int foodListNum= foodDao.queryFoodListNum(map);
            resMap.put("rows",foodList);
            resMap.put("total",foodListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取食物列表异常 {}",e);
            resMap.put("msg","获取食物列表异常");
        }
        return resMap;
    }

    @Override
    public Object queryCarteList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> carteList= foodDao.queryCarteList(map);
            int carteListNum= foodDao.queryCarteListNum(map);
            if(carteList!=null && carteList.size()>0){
                for(HashMap<String,Object> carte:carteList){
                    if(carte.get("set_meal_standard")!=null){
                        HashMap<String, Object> set_meal_standard = foodDao.queryRecommendListByID(carte.get("set_meal_standard").toString());
                        if(set_meal_standard!=null){
                            carte.put("set_meal_standard",set_meal_standard);
                        }
                    }
                    if(carte.get("set_meal_1")!=null){
                        HashMap<String, Object> set_meal_1 = foodDao.queryRecommendListByID(carte.get("set_meal_1").toString());
                        if(set_meal_1!=null){
                            carte.put("set_meal_1",set_meal_1);
                        }
                    }
                    if(carte.get("set_meal_2")!=null){
                        HashMap<String, Object> set_meal_2 = foodDao.queryRecommendListByID(carte.get("set_meal_2").toString());
                        if(set_meal_2!=null){
                            carte.put("set_meal_2",set_meal_2);
                        }
                    }
                    if(carte.get("set_meal_3")!=null){
                        HashMap<String, Object> set_meal_3 = foodDao.queryRecommendListByID(carte.get("set_meal_3").toString());
                        if(set_meal_3!=null){
                            carte.put("set_meal_3",set_meal_3);
                        }
                    }

                }
            }
            resMap.put("rows",carteList);
            resMap.put("total",carteListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取菜单列表异常 {}",e);
            resMap.put("msg","获取菜单列表异常");
        }
        return resMap;
    }

    @Override
    public Object queryRecommendList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> recommendList= foodDao.queryRecommendList(map);
            int recommendListNum= foodDao.queryRecommendListNum(map);
            resMap.put("rows",recommendList);
            resMap.put("total",recommendListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取推荐餐列表异常 {}",e);
            resMap.put("msg","获取推荐餐表异常");
        }
        return resMap;
    }

    @Override
    public Object mergeFood(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        if(map.get("FOOD_ID")==null || "".equals(map.get("FOOD_ID").toString())){
            map.put("FOOD_ID",foodDao.getMaxFoodID());
        }
        int count = 0;
        count = foodDao.mergeFood(map);
        if(count>0){
            resMap.put("code",1);
            resMap.put("msg","操作成功");
        }else {
            resMap.put("code",-1);
            resMap.put("msg","操作失败");
        }
        return resMap;
    }

    @Override
    public Object mergeCarte(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        if(map.get("CARTE_ID")==null || "".equals(map.get("CARTE_ID").toString())){
            map.put("CARTE_ID",foodDao.getMaxCarteID());
        }
        int count = 0;
        count = foodDao.mergeCarte(map);
        if(count>0){
            resMap.put("code",1);
            resMap.put("msg","操作成功");
        }else {
            resMap.put("code",-1);
            resMap.put("msg","操作失败");
        }
        return resMap;
    }

    @Override
    public Object mergeStandardRecommend(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        if(map.get("STANDARD_ID")==null || "".equals(map.get("STANDARD_ID").toString())){
            map.put("STANDARD_ID",foodDao.getMaxStandardID());
        }
        int count = 0;
        count = foodDao.mergeStandardRecommend(map);
        if(count>0){
            resMap.put("code",1);
            resMap.put("msg","操作成功");
        }else {
            resMap.put("code",-1);
            resMap.put("msg","操作失败");
        }
        return resMap;
    }

    @Override
    public Object checkDate(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        int count = 0;
        count = foodDao.checkDate(map);
        if(count>0){
            resMap.put("code",1);
            resMap.put("msg","校验通过");
        }else {
            resMap.put("code",-1);
            resMap.put("msg","校验未通过,不能修改,请检查日期");
        }
        return resMap;
    }
}

package com.dhxh.guns.modular.dhxh.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhxh.guns.modular.dhxh.bean.ShunfengOrderParam;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhy on 2017/12/20.
 */
@Service
public class SmallTools {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getTime(){
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        return format;
    }


    /**
     * 随机生成制定长度编码
     *
     * @return
     */
    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }


    /**
     * 随机生成ID
     *
     * @return
     */
    public static String get32RandomString(){
        int length = 32;
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * 随机生成ID
     *
     * @return
     */
    public static String get16RandomString(){
        int length = 16;
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * 字符串判空判空
     *
     * @return
     */
    public static boolean StringIsEmpty(String str) {
        if(str == null || str.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * HashMap<String, Object>判空判空
     *
     * @return
     */
    public static boolean HashMapIsEmpty(HashMap<String, Object> map) {
        if(map == null || map.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * List<HashMap<String, Object>>判空判空
     *
     * @return
     */
    public static boolean ListIsEmpty(List<HashMap<String, Object>> list) {
        if(list == null || list.isEmpty() || list.size()<=0){
            return true;
        }else {
            return false;
        }
    }

    /***
     * hashMap 转json
     * @return
     */
    public static JSONObject HashMapToJSONObject(HashMap map) {
        JSONObject jo = new JSONObject();
        for(Iterator iter = map.entrySet().iterator(); iter.hasNext();)
        {
            Map.Entry entry = (Map.Entry)iter.next();
            jo.put((String)entry.getKey(),entry.getValue());
        }
        return jo;
    }

    /***
     * Map 转json
     * @return
     */
    public static JSONObject MapToJSONObject(Map<String, Object>  map) {
        JSONObject jo = new JSONObject();
        for (String key : map.keySet()) {
            jo.put(key,map.get(key));
        }
        return jo;
    }

    /***
     * list 转 JSONArray
     * @return
     */
    public static JSONArray ListToJSONArray(List<HashMap<String, Object>>  list) {
        JSONArray array = new JSONArray();
        //遍历list组装成json格式
        for(HashMap map:list){
            JSONObject jo = HashMapToJSONObject(map);
            array.add(jo);
        }
        return array;
    }

//
//    public static JSONArray CarteToJson(HashMap<String, Object> Carte){
//        JSONArray carteArray = new JSONArray();
//        JSONObject staple_food_1 =new JSONObject();
//        staple_food_1.put("type",1);
//        staple_food_1.put("title","主食");
//        JSONArray staple_food_1_array = new JSONArray();
//        JSONObject non_staple_food_1 =new JSONObject();
//        non_staple_food_1.put("type",2);
//        non_staple_food_1.put("title","副食1");
//        JSONArray non_staple_food_1_array = new JSONArray();
//        JSONObject non_staple_food_2 =new JSONObject();
//        non_staple_food_2.put("type",3);
//        non_staple_food_2.put("title","副食2");
//        JSONArray non_staple_food_2_array = new JSONArray();
//        JSONObject drinks_1 =new JSONObject();
//        drinks_1.put("type",4);
//        drinks_1.put("title","饮料");
//        JSONArray drinks_1_array = new JSONArray();
//        JSONObject snacks_1 =new JSONObject();
//        snacks_1.put("type",5);
//        snacks_1.put("title","赠品");
//        JSONArray snacks_1_array = new JSONArray();
//
//        //主食1 3个备选
//        if(Carte.get("staple_food_1_a_id")!=null){
//            JSONObject staple_food_1_a=new JSONObject();
//            staple_food_1_a.put("type",1);
//            staple_food_1_a.put("id",Carte.get("staple_food_1_a_id"));
//            staple_food_1_a.put("name",Carte.get("staple_food_1_a_name"));
//            staple_food_1_a.put("des",Carte.get("staple_food_1_a_des"));
//            staple_food_1_a.put("img",Carte.get("staple_food_1_a_img"));
//            staple_food_1_a.put("order_num",Carte.get("staple_food_1_a_order_num"));
//            staple_food_1_array.add(staple_food_1_a);
//        }
//        if(Carte.get("staple_food_1_b_id")!=null){
//            JSONObject staple_food_1_b=new JSONObject();
//            staple_food_1_b.put("type",1);
//            staple_food_1_b.put("id",Carte.get("staple_food_1_b_id"));
//            staple_food_1_b.put("name",Carte.get("staple_food_1_b_name"));
//            staple_food_1_b.put("des",Carte.get("staple_food_1_b_des"));
//            staple_food_1_b.put("img",Carte.get("staple_food_1_b_img"));
//            staple_food_1_b.put("order_num",Carte.get("staple_food_1_b_order_num"));
//            staple_food_1_array.add(staple_food_1_b);
//        }
//        if(Carte.get("staple_food_1_c_id")!=null){
//            JSONObject staple_food_1_c=new JSONObject();
//            staple_food_1_c.put("type",1);
//            staple_food_1_c.put("id",Carte.get("staple_food_1_c_id"));
//            staple_food_1_c.put("name",Carte.get("staple_food_1_c_name"));
//            staple_food_1_c.put("des",Carte.get("staple_food_1_c_des"));
//            staple_food_1_c.put("img",Carte.get("staple_food_1_c_img"));
//            staple_food_1_c.put("order_num",Carte.get("staple_food_1_c_order_num"));
//            staple_food_1_array.add(staple_food_1_c);
//        }
////        carteArray.put("staple_food_1",staple_food_1);
//        staple_food_1.put("data",staple_food_1_array);
//        carteArray.add(staple_food_1);
//
//        //副食1 3个备选
//        if(Carte.get("non_staple_food_1_a_id")!=null){
//            JSONObject non_staple_food_1_a=new JSONObject();
//            non_staple_food_1_a.put("type",2);
//            non_staple_food_1_a.put("id",Carte.get("non_staple_food_1_a_id"));
//            non_staple_food_1_a.put("name",Carte.get("non_staple_food_1_a_name"));
//            non_staple_food_1_a.put("des",Carte.get("non_staple_food_1_a_des"));
//            non_staple_food_1_a.put("img",Carte.get("non_staple_food_1_a_img"));
//            non_staple_food_1_a.put("order_num",Carte.get("non_staple_food_1_a_order_num"));
//            non_staple_food_1_array.add(non_staple_food_1_a);
//        }
//        if(Carte.get("non_staple_food_1_b_id")!=null){
//            JSONObject non_staple_food_1_b=new JSONObject();
//            non_staple_food_1_b.put("type",2);
//            non_staple_food_1_b.put("id",Carte.get("non_staple_food_1_b_id"));
//            non_staple_food_1_b.put("name",Carte.get("non_staple_food_1_b_name"));
//            non_staple_food_1_b.put("des",Carte.get("non_staple_food_1_b_des"));
//            non_staple_food_1_b.put("img",Carte.get("non_staple_food_1_b_img"));
//            non_staple_food_1_b.put("order_num",Carte.get("non_staple_food_1_b_order_num"));
//            non_staple_food_1_array.add(non_staple_food_1_b);
//        }
//        if(Carte.get("non_staple_food_1_c_id")!=null){
//            JSONObject non_staple_food_1_c=new JSONObject();
//            non_staple_food_1_c.put("type",2);
//            non_staple_food_1_c.put("id",Carte.get("non_staple_food_1_c_id"));
//            non_staple_food_1_c.put("name",Carte.get("non_staple_food_1_c_name"));
//            non_staple_food_1_c.put("des",Carte.get("non_staple_food_1_c_des"));
//            non_staple_food_1_c.put("img",Carte.get("non_staple_food_1_c_img"));
//            non_staple_food_1_c.put("order_num",Carte.get("non_staple_food_1_c_order_num"));
//            non_staple_food_1_array.add(non_staple_food_1_c);
//            non_staple_food_1.put("data",non_staple_food_1_array);
//        }
//        carteArray.add(non_staple_food_1);
//
//        //副食2 3个备选
//        if(Carte.get("non_staple_food_2_a_id")!=null){
//            JSONObject non_staple_food_2_a=new JSONObject();
//            non_staple_food_2_a.put("type",3);
//            non_staple_food_2_a.put("id",Carte.get("non_staple_food_2_a_id"));
//            non_staple_food_2_a.put("name",Carte.get("non_staple_food_2_a_name"));
//            non_staple_food_2_a.put("des",Carte.get("non_staple_food_2_a_des"));
//            non_staple_food_2_a.put("img",Carte.get("non_staple_food_2_a_img"));
//            non_staple_food_2_a.put("order_num",Carte.get("non_staple_food_2_a_order_num"));
//            non_staple_food_2_array.add(non_staple_food_2_a);
//        }
//
//        if(Carte.get("non_staple_food_2_b_id")!=null){
//            JSONObject non_staple_food_2_b=new JSONObject();
//            non_staple_food_2_b.put("type",3);
//            non_staple_food_2_b.put("id",Carte.get("non_staple_food_2_b_id"));
//            non_staple_food_2_b.put("name",Carte.get("non_staple_food_2_b_name"));
//            non_staple_food_2_b.put("des",Carte.get("non_staple_food_2_b_des"));
//            non_staple_food_2_b.put("img",Carte.get("non_staple_food_2_b_img"));
//            non_staple_food_2_b.put("order_num",Carte.get("non_staple_food_2_b_order_num"));
//            non_staple_food_2_array.add(non_staple_food_2_b);
//        }
//
//        if(Carte.get("non_staple_food_2_c_id")!=null){
//            JSONObject non_staple_food_2_c=new JSONObject();
//            non_staple_food_2_c.put("type",3);
//            non_staple_food_2_c.put("id",Carte.get("non_staple_food_2_c_id"));
//            non_staple_food_2_c.put("name",Carte.get("non_staple_food_2_c_name"));
//            non_staple_food_2_c.put("des",Carte.get("non_staple_food_2_c_des"));
//            non_staple_food_2_c.put("img",Carte.get("non_staple_food_2_c_img"));
//            non_staple_food_2_c.put("order_num",Carte.get("non_staple_food_2_c_order_num"));
//            non_staple_food_2_array.add(non_staple_food_2_c);
//            non_staple_food_2.put("data",non_staple_food_2_array);
//        }
//        carteArray.add(non_staple_food_2);
//
//        //饮料1 3个备选
//        if(Carte.get("drinks_1_a_id")!=null){
//            JSONObject drinks_1_a=new JSONObject();
//            drinks_1_a.put("type",4);
//            drinks_1_a.put("id",Carte.get("drinks_1_a_id"));
//            drinks_1_a.put("name",Carte.get("drinks_1_a_name"));
//            drinks_1_a.put("des",Carte.get("drinks_1_a_des"));
//            drinks_1_a.put("img",Carte.get("drinks_1_a_img"));
//            drinks_1_a.put("order_num",Carte.get("drinks_1_a_order_num"));
//            drinks_1_array.add(drinks_1_a);
//        }
//
//        if(Carte.get("drinks_1_b_id")!=null){
//            JSONObject drinks_1_b=new JSONObject();
//            drinks_1_b.put("type",4);
//            drinks_1_b.put("id",Carte.get("drinks_1_b_id"));
//            drinks_1_b.put("name",Carte.get("drinks_1_b_name"));
//            drinks_1_b.put("des",Carte.get("drinks_1_b_des"));
//            drinks_1_b.put("img",Carte.get("drinks_1_b_img"));
//            drinks_1_b.put("order_num",Carte.get("drinks_1_b_order_num"));
//            drinks_1_array.add(drinks_1_b);
//        }
//
//        if(Carte.get("drinks_1_c_id")!=null){
//            JSONObject drinks_1_c=new JSONObject();
//            drinks_1_c.put("type",4);
//            drinks_1_c.put("id",Carte.get("drinks_1_c_id"));
//            drinks_1_c.put("name",Carte.get("drinks_1_c_name"));
//            drinks_1_c.put("des",Carte.get("drinks_1_c_des"));
//            drinks_1_c.put("img",Carte.get("drinks_1_c_img"));
//            drinks_1_c.put("order_num",Carte.get("drinks_1_c_order_num"));
//            drinks_1_array.add(drinks_1_c);
//            drinks_1.put("data",drinks_1_array);
//        }
//        carteArray.add(drinks_1);
//
//        //零食1 3个备选
//        if(Carte.get("snacks_1_a_id")!=null){
//            JSONObject snacks_1_a=new JSONObject();
//            snacks_1_a.put("type",5);
//            snacks_1_a.put("id",Carte.get("snacks_1_a_id"));
//            snacks_1_a.put("name",Carte.get("snacks_1_a_name"));
//            snacks_1_a.put("des",Carte.get("snacks_1_a_des"));
//            snacks_1_a.put("img",Carte.get("snacks_1_a_img"));
//            snacks_1_a.put("order_num",Carte.get("snacks_1_a_order_num"));
//            snacks_1_array.add(snacks_1_a);
//            snacks_1.put("data",snacks_1_array);
//        }
//        carteArray.add(snacks_1);
//
//        return carteArray;
//    }
//
////    public static JSONArray RecommendToJson(HashMap<String, Object> Recommend){
////        JSONArray recommendArray = new JSONArray();
////
////
////
////        //主食1
////        if(Recommend.get("staple_food_1_id")!=null){
////            JSONObject staple_food_1 =new JSONObject();
////            staple_food_1.put("type",1);
////            staple_food_1.put("title","主食");
////            staple_food_1.put("id",Recommend.get("staple_food_1_id"));
////            staple_food_1.put("name",Recommend.get("staple_food_1_name"));
////            staple_food_1.put("des",Recommend.get("staple_food_1_des"));
////            staple_food_1.put("img",Recommend.get("staple_food_1_img"));
////            staple_food_1.put("order_num",Recommend.get("staple_food_1_order_num"));
////            recommendArray.add(staple_food_1);
////        }
////
////
////        //副食1
////        if(Recommend.get("non_staple_food_1_id")!=null){
////            JSONObject non_staple_food_1 =new JSONObject();
////            non_staple_food_1.put("type",2);
////            non_staple_food_1.put("title","副食1");
////            non_staple_food_1.put("id",Recommend.get("non_staple_food_1_id"));
////            non_staple_food_1.put("name",Recommend.get("non_staple_food_1_name"));
////            non_staple_food_1.put("des",Recommend.get("non_staple_food_1_des"));
////            non_staple_food_1.put("img",Recommend.get("non_staple_food_1_img"));
////            non_staple_food_1.put("order_num",Recommend.get("non_staple_food_1_order_num"));
////            recommendArray.add(non_staple_food_1);
////        }
////
////        //副食2
////        if(Recommend.get("non_staple_food_2_id")!=null){
////            JSONObject non_staple_food_2 =new JSONObject();
////            non_staple_food_2.put("type",3);
////            non_staple_food_2.put("title","副食2");
////            non_staple_food_2.put("id",Recommend.get("non_staple_food_2_id"));
////            non_staple_food_2.put("name",Recommend.get("non_staple_food_2_name"));
////            non_staple_food_2.put("des",Recommend.get("non_staple_food_2_des"));
////            non_staple_food_2.put("img",Recommend.get("non_staple_food_2_img"));
////            non_staple_food_2.put("order_num",Recommend.get("non_staple_food_2_order_num"));
////            recommendArray.add(non_staple_food_2);
////        }
////
////        //饮料1
////        if(Recommend.get("drinks_1_id")!=null){
////            JSONObject drinks_1 =new JSONObject();
////            drinks_1.put("type",4);
////            drinks_1.put("title","饮料");
////            drinks_1.put("id",Recommend.get("drinks_1_id"));
////            drinks_1.put("name",Recommend.get("drinks_1_name"));
////            drinks_1.put("des",Recommend.get("drinks_1_des"));
////            drinks_1.put("img",Recommend.get("drinks_1_img"));
////            drinks_1.put("order_num",Recommend.get("drinks_1_order_num"));
////            recommendArray.add(drinks_1);
////        }
////
////        //饮料1
////        if(Recommend.get("snacks_1_id")!=null){
////            JSONObject snacks_1 =new JSONObject();
////            snacks_1.put("type",5);
////            snacks_1.put("title","赠品");
////            snacks_1.put("id",Recommend.get("snacks_1_id"));
////            snacks_1.put("name",Recommend.get("snacks_1_name"));
////            snacks_1.put("des",Recommend.get("snacks_1_des"));
////            snacks_1.put("img",Recommend.get("snacks_1_img"));
////            snacks_1.put("order_num",Recommend.get("snacks_1_order_num"));
////            recommendArray.add(snacks_1);
////        }
////
////        return recommendArray;
////    }
////
////    public static JSONArray EvaluateToJson(HashMap<String, Object> Evaluate){
////        JSONArray evaluateArray = new JSONArray();
////
////
////        //主食1
////        if(Evaluate.get("staple_food_1_id")!=null){
////            JSONObject staple_food_1 =new JSONObject();
////            staple_food_1.put("type",1);
////            staple_food_1.put("title","主食");
////            staple_food_1.put("id",Evaluate.get("staple_food_1_id"));
////            staple_food_1.put("name",Evaluate.get("staple_food_1_name"));
////            staple_food_1.put("des",Evaluate.get("staple_food_1_des"));
////            staple_food_1.put("img",Evaluate.get("staple_food_1_img"));
////            staple_food_1.put("score",Evaluate.get("staple_food_1_score"));
////            evaluateArray.add(staple_food_1);
////        }
////
////        //副食1
////        if(Evaluate.get("non_staple_food_1_id")!=null){
////            JSONObject non_staple_food_1 =new JSONObject();
////            non_staple_food_1.put("type",2);
////            non_staple_food_1.put("title","副食1");
////            non_staple_food_1.put("id",Evaluate.get("non_staple_food_1_id"));
////            non_staple_food_1.put("name",Evaluate.get("non_staple_food_1_name"));
////            non_staple_food_1.put("des",Evaluate.get("non_staple_food_1_des"));
////            non_staple_food_1.put("img",Evaluate.get("non_staple_food_1_img"));
////            non_staple_food_1.put("score",Evaluate.get("non_staple_food_1_score"));
////            evaluateArray.add(non_staple_food_1);
////        }
////
////        //副食2
////        if(Evaluate.get("non_staple_food_2_id")!=null){
////            JSONObject non_staple_food_2 =new JSONObject();
////            non_staple_food_2.put("type",3);
////            non_staple_food_2.put("title","副食2");
////            non_staple_food_2.put("id",Evaluate.get("non_staple_food_2_id"));
////            non_staple_food_2.put("name",Evaluate.get("non_staple_food_2_name"));
////            non_staple_food_2.put("des",Evaluate.get("non_staple_food_2_des"));
////            non_staple_food_2.put("img",Evaluate.get("non_staple_food_2_img"));
////            non_staple_food_2.put("score",Evaluate.get("non_staple_food_2_score"));
////            evaluateArray.add(non_staple_food_2);
////        }
////
////
////        //饮料1
////        if(Evaluate.get("drinks_1_id")!=null){
////            JSONObject drinks_1 =new JSONObject();
////            drinks_1.put("type",4);
////            drinks_1.put("title","饮料");
////            drinks_1.put("id",Evaluate.get("drinks_1_id"));
////            drinks_1.put("name",Evaluate.get("drinks_1_name"));
////            drinks_1.put("des",Evaluate.get("drinks_1_des"));
////            drinks_1.put("img",Evaluate.get("drinks_1_img"));
////            drinks_1.put("score",Evaluate.get("drinks_1_score"));
////            evaluateArray.add(drinks_1);
////        }
////
////        //零食1
////        if(Evaluate.get("snacks_1_id")!=null){
////            JSONObject snacks_1 =new JSONObject();
////            snacks_1.put("type",5);
////            snacks_1.put("title","赠品");
////            snacks_1.put("id",Evaluate.get("snacks_1_id"));
////            snacks_1.put("name",Evaluate.get("snacks_1_name"));
////            snacks_1.put("des",Evaluate.get("snacks_1_des"));
////            snacks_1.put("img",Evaluate.get("snacks_1_img"));
////            snacks_1.put("score",Evaluate.get("snacks_1_score"));
////            evaluateArray.add(snacks_1);
////        }
////
////        return evaluateArray;
////    }
}

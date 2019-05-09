package com.dhxh.guns.modular.dhxh.controller;

import com.dhxh.guns.common.controller.BaseController;
import com.dhxh.guns.common.exception.BizExceptionEnum;
import com.dhxh.guns.common.exception.BussinessException;
import com.dhxh.guns.core.util.ToolUtil;
import com.dhxh.guns.modular.dhxh.dao.FoodDao;
import com.dhxh.guns.modular.dhxh.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

//import com.dhxh.guns.modular.dhxh.utils.FileUtil;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/food")
public class FoodController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(FoodController.class);

    private static String PREFIX = "/dhxh/food";

    @Resource
    FoodService foodService;

    @Resource
    FoodDao foodDao;

    /**
     * 跳转到食物管理
     */
    @RequestMapping("/foodPage")
    public String foodPage() {
        return PREFIX + "/food.html";
    }

    /**
     * 跳转到用推荐餐管理
     */
    @RequestMapping("/foodAddPage")
    public String foodAddPage() {
        return PREFIX + "/food_add.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/foodEditPage/{FOOD_ID}")
    public String userEdit(@PathVariable String FOOD_ID, Model model) {
        if (ToolUtil.isEmpty(FOOD_ID)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap<String,Object> food = foodDao.getFoodByFoodID(FOOD_ID);
        model.addAttribute("FOOD_ID",food.get("FOOD_ID").toString());
        model.addAttribute("FOOD_NAME",food.get("FOOD_NAME").toString());
        model.addAttribute("FOOD_IMG",food.get("FOOD_IMG").toString());
        model.addAttribute("FOOD_DES",food.get("FOOD_DES").toString());
        model.addAttribute("FOOD_TYPE",food.get("FOOD_TYPE").toString());
        return PREFIX + "/food_edit.html";
    }

    /**
     * 跳转到菜单管理
     */
    @RequestMapping("/cartePage")
    public String orderCountPage() {
        return PREFIX + "/carte.html";
    }


    /**
     * 跳转到用推荐餐管理
     */
    @RequestMapping("/carteAddPage")
    public String carteAddPage() {
        return PREFIX + "/carte_add.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/carteEditPage/{CARTE_ID}")
    public String carteEditPage(@PathVariable String CARTE_ID, Model model) {
        if (ToolUtil.isEmpty(CARTE_ID)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap map = new HashMap();
        map.put("CARTE_ID",CARTE_ID);
        List<HashMap<String,Object>> carteList = foodDao.queryCarteList(map);
        model.addAttribute("set_meal_standard",carteList.get(0).get("set_meal_standard")==null?"":carteList.get(0).get("set_meal_standard").toString());
        model.addAttribute("set_meal_1",carteList.get(0).get("set_meal_1")==null?"":carteList.get(0).get("set_meal_1").toString());
        model.addAttribute("set_meal_2",carteList.get(0).get("set_meal_2")==null?"":carteList.get(0).get("set_meal_2").toString());
        model.addAttribute("set_meal_3",carteList.get(0).get("set_meal_3")==null?"":carteList.get(0).get("set_meal_3").toString());
        model.addAttribute("CARTE_ID",CARTE_ID);
        return PREFIX + "/carte_edit.html";
    }

    /**
     * 跳转到用推荐餐管理
     */
    @RequestMapping("/standradRecommendPage")
    public String userPage() {
        return PREFIX + "/standardRecommend.html";
    }

    /**
     * 跳转到用推荐餐管理
     */
    @RequestMapping("/standardRecommendAddPage")
    public String standardRecommendAddPage() {
        return PREFIX + "/standardRecommend_add.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/standardRecommendEditPage/{STANDARD_ID}")
    public String standardRecommendEditPage(@PathVariable String STANDARD_ID, Model model) {
        if (ToolUtil.isEmpty(STANDARD_ID)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap map = new HashMap();
        map.put("STANDARD_ID",STANDARD_ID);
        List<HashMap<String,Object>> standardRecommendList = foodDao.queryRecommendList(map);
        model.addAttribute("standard_name",standardRecommendList.get(0).get("standard_name").toString());
        model.addAttribute("standard_desc",standardRecommendList.get(0).get("standard_desc").toString());
        model.addAttribute("standard_img",standardRecommendList.get(0).get("standard_img").toString());

        model.addAttribute("STAPLE_FOOD_1",standardRecommendList.get(0).get("STAPLE_FOOD_1").toString());
        model.addAttribute("NON_STAPLE_FOOD_1",standardRecommendList.get(0).get("NON_STAPLE_FOOD_1").toString());
        model.addAttribute("NON_STAPLE_FOOD_2",standardRecommendList.get(0).get("NON_STAPLE_FOOD_2").toString());
        model.addAttribute("DRINKS_1",standardRecommendList.get(0).get("DRINKS_1").toString());
        model.addAttribute("SNACKS_1",standardRecommendList.get(0).get("SNACKS_1").toString());
        model.addAttribute("STAPLE_FOOD_1_NAME",standardRecommendList.get(0).get("STAPLE_FOOD_1_NAME").toString());
        model.addAttribute("NON_STAPLE_FOOD_1_NAME",standardRecommendList.get(0).get("NON_STAPLE_FOOD_1_NAME").toString());
        model.addAttribute("NON_STAPLE_FOOD_2_NAME",standardRecommendList.get(0).get("NON_STAPLE_FOOD_2_NAME").toString());
        model.addAttribute("DRINKS_1_NAME",standardRecommendList.get(0).get("DRINKS_1_NAME").toString());
        model.addAttribute("SNACKS_1_NAME",standardRecommendList.get(0).get("SNACKS_1_NAME").toString());
//        model.addAttribute("DATE",standardRecommendList.get(0).get("DATE").toString());
        return PREFIX + "/standardRecommend_edit.html";
    }

    /**
     * 获取食物列表
     */
    @RequestMapping(value = "/foodList")
    @ResponseBody
    public Object foodList(@RequestParam(required = false) HashMap map) {
        return foodService.queryFoodList(map);
    }

    /**
     * 获取菜单列表
     */
    @RequestMapping(value = "/carteList")
    @ResponseBody
    public Object carteList(@RequestParam(required = false) HashMap map) {
        return foodService.queryCarteList(map);
    }

    /**
     * 获取推荐餐列表
     */
    @RequestMapping(value = "/standardRecommendList")
    @ResponseBody
    public Object recommendList(@RequestParam(required = false) HashMap map) {
        return foodService.queryRecommendList(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/mergeFood")
    @ResponseBody
    public Object mergeFood(@RequestParam(required = false) HashMap map) {
        return foodService.mergeFood(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/mergeCarte")
    @ResponseBody
    public Object mergeCarte(@RequestParam(required = false) HashMap map) {
        return foodService.mergeCarte(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/mergeStandardRecommend")
    @ResponseBody
    public Object mergeStandardRecommend(@RequestParam(required = false) HashMap map) {
        return foodService.mergeStandardRecommend(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/checkDate")
    @ResponseBody
    public Object checkDate(@RequestParam(required = false) HashMap map) {
        return foodService.checkDate(map);
    }

}

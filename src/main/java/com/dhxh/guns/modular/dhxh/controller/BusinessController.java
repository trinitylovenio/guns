package com.dhxh.guns.modular.dhxh.controller;

import com.dhxh.guns.common.controller.BaseController;
import com.dhxh.guns.common.exception.BizExceptionEnum;
import com.dhxh.guns.common.exception.BussinessException;
import com.dhxh.guns.core.util.ToolUtil;
import com.dhxh.guns.modular.dhxh.dao.BusinessDao;
import com.dhxh.guns.modular.dhxh.service.BusinessService;
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


/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/business")
public class BusinessController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BusinessController.class);

    private static String PREFIX = "/dhxh/business";

    @Resource
    BusinessService businessService;

    @Resource
    BusinessDao businessDao;

    /**
     * 跳转到订单管理
     */
    @RequestMapping("/businessInfoPage")
    public String businessInfoPage() {
        return PREFIX + "/businessInfo.html";
    }

    /**
     * 获取企业账户列表
     */
    @RequestMapping(value = "/businessInfoList")
    @ResponseBody
    public Object businessInfoList(@RequestParam(required = false) HashMap map){
        return businessService.queryBusinessInfoList(map);
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/addAccount/{id}")
    public String addAccount(@PathVariable String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap<String,Object> businessInfo = businessDao.queryBusinessInfoById(id);
        model.addAttribute("id",businessInfo.get("id").toString());
        model.addAttribute("business_name",businessInfo.get("business_name").toString());
        model.addAttribute("contact",businessInfo.get("contact").toString());
        model.addAttribute("telephone",businessInfo.get("telephone").toString());
        model.addAttribute("a1_id",businessInfo.get("a1_id").toString());
        model.addAttribute("a2_id",businessInfo.get("a2_id").toString());
        model.addAttribute("a1_name",businessInfo.get("a1_name").toString());
        model.addAttribute("a2_name",businessInfo.get("a2_name").toString());
        model.addAttribute("address_info",businessInfo.get("address_info")!=null?businessInfo.get("address_info").toString():"");
        return PREFIX + "/addAccount.html";
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/addSubmit")
    @ResponseBody
    public Object mergeAddress(@RequestParam(required = false) HashMap map) {
        return businessService.addSubmit(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/updateBusinessInfo")
    @ResponseBody
    public Object updateBusinessInfo(@RequestParam(required = false) HashMap map) {
        return businessService.updateBusinessInfo(map);
    }

    /**
     * 跳转到订单管理
     */
    @RequestMapping("/msPage")
    public String orderPage() {
        return PREFIX + "/monthlySubscription.html";
    }

    /**
     * 获取订单列表
     */
    @RequestMapping(value = "/MSList")
    @ResponseBody
    public Object MSList(@RequestParam(required = false) HashMap map) {

        return businessService.queryMSList(map);
    }

}

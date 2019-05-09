package com.dhxh.guns.modular.dhxh.controller;

import com.dhxh.guns.common.controller.BaseController;
import com.dhxh.guns.modular.dhxh.service.ShunfengService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/shunfeng")
public class ShunfengController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ShunfengController.class);

    private static String PREFIX = "/dhxh/shunfeng";

    @Resource
    ShunfengService shunfengService;

    /**
     * 跳转到订单管理
     */
    @RequestMapping("/shunfengPage")
    public String orderPage() {
        return PREFIX + "/shunfeng.html";
    }

    /**
     * 顺丰配送订单查询
     */
    @RequestMapping(value = "/shunfengList")
    @ResponseBody
    public Object shunfengList(@RequestParam(required = false) HashMap map) {
        return shunfengService.shunfengList(map);
    }

    /**
     * 顺丰配送订单下单
     */
    @RequestMapping(value = "/shunfengOrder")
    @ResponseBody
    public Object ShunfengOrder(@RequestParam(required = false) HashMap map) {
        return shunfengService.ShunfengOrder(map);
    }

}

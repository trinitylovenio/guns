package com.dhxh.guns.modular.dhxh.controller;

import com.dhxh.guns.common.controller.BaseController;
import com.dhxh.guns.common.exception.BizExceptionEnum;
import com.dhxh.guns.common.exception.BussinessException;
import com.dhxh.guns.core.util.ToolUtil;
import com.dhxh.guns.modular.dhxh.dao.OrderDao;
import com.dhxh.guns.modular.dhxh.service.OrderService;
import com.dhxh.guns.modular.dhxh.utils.FileUtil;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private static String PREFIX = "/dhxh/order";

    private static String PREFIX2 = "/dhxh/service";

    @Resource
    OrderService orderService;

    @Resource
    OrderDao orderDao;

    /**
     * 跳转到订单管理
     */
    @RequestMapping("/orderPage")
    public String orderPage() {
        return PREFIX + "/order.html";
    }

    /**
     * 跳转到订单汇总信息（厨房用）页面
     */
    @RequestMapping("/orderCountPage")
    public String orderCountPage() {
        return PREFIX + "/orderCount.html";
    }

    /**
     * 包月订单页面
     */
    @RequestMapping("/personalServicePage")
    public String personalServicePage() {
        return PREFIX2 + "/personalService.html";
    }

    /**
     * 团购订单页面
     */
    @RequestMapping("/groupServicePage")
    public String groupServicePage() {
        return PREFIX2 + "/groupService.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/groupServiceInfoPage/{id}")
    public String groupServiceInfoPage(@PathVariable String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap map = new HashMap();
        map.put("id",id);
        List<HashMap<String,Object>> groupServiceList = orderDao.queryGroupServiceList(map);
        model.addAttribute("head_name",groupServiceList.get(0).get("head_name")==null?"":groupServiceList.get(0).get("head_name").toString());
        model.addAttribute("head_phone",groupServiceList.get(0).get("head_phone")==null?"":groupServiceList.get(0).get("head_phone").toString());
        model.addAttribute("create_time",groupServiceList.get(0).get("create_time")==null?"":groupServiceList.get(0).get("create_time").toString());
        model.addAttribute("status",groupServiceList.get(0).get("status")==null?"":groupServiceList.get(0).get("status").toString());
        model.addAttribute("delivery_time",groupServiceList.get(0).get("delivery_time")==null?"":groupServiceList.get(0).get("delivery_time").toString());
        String name5 = "";
        String name6 = "";
        String address_info = "";
        String accurate_address = "";
        if(groupServiceList.get(0).get("name5")!=null){
            name5 = groupServiceList.get(0).get("name5").toString();
        }
        if(groupServiceList.get(0).get("name6")!=null){
            name6 = groupServiceList.get(0).get("name6").toString();
        }
        if(groupServiceList.get(0).get("address_info")!=null){
            if("大厦".equals(groupServiceList.get(0).get("delivery_type").toString())){
                address_info = groupServiceList.get(0).get("address_info").toString();
            }
        }
        if(groupServiceList.get(0).get("accurate_address")!=null){
            accurate_address = groupServiceList.get(0).get("accurate_address").toString();
        }
        if("".equals(accurate_address)){
            model.addAttribute("address",name5+name6+address_info);
        }else {
            model.addAttribute("address",name5+accurate_address+"("+name6+")"+address_info);
        }
        model.addAttribute("balance",groupServiceList.get(0).get("balance")==null?"":groupServiceList.get(0).get("balance").toString());
        model.addAttribute("team_num",groupServiceList.get(0).get("team_num")==null?"":groupServiceList.get(0).get("team_num").toString());
        model.addAttribute("id",id);
        return PREFIX2 + "/groupServiceInfo.html";
    }


    /**
     * 获取配送列表
     */
    @RequestMapping(value = "/orderList")
    @ResponseBody
    public Object orderList(@RequestParam(required = false) HashMap map) {
        return orderService.queryorderList(map);
    }

    /**
     * 获取订单汇总列表
     */
    @RequestMapping(value = "/orderCountList")
    @ResponseBody
    public Object orderCountList(@RequestParam(required = false) HashMap map) {
        return orderService.queryOrderCountList(map);
    }

    /**
     * 获取包月列表
     */
    @RequestMapping(value = "/personalServiceList")
    @ResponseBody
    public Object personalServiceList(@RequestParam(required = false) HashMap map) {
        return orderService.queryorderList(map);
    }

    /**
     * 获取团购列表
     */
    @RequestMapping(value = "/groupServiceList")
    @ResponseBody
    public Object groupServiceList(@RequestParam(required = false) HashMap map) {
        return orderService.queryGroupServiceList(map);
    }

    /**
     * 获取团购列表
     */
    @RequestMapping(value = "/groupServiceInfoList")
    @ResponseBody
    public Object groupServiceInfoList(@RequestParam(required = false) HashMap map) {
        return orderService.queryGroupServiceInfoList(map);
    }



    /**
     * 导出配送信息exportExcel
     */
    @RequestMapping(value = "/exportExcel/{beginTime}/{endTime}/{USER_NAME}/{user_type}/{PRINT_STATUS}/{SERVICE_TIME}" +
            "/{NAME5}/{NAME6}/{ACCURATE_ADDRESS}/{ORDER_STATUS}/{ORDER_TYPE}")
    @ResponseBody
    public void exportExcel(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable String USER_NAME,
                            @PathVariable String user_type, @PathVariable String PRINT_STATUS, @PathVariable String SERVICE_TIME,
                            @PathVariable String NAME5,@PathVariable String NAME6, @PathVariable String ACCURATE_ADDRESS,
                            @PathVariable String ORDER_STATUS,  @PathVariable String ORDER_TYPE,
                            HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("beginTime",beginTime);
        params.put("endTime",endTime);
        if(!"empty".equals(USER_NAME)){
            params.put("USER_NAME",USER_NAME);
        }
        if(!"empty".equals(user_type)){
            params.put("user_type",user_type);
        }
        if(!"empty".equals(PRINT_STATUS)){
            params.put("PRINT_STATUS",PRINT_STATUS);
        }
        if(!"empty".equals(SERVICE_TIME)){
            params.put("SERVICE_TIME",SERVICE_TIME);
        }
        if(!"empty".equals(NAME5)){
            params.put("NAME5",NAME5);
        }
        if(!"empty".equals(NAME6)){
            params.put("NAME6",NAME6);
        }
        if(!"empty".equals(ACCURATE_ADDRESS)){
            params.put("ACCURATE_ADDRESS",ACCURATE_ADDRESS);
        }
        if(!"empty".equals(ORDER_STATUS)){
            params.put("ORDER_STATUS",ORDER_STATUS);
        }
        if(!"empty".equals(ORDER_TYPE)){
            params.put("ORDER_TYPE",ORDER_TYPE);
        }
        List<HashMap<String, Object>> orderList = orderService.exportExcel(params);
        //生成Excel
        String name = beginTime.substring(0,10)+" to "+endTime.substring(0,10);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名称");
        row.createCell(1).setCellValue("用户电话");
//        row.createCell(2).setCellValue("市");
//        row.createCell(3).setCellValue("区");
        row.createCell(2).setCellValue("商圈");
        row.createCell(3).setCellValue("大厦/小区");
        row.createCell(4).setCellValue("公司");
        row.createCell(5).setCellValue("详细地址");
        row.createCell(6).setCellValue("送达时间");
        row.createCell(7).setCellValue("订单类型");
        row.createCell(8).setCellValue("订单状态");
        row.createCell(9).setCellValue("主食1");
        row.createCell(10).setCellValue("副食1");
        row.createCell(11).setCellValue("副食2");
        row.createCell(12).setCellValue("饮料1");
        row.createCell(13).setCellValue("小食1");
        row.createCell(14).setCellValue("下单时间");
        row.createCell(15).setCellValue("用户类型");
        row.createCell(16).setCellValue("订单状态");
        row.createCell(17).setCellValue("打印状态");

        int i=0;
        for (Map<String, Object> map:orderList) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(map.get("USER_NAME")==null?"-":map.get("USER_NAME").toString());
            row.createCell(1).setCellValue(map.get("TELEPHONE")==null?"-":map.get("TELEPHONE").toString());
            row.createCell(2).setCellValue(map.get("NAME4")==null?"-":map.get("NAME4").toString());
            row.createCell(3).setCellValue(map.get("NAME5")==null?"-":map.get("NAME5").toString());
            row.createCell(4).setCellValue(map.get("NAME6")==null?"-":map.get("NAME6").toString());
            row.createCell(5).setCellValue(map.get("ACCURATE_ADDRESS")==null?"-":map.get("ACCURATE_ADDRESS").toString());
            row.createCell(6).setCellValue(map.get("SERVICE_TIME")==null?"-":map.get("SERVICE_TIME").toString());
            row.createCell(7).setCellValue(map.get("ORDER_TYPE")==null?"-":map.get("ORDER_TYPE").toString());
            row.createCell(8).setCellValue(map.get("ORDER_STATUS")==null?"-":map.get("ORDER_STATUS").toString());
            row.createCell(9).setCellValue(map.get("STAPLE_FOOD_1_NAME")==null?"-":map.get("STAPLE_FOOD_1_NAME").toString());
            row.createCell(10).setCellValue(map.get("NON_STAPLE_FOOD_1_NAME")==null?"-":map.get("NON_STAPLE_FOOD_1_NAME").toString());
            row.createCell(11).setCellValue(map.get("NON_STAPLE_FOOD_2_NAME")==null?"-":map.get("NON_STAPLE_FOOD_2_NAME").toString());
            row.createCell(12).setCellValue(map.get("DRINKS_1_NAME")==null?"-":map.get("DRINKS_1_NAME").toString());
            row.createCell(13).setCellValue(map.get("SNACKS_1_NAME")==null?"-":map.get("SNACKS_1_NAME").toString());
            row.createCell(14).setCellValue(map.get("create_time")==null?"-":map.get("create_time").toString());
            row.createCell(15).setCellValue(map.get("user_type")==null?"-":map.get("user_type").toString());
            row.createCell(16).setCellValue(map.get("ORDER_STATUS")==null?"-":map.get("ORDER_STATUS").toString());
            row.createCell(17).setCellValue(map.get("print_status")==null?"-":map.get("print_status").toString());
            i++;
        }
        //生成临时Excel文件
        String path = request.getServletContext().getRealPath("/static");
        String fileName = "DhxhOrderData.xlsx";
        FileUtil.saveFile(workbook, path, fileName);
        //下载生成的Excel文件
        String filePath = path + "/" + fileName;
        FileUtil.downloadFile(filePath, response);
        //删除临时文件
        File tempFile = new File(filePath);
        boolean delete = tempFile.delete();
        LOG.debug("debug",delete);
    }

    /**
     * 导出订单汇总信息exportExcel
     */
    @RequestMapping(value = "/orderCountExportExcel/{beginTime}/{endTime}")
    @ResponseBody
    public void orderCountExportExcel(@PathVariable String beginTime, @PathVariable String endTime,
                                      HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("beginTime",beginTime);
        params.put("endTime",endTime);
        List<HashMap<String, Object>> orderList = orderService.orderCountExportExcel(params);
        //生成Excel
        String name = beginTime.substring(0,10)+" to "+endTime.substring(0,10);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("食品名称");
        row.createCell(1).setCellValue("总数");
        int i=0;
        for (Map<String, Object> map:orderList) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(map.get("FOOD_NAME")==null?"-":map.get("FOOD_NAME").toString());
            row.createCell(1).setCellValue(map.get("COUNT_NUM")==null?"-":map.get("COUNT_NUM").toString());
            i++;
        }
        //生成临时Excel文件
        String path = request.getServletContext().getRealPath("/static");
        String fileName = "DhxhOrderCountData.xlsx";
        FileUtil.saveFile(workbook, path, fileName);
        //下载生成的Excel文件
        String filePath = path + "/" + fileName;
        FileUtil.downloadFile(filePath, response);
        //删除临时文件
        File tempFile = new File(filePath);
        boolean delete = tempFile.delete();
        LOG.debug("debug",delete);
    }

    /**
     * 订单信息exportExcel
     */
    @RequestMapping(value = "/prientOrder")
    @ResponseBody
    public Object prientOrder(@RequestParam(required = false) HashMap map) {
        return orderService.prientOrder(map);
    }

}

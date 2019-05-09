package com.dhxh.guns.modular.dhxh.controller;

import com.dhxh.guns.common.controller.BaseController;
import com.dhxh.guns.common.exception.BizExceptionEnum;
import com.dhxh.guns.common.exception.BussinessException;
import com.dhxh.guns.common.node.ZTreeNode;
import com.dhxh.guns.core.util.ToolUtil;
import com.dhxh.guns.modular.dhxh.dao.UserDao;
import com.dhxh.guns.modular.dhxh.service.UserService;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static String PREFIX = "/dhxh";

    @Resource
    UserService userService;

    @Resource
    UserDao userDao;


    /**
     * 跳转到用户信息
     */
    @RequestMapping("/userPage")
    public String userPage() {
        return PREFIX + "/user/user.html";
    }

    /**
     * 跳转到用户信息
     */
    @RequestMapping("/addressPage")
    public String addressPage() {
        return PREFIX + "/user/address.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/addressAddPage")
    public String addAdd() {
        return PREFIX + "/user/address_add.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/addressEditPage/{ID}")
    public String userEdit(@PathVariable String ID, Model model) {
        if (ToolUtil.isEmpty(ID)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        HashMap<String,Object> address = userDao.getAddressByID(ID);
        HashMap<String,Object> parentAddress = userDao.getAddressByID(address.get("PARENT_ID").toString());
        model.addAttribute("",address);
        model.addAttribute("NAME",address.get("NAME").toString());
        model.addAttribute("ADDRESS_LEVEL",address.get("ADDRESS_LEVEL").toString());
        model.addAttribute("PARENT_NAME",parentAddress.get("NAME").toString());
        model.addAttribute("PARENT_ID",parentAddress.get("ID").toString());
        if(address.get("TYPE")!=null){
            model.addAttribute("TYPE",address.get("TYPE").toString());
        }else {
            model.addAttribute("TYPE","");
        }
        if(address.get("accurate_address")!=null){
            model.addAttribute("accurate_address",address.get("accurate_address").toString());
        }else {
            model.addAttribute("accurate_address","");
        }
        if(address.get("lng_lat")!=null){
            model.addAttribute("lng_lat",address.get("lng_lat").toString());
        }else {
            model.addAttribute("lng_lat","");
        }
        if(address.get("status")!=null){
            model.addAttribute("status",address.get("status").toString());
        }else {
            model.addAttribute("status","2");
        }
        return PREFIX + "/user/address_edit.html";
    }

    /**
     * 跳转到用户激活信息
     */
    @RequestMapping("/activityPage")
    public String activityPage() {
        return PREFIX + "/activity/activity.html";
    }

    /**
     * 获取用户激活列表
     */
    @RequestMapping(value = "/activityList")
    @ResponseBody
    public Object activityList(@RequestParam(required = false) HashMap map) {
        return userService.queryActivityList(map);
    }


    /**
     * 激活信息exportExcel
     */
    @RequestMapping(value = "/exportActivityExcel/{beginTime}/{endTime}")
    @ResponseBody
    public void exportActivityExcel(@PathVariable String beginTime, @PathVariable String endTime, HttpServletRequest request, HttpServletResponse response) {
        HashMap params = new HashMap<String, Object>();
        params.put("beginTime",beginTime);
        params.put("endTime",endTime);
        List<HashMap> orderList = userService.queryActivityExport(params);
        //生成Excel
        String name = beginTime.substring(0,10)+" to "+endTime.substring(0,10);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名称");
        row.createCell(1).setCellValue("用户电话");
        row.createCell(2).setCellValue("用户昵称");
        row.createCell(3).setCellValue("送达时间");
        row.createCell(4).setCellValue("市");
        row.createCell(5).setCellValue("区");
        row.createCell(6).setCellValue("商圈");
        row.createCell(7).setCellValue("大厦/小区");
        row.createCell(8).setCellValue("详细地址");
        row.createCell(9).setCellValue("注册时间");
        row.createCell(10).setCellValue("激活时间");
        int i=0;
        for (HashMap map:orderList) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(map.get("user_name")==null?"-":map.get("user_name").toString());
            row.createCell(1).setCellValue(map.get("telephone")==null?"-":map.get("telephone").toString());
            row.createCell(2).setCellValue(map.get("nick_name")==null?"-":map.get("nick_name").toString());
            row.createCell(3).setCellValue(map.get("service_time")==null?"-":map.get("service_time").toString());
            row.createCell(4).setCellValue(map.get("name2")==null?"-":map.get("name2").toString());
            row.createCell(5).setCellValue(map.get("name3")==null?"-":map.get("name3").toString());
            row.createCell(6).setCellValue(map.get("name4")==null?"-":map.get("name4").toString());
            row.createCell(7).setCellValue(map.get("name5")==null?"-":map.get("name5").toString());
            row.createCell(8).setCellValue(map.get("accurate_address")==null?"-":map.get("accurate_address").toString());
            row.createCell(9).setCellValue(map.get("create_time")==null?"-":map.get("create_time").toString());
            row.createCell(10).setCellValue(map.get("activity_time")==null?"-":map.get("activity_time").toString());
            i++;
        }
        //生成临时Excel文件
        String path = request.getServletContext().getRealPath("/static");
        String fileName = "DhxhActivityData.xlsx";
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
     * 获取用户列表
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public Object userList(@RequestParam(required = false) HashMap map) {
        return userService.queryUserList(map);
    }

    /**
     * 用户信息exportExcel
     */
    @RequestMapping(value = "/exportUserExcel/{beginTime}/{endTime}")
    @ResponseBody
    public void exportUserExcel(@PathVariable String beginTime, @PathVariable String endTime, HttpServletRequest request, HttpServletResponse response) {
        HashMap params = new HashMap<String, Object>();
        params.put("beginTime",beginTime);
        params.put("endTime",endTime);
        List<HashMap<String,Object>> orderList = userService.queryUserExcel(params);
        //生成Excel
        String name = beginTime.substring(0,10)+" to "+endTime.substring(0,10);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名称");
        row.createCell(1).setCellValue("用户电话");
        row.createCell(2).setCellValue("用户昵称");
        row.createCell(3).setCellValue("送达时间");
        row.createCell(4).setCellValue("商圈");
        row.createCell(5).setCellValue("大厦/小区");
        row.createCell(6).setCellValue("公司");
        row.createCell(7).setCellValue("详细地址");
        row.createCell(8).setCellValue("用户类型");
        row.createCell(9).setCellValue("注册时间");
        int i=0;
        for (HashMap map:orderList) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(map.get("user_name")==null?"-":map.get("user_name").toString());
            row.createCell(1).setCellValue(map.get("telephone")==null?"-":map.get("telephone").toString());
            row.createCell(2).setCellValue(map.get("nick_name")==null?"-":map.get("nick_name").toString());
            row.createCell(3).setCellValue(map.get("service_time")==null?"-":map.get("service_time").toString());
            row.createCell(4).setCellValue(map.get("NAME4")==null?"-":map.get("NAME4").toString());
            row.createCell(5).setCellValue(map.get("NAME5")==null?"-":map.get("NAME5").toString());
            row.createCell(6).setCellValue(map.get("NAME6")==null?"-":map.get("NAME6").toString());
            row.createCell(7).setCellValue(map.get("accurate_address")==null?"-":map.get("accurate_address").toString());
            row.createCell(8).setCellValue(map.get("user_type")==null?"-":map.get("user_type").toString());
            row.createCell(9).setCellValue(map.get("create_time")==null?"-":map.get("create_time").toString());
            i++;
        }
        //生成临时Excel文件
        String path = request.getServletContext().getRealPath("/static");
        String fileName = "DhxhUserData.xlsx";
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
     * 获取地址列表
     */
    @RequestMapping(value = "/addressList")
    @ResponseBody
    public Object queryAddressList(@RequestParam(required = false) HashMap map) {
        return userService.queryAddressList(map);
    }

    /**
     * 获取地址树
     */
    @RequestMapping(value = "/addressTree")
    @ResponseBody
    public List<ZTreeNode> queryAddressTree(@RequestParam(required = false) HashMap map) {
        return userService.queryAddressTree(map);
    }

    /**
     * 获取地址列表
     */
    @RequestMapping(value = "/mergeAddress")
    @ResponseBody
    public Object mergeAddress(@RequestParam(required = false) HashMap map) {
        return userService.mergeAddress(map);
    }

}

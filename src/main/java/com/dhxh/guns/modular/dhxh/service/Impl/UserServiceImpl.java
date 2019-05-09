package com.dhxh.guns.modular.dhxh.service.Impl;

import com.dhxh.guns.common.node.ZTreeNode;
import com.dhxh.guns.modular.dhxh.dao.OrderDao;
import com.dhxh.guns.modular.dhxh.dao.UserDao;
import com.dhxh.guns.modular.dhxh.service.OrderService;
import com.dhxh.guns.modular.dhxh.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Object queryUserList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> userList= userDao.queryUserList(map);
            int userListNum= userDao.queryUserListNum(map);
            resMap.put("rows",userList);
            resMap.put("total",userListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取用户信息列表异常 {}",e);
            resMap.put("msg","获取用户信息列表异常");
        }
        return resMap;
    }

    @Override
    public Object queryAddressList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap<String,Object>> addressList= userDao.queryAddressList(map);
            int addressListNum= userDao.queryAddressListNum(map);
            resMap.put("rows",addressList);
            resMap.put("total",addressListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取地址信息列表异常 {}",e);
            resMap.put("msg","获取地址信息列表异常");
        }
        return resMap;
    }

    @Override
    public List<ZTreeNode> queryAddressTree(HashMap map) {
        List<ZTreeNode> tree = userDao.queryAddressTree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    @Override
    public Object mergeAddress(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        if("0".equals(map.get("PARENT_ID").toString())){
            map.put("ADDRESS_LEVEL",1);
            map.replace("PARENT_ID",-1);
        }else {
            HashMap<String,Object> address = userDao.getAddressByID(map.get("PARENT_ID").toString());
            if(address!=null){
                map.put("ADDRESS_LEVEL",Integer.parseInt(address.get("ADDRESS_LEVEL").toString())+1);
            }else {
                resMap.put("code",-2);
                resMap.put("msg","父级地址出错");
                return resMap;
            }
        }
        int count = 0;
        count = userDao.mergeAddress(map);
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
    public Object queryActivityList(HashMap map) {
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        try {
            List<HashMap> userList= userDao.queryActivityList(map);
            int userListNum= userDao.queryActivityListNum(map);
            resMap.put("rows",userList);
            resMap.put("total",userListNum);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取用户激活信息列表异常 {}",e);
            resMap.put("msg","获取用户激活信息列表异常");
        }
        return resMap;
    }

    @Override
    public List<HashMap> queryActivityExport(HashMap map) {
        try {
            List<HashMap> orderList = userDao.queryActivityList(map);
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取要导出的用户激活信息异常 {}",e);
            return null;
        }
    }

    @Override
    public List<HashMap<String,Object>> queryUserExcel(HashMap map) {
        try {
            List<HashMap<String,Object>> userList= userDao.queryUserList(map);
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("获取要导出的用户信息异常 {}",e);
            return null;
        }
    }

}

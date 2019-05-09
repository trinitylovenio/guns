package com.dhxh.guns.modular.dhxh.service;

import com.dhxh.guns.common.node.ZTreeNode;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface UserService {

    /**
     * 按条件获取用户信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryUserList(HashMap map);

    /**
     * 按条件获取地址信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryAddressList(HashMap map);

    /**
     * 按条件获取订单信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    List<ZTreeNode> queryAddressTree(HashMap map);

    /**
     * 添加地址
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object mergeAddress(HashMap map);


    /**
     * 按条件获取用户激活信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryActivityList(HashMap map);

    /**
     * 获取要导出的用户激活信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    List<HashMap> queryActivityExport(HashMap map);


    /**
     * 获取要导出的用户信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    List<HashMap<String,Object>> queryUserExcel(HashMap map);

}

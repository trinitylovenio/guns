package com.dhxh.guns.modular.dhxh.dao;

import com.dhxh.guns.common.node.ZTreeNode;

import java.util.HashMap;
import java.util.List;

/**
 * 管理员的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface UserDao {



    /**
     * 查询用户列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryUserList(HashMap map);

    /**
     * 查询用户总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryUserListNum(HashMap map);

    /**
     * 查询地址列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryAddressList(HashMap map);

    /**
     * 查询地址总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryAddressListNum(HashMap map);

    /**
     * 查询地址树
     *
     * @param
     * @date 2019/03/22
     */
    List<ZTreeNode> queryAddressTree();

    /**
     * 获取选中的地址详情
     *
     * @param ID
     * @date 2019/03/22
     */
    HashMap<String,Object> getAddressByID(String ID);

    /**
     * 插入地址
     *
     * @param map
     * @date 2019/03/22
     */
    int mergeAddress(HashMap map);


    /**
     * 查询用户列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap> queryActivityList(HashMap map);

    /**
     * 查询用户总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryActivityListNum(HashMap map);

}

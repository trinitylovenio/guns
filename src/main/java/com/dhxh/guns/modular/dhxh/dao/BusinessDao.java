package com.dhxh.guns.modular.dhxh.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 管理员的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface BusinessDao {

    /**
     * 查询企业账户列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryBusinessInfoList(HashMap map);

    /**
     * 查询企业账户列表总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryBusinessInfoListNum(HashMap map);

    /**
     * 查询企业包月列表
     *
     * @param map
     * @date 2019/03/22
     */
    List<HashMap<String,Object>> queryMSList(HashMap map);

    /**
     * 查询企业包月列表总数
     *
     * @param map
     * @date 2019/03/22
     */
    int queryMSListNum(HashMap map);

    /**
     * 查询企业账户列表
     *
     * @param id
     * @date 2019/03/22
     */
    HashMap<String,Object> queryBusinessInfoById(String id);

    /**
     * 修改账户信息
     *
     * @param map
     * @date 2019/03/22
     */
    int updateBusinessInfo(HashMap map);
}

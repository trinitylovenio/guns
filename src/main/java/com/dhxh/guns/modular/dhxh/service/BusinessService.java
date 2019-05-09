package com.dhxh.guns.modular.dhxh.service;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface BusinessService {

    /**
     * 企业账户信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryBusinessInfoList(HashMap map);

    /**
     * 企业包月信息
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object queryMSList(HashMap map);

    /**
     * 添加企业账户
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object addSubmit(HashMap map);

    /**
     * 修改启用账户
     *
     * @author fengshuonan
     * @date 2017-05-05 22:19
     */
    Object updateBusinessInfo(HashMap map);


}

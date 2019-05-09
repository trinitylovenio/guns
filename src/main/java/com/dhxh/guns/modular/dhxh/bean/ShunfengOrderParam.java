package com.dhxh.guns.modular.dhxh.bean;

/**
 * 微信统一下单 接口参数
 */
public class ShunfengOrderParam {
    private int dev_id; //必填 同城开发者ID
    private String shop_id; //必填 店铺ID
    private int shop_type; //非必填 店铺ID类型	1：顺丰店铺ID ；2：接入方店铺ID
    private String shop_order_id; //必填 商家订单号	不允许重复
    private String order_source; //必填 订单接入来源	1：美团；2：饿了么；3：百度；4：口碑；其他请直接填写中文字符串值
    private String order_sequence; //非必填 取货序号	与order_source配合使用如：饿了么10号单，表示如下：order_source=2;order_sequence=10。用于骑士快速寻找配送物
    private int lbs_type; //非必填 坐标类型	1：百度坐标，2：高德坐标
    private int pay_type; // 必填 用户支付方式	1：已付款 0：货到付款
    private int receive_user_money; //非必填 代收金额	单位：分
    private int order_time; //必填 用户下单时间	秒级时间戳
    private int is_appoint; //必填 是否是预约单	0：非预约单；1：预约单
    private int expect_time; //非必填  用户期望送达时间	预约单需必传,秒级时间戳
    private int is_insured; //  必填  是	是否保价，0：非保价；1：保价
    private int declared_value;// 非必填  保价金额	单位：分
    private int remark;// 非必填  否	订单备注
    private int rider_pick_method;//非必填 物流流向	1：从门店取件送至用户；2：从用户取件送至门店
    private int return_flag;//非必填 返回字段控制标志位（二进制）	1：价格，2：距离，4：重量，组合条件请相加例如全部返回为填入7
    private int push_time; // 必填  推单时间	秒级时间戳
    private int version; // 必填  版本号	参照文档主版本号填写如：文档版本号1.6,version=16
    private Object receive; //必填 收货人信息	，详见receive结构
    private Object shop; //非必填 发货店铺信息，详见shop结构，平台级开发者（如饿了么）需传入如无特殊说明此字段可忽略
    private Object order_detail; //必填 收货人信息	订单详情	Obj，详见order_detail结构

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public int getShop_type() {
        return shop_type;
    }

    public void setShop_type(int shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_order_id() {
        return shop_order_id;
    }

    public void setShop_order_id(String shop_order_id) {
        this.shop_order_id = shop_order_id;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getOrder_sequence() {
        return order_sequence;
    }

    public void setOrder_sequence(String order_sequence) {
        this.order_sequence = order_sequence;
    }

    public int getLbs_type() {
        return lbs_type;
    }

    public void setLbs_type(int lbs_type) {
        this.lbs_type = lbs_type;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getReceive_user_money() {
        return receive_user_money;
    }

    public void setReceive_user_money(int receive_user_money) {
        this.receive_user_money = receive_user_money;
    }

    public int getOrder_time() {
        return order_time;
    }

    public void setOrder_time(int order_time) {
        this.order_time = order_time;
    }

    public int getIs_appoint() {
        return is_appoint;
    }

    public void setIs_appoint(int is_appoint) {
        this.is_appoint = is_appoint;
    }

    public int getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(int expect_time) {
        this.expect_time = expect_time;
    }

    public int getIs_insured() {
        return is_insured;
    }

    public void setIs_insured(int is_insured) {
        this.is_insured = is_insured;
    }

    public int getDeclared_value() {
        return declared_value;
    }

    public void setDeclared_value(int declared_value) {
        this.declared_value = declared_value;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public int getRider_pick_method() {
        return rider_pick_method;
    }

    public void setRider_pick_method(int rider_pick_method) {
        this.rider_pick_method = rider_pick_method;
    }

    public int getReturn_flag() {
        return return_flag;
    }

    public void setReturn_flag(int return_flag) {
        this.return_flag = return_flag;
    }

    public int getPush_time() {
        return push_time;
    }

    public void setPush_time(int push_time) {
        this.push_time = push_time;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Object getReceive() {
        return receive;
    }

    public void setReceive(Object receive) {
        this.receive = receive;
    }

    public Object getShop() {
        return shop;
    }

    public void setShop(Object shop) {
        this.shop = shop;
    }

    public Object getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(Object order_detail) {
        this.order_detail = order_detail;
    }
}

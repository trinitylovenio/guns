/**
 *
 */
var AddAccount = {
    id: "AddAccount",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
};

/**
 * 提交添加用户
 */
AddAccount.addSubmit = function () {
    var queryData = {};
    var id = $("#id").val();
    var business_name = $("#business_name").val();
    var contact = $("#contact").val();
    var telephone = $("#telephone").val();
    var a2 = $("#a2").val();
    var address_info = $("#address_info").val();

    if(!(/^1[3456789]\d{9}$/.test(telephone))){
        Feng.info("手机号码有误");
        return;
    }
    if(business_name.length<=0){
        Feng.info("请填写企业名称");
        return;
    }
    if(contact.length<=0){
        Feng.info("请填写联系人");
        return;
    }
    if(a2==null || a2=='' || a2==undefined){
        Feng.info("请选择省市")
    }
    if(address_info.length<=0){
        Feng.info("请填写详细地址");
        return;
    }
    var queryData = {};
    queryData['id'] = id;
    queryData['business_name'] = business_name;
    queryData['contact'] = contact;
    queryData['telephone'] = telephone;
    queryData['address_id'] = a2;
    queryData['address_info'] = address_info;
    queryData['status'] = 0;
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/business/addSubmit", function (data) {
        if(data.code==1){
            Feng.success("添加成功!");
            window.parent.BusinessInfo.table.refresh();
            AddAccount.close();
        }else {
            Feng.error(data.msg)
        }

    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(queryData);
    ajax.start();

};

/**
 * 关闭此对话框
 */
AddAccount.close = function () {
    parent.layer.close(window.parent.BusinessInfo.layerIndex);
};
/**
 * 级联更新市级地址内容
 */
AddAccount.getA2Item = function(){
    var parent_id = $("#a1").val();
    $("#a2").empty()
    var ajax = new $ax(Feng.ctxPath + "/user/addressList", function (data) {
        var addressList =  data["rows"]
        var name = null;
        var id = null;
        for(var i=0;i<addressList.length;i++){
            name = addressList[i]["NAME"]
            id = addressList[i]["ID"]
            $("#a2").append("<option value='"+id+"'>"+name+"</option>")
        }
    });
    ajax.set("PARENT_ID",parent_id);
    ajax.set("address_level",2);
    ajax.start();
}

$(function () {
    //获取用户填写的省市
    var a1_id = $("#a1_id").val()
    var a2_id = $("#a2_id").val()
    //初始化填充省级地址下拉选框
    var ajax = new $ax(Feng.ctxPath + "/user/addressList", function (data) {
        var addressList =  data["rows"]
        var name = null;
        var id = null;
        for(var i=0;i<addressList.length;i++){
            name = addressList[i]["NAME"]
            id = addressList[i]["ID"]
            $("#a1").append("<option value='"+id+"'>"+name+"</option>")
        }
    });
    ajax.set("address_level",1);
    ajax.start();

    //初始化填充市级地址下拉选框
    var ajax = new $ax(Feng.ctxPath + "/user/addressList", function (data) {
        var addressList =  data["rows"]
        var name = null;
        var id = null;
        for(var i=0;i<addressList.length;i++){
            name = addressList[i]["NAME"]
            id = addressList[i]["ID"]
            $("#a2").append("<option value='"+id+"'>"+name+"</option>")
        }
    });
    ajax.set("PARENT_ID",a1_id);
    ajax.set("address_level",2);
    ajax.start();

    //选中值
    $("#a1").val(a1_id)
    $("#a2").val(a2_id)
});


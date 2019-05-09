/**
 * 角色管理的单例
 */
var Order = {
    id: "orderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '用户名称', field: 'USER_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '用户电话', field: 'TELEPHONE', align: 'center', valign: 'middle', sortable: false},
        // {title: '区', field: 'NAME3', align: 'center', valign: 'middle', sortable: false},
        {title: '商圈', field: 'NAME4', align: 'center', valign: 'middle', sortable: false},
        {title: '大厦/小区', field: 'NAME5', align: 'center', valign: 'middle', sortable: false},
        {title: '公司', field: 'NAME6', align: 'center', valign: 'middle', sortable: false},
        {title: '详细地址', field: 'ACCURATE_ADDRESS', align: 'center', valign: 'middle', sortable: false},
        {title: '送达时间', field: 'SERVICE_TIME', align: 'center', valign: 'middle', sortable: false},
        {title: '订单类型', field: 'ORDER_TYPE', align: 'center', valign: 'middle', sortable: false},
        {title: '主食1', field: 'STAPLE_FOOD_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '副食1', field: 'NON_STAPLE_FOOD_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '副食2', field: 'NON_STAPLE_FOOD_2_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '饮料1', field: 'DRINKS_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '小吃1', field: 'SNACKS_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '下单时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false},
        {title: '用户类型 ', field: 'user_type', align: 'center', valign: 'middle', sortable: false},
        {title: '订单状态', field: 'ORDER_STATUS', align: 'center', valign: 'middle', sortable: false},
        {title: '打印状态', field: 'print_status', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Order.seItem = selected[0];
        return true;
    }
};


/**
 * 搜索
 */
Order.search = function () {
    var queryData = {};
    queryData["USER_NAME"]=$("#USER_NAME").val();
    queryData["user_type"]=$("#user_type").val();
    queryData["PRINT_STATUS"]=$("#PRINT_STATUS").val();
    queryData["SERVICE_TIME"]=$("#SERVICE_TIME").val();
    queryData["NAME5"]=$("#NAME5").val();
    queryData["NAME6"]=$("#NAME6").val();
    queryData["ACCURATE_ADDRESS"]=$("#ACCURATE_ADDRESS").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["ORDER_STATUS"]=$("#ORDER_STATUS").val();
    queryData["ORDER_TYPE"]=$("#ORDER_TYPE").val();
    Order.table.server_init(queryData);
}

/**
 * 导出Excel表格
 */
Order.Export = function () {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();

    if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
        Feng.info("请先选择时间范围然后再导出表格")
    }else{
        var USER_NAME='empty';
        var user_type='empty';
        var PRINT_STATUS='empty';
        var SERVICE_TIME='empty';
        var NAME5='empty';
        var NAME6='empty';
        var ACCURATE_ADDRESS='empty';
        var ORDER_STATUS='empty';
        var ORDER_TYPE='empty';
        if(!Feng.isEmpty($("#USER_NAME").val())){
            USER_NAME=$("#USER_NAME").val();
        }
        if(!Feng.isEmpty($("#user_type").val())){
            user_type=$("#user_type").val();
        }
        if(!Feng.isEmpty($("#PRINT_STATUS").val())){
            PRINT_STATUS=$("#PRINT_STATUS").val();
        }
        if(!Feng.isEmpty($("#SERVICE_TIME").val())){
            SERVICE_TIME=$("#SERVICE_TIME").val();
        }
        if(!Feng.isEmpty($("#NAME5").val())){
            NAME5=$("#NAME5").val();
        }
        if(!Feng.isEmpty($("#NAME6").val())){
            NAME6=$("#NAME6").val();
        }
        if(!Feng.isEmpty($("#ACCURATE_ADDRESS").val())){
            ACCURATE_ADDRESS=$("#ACCURATE_ADDRESS").val();
        }
        if(!Feng.isEmpty($("#ORDER_STATUS").val())){
            ORDER_STATUS=$("#ORDER_STATUS").val();
        }
        if(!Feng.isEmpty($("#ORDER_TYPE").val())){
            ORDER_TYPE=$("#ORDER_TYPE").val();
        }
        window.location.href =Feng.ctxPath+ "/order/exportExcel/"+beginTime+"/"+endTime+"/"
            +USER_NAME+"/"+user_type+"/"+PRINT_STATUS+"/"+SERVICE_TIME+"/"+NAME5+"/"+NAME6+"/"
            +ACCURATE_ADDRESS+"/"+ORDER_STATUS+"/"+ORDER_TYPE

    }
}

/**
 * 打印配送单
 */
Order.prientOrder = function () {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
        Feng.info("请先选择时间范围然后再导出表格")
    }else{
        var queryData ={};
        queryData["USER_NAME"]=$("#USER_NAME").val();
        queryData["user_type"]=$("#user_type").val();
        queryData["PRINT_STATUS"]=$("#PRINT_STATUS").val();
        queryData["SERVICE_TIME"]=$("#SERVICE_TIME").val();
        queryData["NAME5"]=$("#NAME5").val();
        queryData["NAME6"]=$("#NAME6").val();
        queryData["ACCURATE_ADDRESS"]=$("#ACCURATE_ADDRESS").val();
        queryData["beginTime"]=$("#beginTime").val();
        queryData["endTime"]=$("#endTime").val();
        queryData["ORDER_STATUS"]=$("#ORDER_STATUS").val();
        queryData["ORDER_TYPE"]=$("#ORDER_TYPE").val();
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/order/prientOrder", function (data) {
            if(data.code==1){
                Feng.success("打印成功!");
            }else {
                Feng.error(data.msg)
            }

        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.setData(queryData);
        ajax.start();
    }
}

// /**
//  * 顺丰下单
//  */
// Order.shunfengOrder = function () {
//     var beginTime = $("#beginTime").val();
//     var endTime = $("#endTime").val();
//     if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
//         Feng.info("请先选择时间范围然后再导出表格")
//     }else{
//         var queryData ={};
//         queryData["beginTime"]=$("#beginTime").val();
//         queryData["endTime"]=$("#endTime").val();
//         queryData["order_status"]=0;
//         //提交信息
//         var ajax = new $ax(Feng.ctxPath + "/order/shunfengOrder", function (data) {
//             if(data.code==1){
//                 Feng.success("打印成功!");
//             }else {
//                 Feng.error(data.msg)
//             }
//
//         }, function (data) {
//             Feng.error("添加失败!" + data.responseJSON.message + "!");
//         });
//         ajax.setData(queryData);
//         ajax.start();
//     }
// }

$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var today = new Date();
    $("#beginTime").val(Order.getBeginDate(today))
    $("#endTime").val(Order.getEndDate(today))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["ORDER_STATUS"]=$("#ORDER_STATUS").val();
    queryData["ORDER_TYPE"]=$("#ORDER_TYPE").val();
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/orderList", defaultColunms);
    Order.table = table.server_init(queryData);
});

Order.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 08:00:00";
    return nowDay;
}

Order.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 13:06:00";
    return nowDay;
}

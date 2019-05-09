/**
 * 角色管理的单例
 */
var Shunfeng = {
    id: "ShunfengTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Shunfeng.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: false},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '用户名称', field: 'user_name', align: 'center', valign: 'middle', sortable: false},
        {title: '用户电话', field: 'user_phone', align: 'center', valign: 'middle', sortable: false},
        {title: '用户地址', field: 'user_address', align: 'center', valign: 'middle', sortable: false},
        {title: '用户经度', field: 'user_lng', align: 'center', valign: 'middle', sortable: false},
        {title: '用户纬度', field: 'user_lat', align: 'center', valign: 'middle', sortable: false},
        {title: '期望送达时间', field: 'expect_time', align: 'center', valign: 'middle', sortable: false},
        {title: '数量', field: 'product_num', align: 'center', valign: 'middle', sortable: false},
        {title: '订单状态', field: 'order_status', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var order_status = row.order_status;
                var dir={"0":"预下单","1":"订单创建","2":"订单取消","2":"订单取消","10":"配送员确认订单","12":"配送员到店",
                    "15":"配送员配送中","17":"订单完成"};
                return dir[order_status];
            }
        },
        {title: '创建时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false},
        {title: '推单时间', field: 'push_time', align: 'center', valign: 'middle', sortable: false},
    ];
    return columns;
};

/**
 * 检查是否选中
 */
Shunfeng.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Shunfeng.seItem = selected[0];
        return true;
    }
};


/**
 * 搜索
 */
Shunfeng.search = function () {
    var queryData = {};
    queryData["user_name"]=$("#user_name").val();
    queryData["user_address"]=$("#user_address").val();
    queryData["user_phone"]=$("#user_phone").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    // queryData["arrive_time"]=$("#arrive_time").val();
    queryData["expect_time"]=$("#expect_time").val();
    queryData["order_status"]=$("#order_status").val();
    Shunfeng.table.server_init(queryData);
}

/**
 * 下单
 */
Shunfeng.shunfengOrder = function () {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
        Feng.info("请先选择时间范围然后再导出表格")
    }else{
        var queryData ={};
        queryData["user_name"]=$("#user_name").val();
        queryData["user_address"]=$("#user_address").val();
        queryData["user_phone"]=$("#user_phone").val();
        queryData["beginTime"]=$("#beginTime").val();
        queryData["endTime"]=$("#endTime").val();
        // queryData["arrive_time"]=$("#arrive_time").val();
        queryData["expect_time"]=$("#expect_time").val();
        queryData["order_status"]=$("#order_status").val();
        queryData["shunfeng_status"]=0;
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/shunfeng/shunfengOrder", function (data) {
            if(data.code==1){
                Feng.success("下单成功");
            }else {
                Feng.error(data.msg)
            }

        }, function (data) {
            Feng.error("下单失败!" + data.responseJSON.message + "!");
        });
        ajax.setData(queryData);
        ajax.start();
    }
}

$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var today = new Date();
    $("#beginTime").val(Shunfeng.getBeginDate(today))
    $("#endTime").val(Shunfeng.getEndDate(today))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    var defaultColunms = Shunfeng.initColumn();
    var table = new BSTable(Shunfeng.id, "/shunfeng/shunfengList", defaultColunms);
    Shunfeng.table = table.server_init(queryData);
});

Shunfeng.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 08:00:00";
    return nowDay;
}

Shunfeng.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 13:07:00";
    return nowDay;
}

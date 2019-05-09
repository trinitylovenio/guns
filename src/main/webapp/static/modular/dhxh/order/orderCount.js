/**
 * 角色管理的单例
 */
var OrderCount = {
    id: "orderCountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderCount.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '菜名', field: 'FOOD_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '总数', field: 'COUNT_NUM', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
OrderCount.check = function () {
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
OrderCount.search = function () {
    var queryData = {};
    // queryData["USER_NAME"]=$("#USER_NAME").val();
    // queryData["TELEPHONE"]=$("#TELEPHONE").val();
    // queryData["ACCURATE_ADDRESS"]=$("#ACCURATE_ADDRESS").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    // queryData["OrderCount_STATUS"]=$("#OrderCount_STATUS").val();
    // queryData["OrderCount_TYPE"]=$("#OrderCount_TYPE").val();
    OrderCount.table.server_init(queryData);
}

/**
 * 导出Excel表格
 */
OrderCount.Export = function () {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
        Feng.info("请先选择时间范围然后再导出表格")
    }else{
        window.location.href =Feng.ctxPath+ "/order/orderCountExportExcel/"+beginTime+"/"+endTime

    }
}

$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var today = new Date();
    var yester = new Date(today - 1000*60*60*24)
    $("#beginTime").val(OrderCount.getBeginDate(yester))
    $("#endTime").val(OrderCount.getEndDate(yester))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    var defaultColunms = OrderCount.initColumn();
    var table = new BSTable(OrderCount.id, "/order/orderCountList", defaultColunms);
    OrderCount.table = table.server_init(queryData);
});

OrderCount.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 08:00:00";
    return nowDay;
}

OrderCount.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 13:06:00";
    return nowDay;
}

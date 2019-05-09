/**
 * 角色管理的单例
 */
var PersonalService = {
    id: "personalServiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PersonalService.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: false},
        {title: '姓名', field: 'user_name', align: 'center', valign: 'middle', sortable: false},
        {title: '电话', field: 'telephone', align: 'center', valign: 'middle', sortable: false},
        {title: '服务周期', field: '', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var charge_begin_time = row.charge_begin_time;
                var charge_end_time = row.charge_end_time;
                var html =charge_begin_time+'<br/>'+
                    '至 '+charge_end_time;
                return html;
            }
        },
        {title: '下单时间', field: 'purchase_time', align: 'center', valign: 'middle', sortable: false},
        {title: '配送地址', field: '', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var name4 = row.name4;
                var name5 = row.name5;
                var name6 = row.name6;
                var accurate_address = row.accurate_address;
                var html =name4+name5+name6+'<br/>'+
                    '详细地址: '+accurate_address
                return html;
            }
        },
        {title: '送达时间', field: 'service_time', align: 'center', valign: 'middle', sortable: false},
        {title: '包月价格', field: 'price', align: 'center', valign: 'middle', sortable: false},
        {title: '实付金额', field: 'cash', align: 'center', valign: 'middle', sortable: false},
        {title: '使用优惠券', field: 'coupon', align: 'center', valign: 'middle', sortable: false},
        {title: '使用余额', field: 'balance', align: 'center', valign: 'middle', sortable: false},
        {title: '服务状态', field: 'service_status', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};


/**
 * 搜索
 */
PersonalService.search = function () {
    var queryData = {};
    queryData["address_name"]=$("#address_name").val();
    queryData["service_status"]=$("#service_status").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["user_name"]=$("#user_name").val();
    queryData["telephone"]=$("#telephone").val();
    PersonalService.table.server_init(queryData);
}


$(function () {
    var today = new Date();
    $("#beginTime").val(PersonalService.getBeginDate(today))
    $("#endTime").val(PersonalService.getEndDate(today))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    //自动填充开始结束时间 为 昨天和今天
    var defaultColunms = PersonalService.initColumn();
    var table = new BSTable(PersonalService.id, "/order/personalServiceList", defaultColunms);
    PersonalService.table = table.server_init(queryData);
});

PersonalService.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 00:00:00";
    return nowDay;
}

PersonalService.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate()+1;
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 00:00:00";
    return nowDay;
}


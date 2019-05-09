/**
 * 角色管理的单例
 */
var GroupService = {
    id: "groupServiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GroupService.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: false},
        {title: '团购id', field: 'id',align: 'center', valign: 'middle', sortable: false},
        {title: '团购人数', field: 'team_num', align: 'center', valign: 'middle', sortable: false},
        {title: '发起时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false},
        {title: '大厦/小区', field: 'delivery_type', align: 'center', valign: 'middle', sortable: false},
        {title: '配送地址', field: '', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var name4 = row.name4;
                var name5 = row.name5;
                var name6 = row.name6;
                var address_info = row.address_info;
                var accurate_address = row.accurate_address;
                var html =name4+name5+name6+'<br/>'+
                    '详细地址: '+address_info+accurate_address
                return html;
            }
        },
        {title: '已参团人数', field: 'join_num', align: 'center', valign: 'middle', sortable: false},
        {title: '团长姓名', field: 'head_name', align: 'center', valign: 'middle', sortable: false},
        {title: '团购金额', field: 'balance', align: 'center', valign: 'middle', sortable: false},
        {title: '团购状态', field: 'status', align: 'center', valign: 'middle', sortable: false},
        {title: '操作', field: 'NON_STAPLE_FOOD_2_NAME', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var id = row.id;
                return '<a onclick="GroupService.groupServiceInfo(\''+id+'\')">查看详情</a>';
            }
        }
    ];
    return columns;
};

/**
 * 检查是否选中
 */
GroupService.groupServiceInfo = function (id) {
    var index = layer.open({
        type: 2,
        title: '团购订单详情',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/groupServiceInfoPage/'+id
    });
    this.layerIndex = index;
};

/**
 * 搜索
 */
GroupService.search = function () {
    var queryData = {};
    queryData["address_name"]=$("#address_name").val();
    queryData["group_status"]=$("#group_status").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["user_name"]=$("#user_name").val();
    queryData["telephone"]=$("#telephone").val();
    GroupService.table.server_init(queryData);
}



$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var today = new Date();
    $("#beginTime").val(GroupService.getBeginDate(today))
    $("#endTime").val(GroupService.getEndDate(today))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    var defaultColunms = GroupService.initColumn();
    var table = new BSTable(GroupService.id, "/order/groupServiceList", defaultColunms);
    GroupService.table = table.server_init(queryData);
});

GroupService.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 00:00:00";
    return nowDay;
}

GroupService.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate()+1;
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 00:00:00";
    return nowDay;
}

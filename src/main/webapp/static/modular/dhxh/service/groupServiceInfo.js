/**
 * 角色管理的单例
 */
var GroupServiceInfo = {
    id: "groupServiceInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GroupServiceInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: false},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: false},
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
        {title: '团购金额', field: 'price', align: 'center', valign: 'middle', sortable: false},
        {title: '实付金额', field: 'cash', align: 'center', valign: 'middle', sortable: false},
        {title: '使用优惠券', field: 'coupon', align: 'center', valign: 'middle', sortable: false},
        {title: '使用余额', field: 'balance', align: 'center', valign: 'middle', sortable: false},
        {title: '获得返现', field: 'back', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};



/**
 * 关闭此对话框
 */
GroupServiceInfo.close = function () {
    parent.layer.close(window.parent.GroupService.layerIndex);
};

$(function () {
    var queryData = {};
    queryData["group_id"] = $("#group_id").val()
    //自动填充开始结束时间 为 昨天和今天
    var defaultColunms = GroupServiceInfo.initColumn();
    var table = new BSTable(GroupServiceInfo.id, "/order/groupServiceInfoList", defaultColunms);
    GroupServiceInfo.table = table.server_init(queryData);
});


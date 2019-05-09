/**
 * 角色管理的单例
 */
var MS = {
    id: "MSTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MS.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: false},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '企业名称', field: 'business_name', align: 'center', valign: 'middle', sortable: false},
        {title: '包月开始时间', field: 'begin_time', align: 'center', valign: 'middle', sortable: false},
        {title: '包月开始时间', field: 'begin_time', align: 'center', valign: 'middle', sortable: false},
        {title: '包月结束时间', field: 'end_time', align: 'center', valign: 'middle', sortable: false},
        {title: '包月人数', field: 'group_num', align: 'center', valign: 'middle', sortable: false},
        {title: '交易金额', field: 'transaction_amount', align: 'center', valign: 'middle', sortable: false},
        {title: '联系人', field: 'contact', align: 'center', valign: 'middle', sortable: false},
        {title: '手机号码', field: 'telephone', align: 'center', valign: 'middle', sortable: false},
        {title: '收货地址', field: 'address', align: 'center', valign: 'middle', sortable: false},
        {title: '详细地址', field: 'address_info', align: 'center', valign: 'middle', sortable: false},
        {title: '服务状态', field: 'service_status', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var service_status = row.service_status;
                var dir={"0":"未开始","2":"服务中","3":"已结束"};
                return dir[service_status];
            }
        },
        {title: '下单时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};


/**
 * 搜索
 */
MS.search = function () {
    var queryData = {};
    queryData["business_name"]=$("#business_name").val();
    // queryData["beginTime"]=$("#beginTime").val();
    // queryData["endTime"]=$("#endTime").val();
    MS.table.server_init(queryData);
}

$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var queryData = {};
    // queryData["begin_time"]=$("#beginTime").val();
    // queryData["end_time"]=$("#endTime").val();
    var defaultColunms = MS.initColumn();
    var table = new BSTable(MS.id, "/business/MSList", defaultColunms);
    MS.table = table.server_init(queryData);
});

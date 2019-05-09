/**
 * 角色管理的单例
 */
var BusinessInfo = {
    id: "businessInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusinessInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '企业名称', field: 'business_name', align: 'center', valign: 'middle', sortable: false},
        {title: '联系人', field: 'contact', align: 'center', valign: 'middle', sortable: false},
        {title: '手机号', field: 'telephone', align: 'center', valign: 'middle', sortable: false},
        {title: '省市', field: 'address', align: 'center', valign: 'middle', sortable: false},
        {title: '详细地址', field: 'address_info', align: 'center', valign: 'middle', sortable: false},
        {title: '账户状态', field: 'status', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var status = row.status;
                var dir={"0":"正常","1":"失效","2":"无账号"};
                return dir[status];
            }
        },
        {title: '创建时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};

/**
 * 创建账户
 */
BusinessInfo.addAccount = function () {
    if (this.check()) {
        if(BusinessInfo.seItem.status!='2' && BusinessInfo.seItem.status!=2){
            Feng.info("该企业用户已经有账户了,不能重复创建");
            return false;
        }else {
            var index = layer.open({
                type: 2,
                title: '添加账户',
                area: ['100%', '100%'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/business/addAccount/'+BusinessInfo.seItem.id
            });
            this.layerIndex = index;
        }
    }
};


/**
 * 禁用账户
 */
BusinessInfo.disable = function () {
    if (this.check()) {
        if(BusinessInfo.seItem.status!='0' && BusinessInfo.seItem.status!=0){
            Feng.info("只有正常状态的账户可以禁用");
            return false;
        }else {
            var queryData = {};
            queryData['id'] = BusinessInfo.seItem.id;
            queryData['status'] = 1;
            var ajax = new $ax(Feng.ctxPath + "/business/updateBusinessInfo", function (data) {
                if(data.code==1){
                    Feng.success(data.msg);
                    BusinessInfo.table.refresh();
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
};

/**
 * 启用
 */
BusinessInfo.enable = function () {
    if (this.check()) {
        if(BusinessInfo.seItem.status!='1' && BusinessInfo.seItem.status!=1){
            Feng.info("只有失效状态的账户可以启用");
            return false;
        }else {
            var queryData = {};
            queryData['id'] = BusinessInfo.seItem.id;
            queryData['status'] = 0;
            var ajax = new $ax(Feng.ctxPath + "/business/updateBusinessInfo", function (data) {
                if(data.code==1){
                    Feng.success(data.msg);
                    BusinessInfo.table.refresh();
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
};

/**
 * 检查是否选中
 */
BusinessInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        BusinessInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 搜索
 */
BusinessInfo.search = function () {
    var queryData = {};
    queryData["business_name"]=$("#business_name").val();
    BusinessInfo.table.server_init(queryData);
}

$(function () {
    //自动填充开始结束时间 为 昨天和今天b
    var queryData = {};
    var defaultColunBusinessInfo = BusinessInfo.initColumn();
    var table = new BSTable(BusinessInfo.id, "/business/businessInfoList", defaultColunBusinessInfo);
    BusinessInfo.table = table.server_init(queryData);
});

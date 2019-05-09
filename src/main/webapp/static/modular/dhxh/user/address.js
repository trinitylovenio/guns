/**
 * 系统管理--用户管理的单例对象
 */
var Address = {
    id: "addressTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
    addressTree: null
};

/**
 * 初始化表格的列
 */
Address.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '地址ID', field: 'ID', visible: false, align: 'center', valign: 'middle'},
        {title: '地址等级', field: 'ADDRESS_LEVEL', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var ADDRESS_LEVEL = row.ADDRESS_LEVEL;
                var dir={"1":"省","2":"市","3":"区","4":"商圈","5":"大厦/小区","6":"公司"};
                return dir[ADDRESS_LEVEL];
            }
        },
        {title: '地址', field: 'NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '类型', field: 'TYPE', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var TYPE = row.TYPE;
                if(Feng.isEmpty(TYPE)){
                    return '--'
                }
                var dir={"0":"大厦","1":"小区"};
                return dir[TYPE];
            }
        },
        {title: '门牌号', field: 'accurate_address', align: 'center', valign: 'middle', sortable: false},
        {title: '经纬度', field: 'lng_lat', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
Address.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Address.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加地址
 */
Address.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加地址',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/user/addressAddPage'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 地址id
 */
Address.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑地址',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/user/addressEditPage/' + this.seItem.ID
        });
        this.layerIndex = index;
    }
};


Address.resetSearch = function () {
    $("#Address").val("");
    Address.search();
}

Address.search = function () {
    var queryData = {};
    queryData['PARENT_ID'] = Address.PARENT_ID;
    queryData['NAME'] = $("#NAME").val();

    Address.table.server_init(queryData);
}

Address.onClickDept = function (e, treeId, treeNode) {
    Address.PARENT_ID = treeNode.id;
    Address.search();
};

Address.refresh = function(){
    var queryData = {};
    queryData['PARENT_ID'] = Address.PARENT_ID;
    queryData['NAME'] = $("#NAME").val();
    Address.table.server_init(queryData);
    var ztree = new $ZTree("deptTree", "/user/addressTree");
    ztree.bindOnClick(Address.onClickDept);
    Address.addressTree =  ztree.init();
}

$(function () {
    var defaultColunms = Address.initColumn();
    var table = new BSTable(Address.id, "/user/addressList", defaultColunms);
    Address.table = table.server_init();
    var ztree = new $ZTree("deptTree", "/user/addressTree");
    ztree.bindOnClick(Address.onClickDept);
    Address.addressTree =  ztree.init();
});

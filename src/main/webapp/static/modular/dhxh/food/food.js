/**
 * 角色管理的单例
 */
var Food = {
    id: "foodTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Food.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: 'ID', field: 'FOOD_ID', align: 'center', valign: 'middle', sortable: false},
        {title: '名称', field: 'FOOD_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '描述', field: 'FOOD_DES', align: 'center', valign: 'middle', sortable: false},
        {title: '类型', field: 'FOOD_TYPE', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var FOOD_TYPE = row.FOOD_TYPE;
                var dir={"1":"主食","2":"副食","3":"饮料","4":"小吃"};
                return dir[FOOD_TYPE];
            }
        },
        {title: '图片', field: '', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var FOOD_IMG = row.FOOD_IMG;
                if(FOOD_IMG==null || FOOD_IMG=='' || FOOD_IMG==undefined){
                    return '--'
                }
                var ID = row.ID;
                var img = '<div id="cutPic'+ID+'"><img onclick="Food.showPic(\''+ID+'\')" src="'+FOOD_IMG+'" width="50" height="50"></div>'
                    +'<div id="pic'+ID+'" hidden="hidden"><img onclick="Food.hidePic(\''+ID+'\')" src="'+FOOD_IMG+'"></div>';
                return img;
            }
        }
    ];
    return columns;
};

Food.refresh = function(){
    var queryData = {};
    queryData["FOOD_NAME"]=$("#FOOD_NAME").val();
    queryData["FOOD_TYPE"]=$("#FOOD_TYPE").val();
    Food.table.server_init(queryData);
}

/**
 * 点击添加菜品
 */
Food.add = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜品',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/food/foodAddPage'
    });
    this.layerIndex = index;
};

/**
 * 点击修改菜品
 */
Food.edit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加菜品',
            area: ['800px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/food/foodEditPage/'+Food.seItem.FOOD_ID
        });
        this.layerIndex = index;
    }
};

/**
 *点击缩略图查看大图
 */
Food.showPic=function (ID) {
    $("#cutPic"+ID).hide();
    $("#pic"+ID).show();
}
/**
 *点击大图隐藏大图
 */
Food.hidePic=function (ID) {
    $("#cutPic"+ID).show();
    $("#pic"+ID).hide();
}

/**
 * 检查是否选中
 */
Food.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Food.seItem = selected[0];
        return true;
    }
};


/**
 * 搜索
 */
Food.search = function () {
    var queryData = {};
    queryData["FOOD_NAME"]=$("#FOOD_NAME").val();
    queryData["FOOD_TYPE"]=$("#FOOD_TYPE").val();
    Food.table.server_init(queryData);
}


$(function () {
    var defaultColunms = Food.initColumn();
    var table = new BSTable(Food.id, "/food/foodList", defaultColunms);
    Food.table = table.server_init();
});



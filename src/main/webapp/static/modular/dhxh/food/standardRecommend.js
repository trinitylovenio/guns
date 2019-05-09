/**
 * 角色管理的单例
 */
var StandardRecommend = {
    id: "standardRecommendTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StandardRecommend.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '套餐ID', field: 'STANDARD_ID', visible: false, align: 'center', valign: 'middle', sortable: false},
        // {title: '日期', field: 'DATE',align: 'center', valign: 'middle', sortable: false,
        //     formatter:function (value,row) {
        //         var DATE = row.DATE;
        //         DATE = DATE.substr(0,10)
        //         return DATE;
        //     }
        // },
        {title: '套餐名称', field: 'standard_name', align: 'center', valign: 'middle', sortable: false},
        {title: '套餐描述', field: 'standard_desc', align: 'center', valign: 'middle', sortable: false},
        {title: '套餐图片', field: 'standard_img', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var standard_img = row.standard_img;
                if(standard_img==null || standard_img=='' || standard_img==undefined){
                    return '--'
                }
                var id = row.id;
                var img = '<div id="cutPic'+id+'"><img onclick="StandardRecommend.showPic(\''+id+'\')" src="'+standard_img+'" width="50" height="50"></div>'
                    +'<div id="pic'+id+'" hidden="hidden"><img onclick="StandardRecommend.hidePic(\''+id+'\')" src="'+standard_img+'"></div>';
                return img;
            }
        },
        {title: '主食1', field: 'STAPLE_FOOD_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '副食1', field: 'NON_STAPLE_FOOD_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '副食2', field: 'NON_STAPLE_FOOD_2_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '饮料1', field: 'DRINKS_1_NAME', align: 'center', valign: 'middle', sortable: false},
        {title: '小吃（赠品）', field: 'SNACKS_1_NAME', align: 'center', valign: 'middle', sortable: false}
        ];
    return columns;
};

/**
 *点击缩略图查看大图
 */
StandardRecommend.showPic=function (ID) {
    $("#cutPic"+ID).hide();
    $("#pic"+ID).show();
}
/**
 *点击大图隐藏大图
 */
StandardRecommend.hidePic=function (ID) {
    $("#cutPic"+ID).show();
    $("#pic"+ID).hide();
}

/**
 * 点击添加菜品
 */
StandardRecommend.add = function () {
    var index = layer.open({
        type: 2,
        title: '添加套餐',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/food/standardRecommendAddPage'
    });
    this.layerIndex = index;
};

/**
 * 点击修改菜品
 */
StandardRecommend.edit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加套餐',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/food/standardRecommendEditPage/'+StandardRecommend.seItem.STANDARD_ID
        });
        this.layerIndex = index;
    }
};

/**
 * 检查是否选中
 */
StandardRecommend.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        StandardRecommend.seItem = selected[0];
        return true;
    }
};

/**
 * 搜索
 */
StandardRecommend.search = function () {
    var queryData = {};
    // queryData["beginTime"]=$("#beginTime").val();
    // queryData["endTime"]=$("#endTime").val();
    queryData["standard_name"]=$("#standard_name").val();
    StandardRecommend.table.server_init(queryData);
}


$(function () {
    var queryData = {};
    // queryData["beginTime"]=$("#beginTime").val();
    // queryData["endTime"]=$("#endTime").val();
    queryData["standard_name"]=$("#standard_name").val();
    var defaultColunms = StandardRecommend.initColumn();
    var table = new BSTable(StandardRecommend.id, "/food/standardRecommendList", defaultColunms);
    StandardRecommend.table = table.server_init(queryData);
});


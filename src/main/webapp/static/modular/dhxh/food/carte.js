/**
 * 角色管理的单例
 */
var Carte = {
    id: "carteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Carte.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '菜单ID', field: 'CARTE_ID',align: 'center', valign: 'middle', sortable: false},
        {title: '送餐日期', field: 'DATE',align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var DATE = row.DATE;
                DATE = DATE.substr(0,10)
                return DATE;
            }
        },
        {title: '推荐套餐', field: 'set_meal_standard', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_standard = row.set_meal_standard;
                if(!Feng.isEmpty(set_meal_standard)){
                    var standard_name = set_meal_standard['standard_name'];
                    var standard_desc = set_meal_standard['standard_desc'];
                    html = '套餐名称: '+'<br/>'+standard_name+'<br/>' +
                        '套餐简介: '+'<br/>'+standard_desc+'<br/>'
                }
                return html;
            }
        },
        {title: '推荐套餐详情', field: 'set_meal_standard_info', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_standard = row.set_meal_standard;
                if(!Feng.isEmpty(set_meal_standard)){
                    var STAPLE_FOOD_1_NAME = set_meal_standard['STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_1_NAME = set_meal_standard['NON_STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_2_NAME = set_meal_standard['NON_STAPLE_FOOD_2_NAME'];
                    var DRINKS_1_NAME = set_meal_standard['DRINKS_1_NAME'];
                    var SNACKS_1_NAME = set_meal_standard['SNACKS_1_NAME'];
                    if(STAPLE_FOOD_1_NAME!='--'){
                        html = html+'主食1：'+'<br/>'+STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_1_NAME!='--'){
                        html = html+'辅食1：'+'<br/>'+NON_STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_2_NAME!='--'){
                        html = html+'辅食2：'+'<br/>'+NON_STAPLE_FOOD_2_NAME+'<br/>'
                    }
                    if(DRINKS_1_NAME!='--'){
                        html = html+'饮料1：'+'<br/>'+DRINKS_1_NAME+'<br/>'
                    }
                    if(SNACKS_1_NAME!='--'){
                        html = html+'小吃1：'+'<br/>'+SNACKS_1_NAME+'<br/>'
                    }
                }
                return html;
            }
        },
        {title: '套餐1', field: 'set_meal_1', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_1 = row.set_meal_1;
                if(!Feng.isEmpty(set_meal_1)){
                    var standard_name = set_meal_1['standard_name'];
                    var standard_desc = set_meal_1['standard_desc'];
                    html = '套餐名称: '+'<br/>'+standard_name+'<br/>' +
                        '套餐简介: '+'<br/>'+standard_desc+'<br/>'
                }
                return html;
            }
        },
        {title: '套餐1详情', field: 'set_meal_1_info', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_1 = row.set_meal_1;
                if(!Feng.isEmpty(set_meal_1)){
                    var STAPLE_FOOD_1_NAME = set_meal_1['STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_1_NAME = set_meal_1['NON_STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_2_NAME = set_meal_1['NON_STAPLE_FOOD_2_NAME'];
                    var DRINKS_1_NAME = set_meal_1['DRINKS_1_NAME'];
                    var SNACKS_1_NAME = set_meal_1['SNACKS_1_NAME'];
                    if(STAPLE_FOOD_1_NAME!='--'){
                        html = html+'主食1：'+'<br/>'+STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_1_NAME!='--'){
                        html = html+'辅食1：'+'<br/>'+NON_STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_2_NAME!='--'){
                        html = html+'辅食2：'+'<br/>'+NON_STAPLE_FOOD_2_NAME+'<br/>'
                    }
                    if(DRINKS_1_NAME!='--'){
                        html = html+'饮料1：'+'<br/>'+DRINKS_1_NAME+'<br/>'
                    }
                    if(SNACKS_1_NAME!='--'){
                        html = html+'小吃1：'+'<br/>'+SNACKS_1_NAME+'<br/>'
                    }
                }
                return html;
            }
        },
        {title: '套餐2', field: 'set_meal_2', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_2 = row.set_meal_2;
                if(!Feng.isEmpty(set_meal_2)){
                    var standard_name = set_meal_2['standard_name'];
                    var standard_desc = set_meal_2['standard_desc'];
                    html = '套餐名称: '+'<br/>'+standard_name+'<br/>' +
                        '套餐简介: '+'<br/>'+standard_desc+'<br/>'
                }
                return html;
            }
        },
        {title: '套餐2详情', field: 'set_meal_2_info', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_2 = row.set_meal_2;
                if(!Feng.isEmpty(set_meal_2)){
                    var STAPLE_FOOD_1_NAME = set_meal_2['STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_1_NAME = set_meal_2['NON_STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_2_NAME = set_meal_2['NON_STAPLE_FOOD_2_NAME'];
                    var DRINKS_1_NAME = set_meal_2['DRINKS_1_NAME'];
                    var SNACKS_1_NAME = set_meal_2['SNACKS_1_NAME'];
                    if(STAPLE_FOOD_1_NAME!='--'){
                        html = html+'主食1：'+'<br/>'+STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_1_NAME!='--'){
                        html = html+'辅食1：'+'<br/>'+NON_STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_2_NAME!='--'){
                        html = html+'辅食2：'+'<br/>'+NON_STAPLE_FOOD_2_NAME+'<br/>'
                    }
                    if(DRINKS_1_NAME!='--'){
                        html = html+'饮料1：'+'<br/>'+DRINKS_1_NAME+'<br/>'
                    }
                    if(SNACKS_1_NAME!='--'){
                        html = html+'小吃1：'+'<br/>'+SNACKS_1_NAME+'<br/>'
                    }
                }
                return html;
            }
        },
        {title: '套餐3', field: 'set_meal_3', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_3 = row.set_meal_3;
                if(!Feng.isEmpty(set_meal_3)){
                    var standard_name = set_meal_3['standard_name'];
                    var standard_desc = set_meal_3['standard_desc'];
                    html = html + '套餐名称: '+'<br/>'+standard_name+'<br/>' +
                        '套餐简介: '+'<br/>'+standard_desc+'<br/>'
                }
                return html;
            }
        },
        {title: '套餐3详情', field: 'set_meal_3_info', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var html = '';
                var set_meal_3 = row.set_meal_3;
                if(!Feng.isEmpty(set_meal_3)){
                    var STAPLE_FOOD_1_NAME = set_meal_3['STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_1_NAME = set_meal_3['NON_STAPLE_FOOD_1_NAME'];
                    var NON_STAPLE_FOOD_2_NAME = set_meal_3['NON_STAPLE_FOOD_2_NAME'];
                    var DRINKS_1_NAME = set_meal_3['DRINKS_1_NAME'];
                    var SNACKS_1_NAME = set_meal_3['SNACKS_1_NAME'];
                    if(STAPLE_FOOD_1_NAME!='--'){
                        html = html+'主食1：'+'<br/>'+STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_1_NAME!='--'){
                        html = html+'辅食1：'+'<br/>'+NON_STAPLE_FOOD_1_NAME+'<br/>'
                    }
                    if(NON_STAPLE_FOOD_2_NAME!='--'){
                        html = html+'辅食2：'+'<br/>'+NON_STAPLE_FOOD_2_NAME+'<br/>'
                    }
                    if(DRINKS_1_NAME!='--'){
                        html = html+'饮料1：'+'<br/>'+DRINKS_1_NAME+'<br/>'
                    }
                    if(SNACKS_1_NAME!='--'){
                        html = html+'小吃1：'+'<br/>'+SNACKS_1_NAME+'<br/>'
                    }
                }
                return html;
            }
        }
        // {title: '主食1备选A', field: 'STAPLE_FOOD_1_A_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '主食1备选B', field: 'STAPLE_FOOD_1_B_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '主食1备选C', field: 'STAPLE_FOOD_1_C_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食1备选A', field: 'NON_STAPLE_FOOD_1_A_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食1备选B', field: 'NON_STAPLE_FOOD_1_B_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食1备选C', field: 'NON_STAPLE_FOOD_1_C_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食2备选A', field: 'NON_STAPLE_FOOD_2_A_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食2备选B', field: 'NON_STAPLE_FOOD_2_B_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '副食2备选C', field: 'NON_STAPLE_FOOD_2_C_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '饮料1备选A', field: 'DRINKS_1_A_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '饮料1备选A', field: 'DRINKS_1_B_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '饮料1备选A', field: 'DRINKS_1_C_NAME', align: 'center', valign: 'middle', sortable: false},
        // {title: '小吃（赠品）', field: 'SNACKS_1_A_NAME', align: 'center', valign: 'middle', sortable: false}
    ];
    return columns;
};

/**
 * 点击添加菜品
 */
Carte.add = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/food/carteAddPage'
    });
    this.layerIndex = index;
};

/**
 * 点击修改菜品
 */
Carte.edit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/food/carteEditPage/'+Carte.seItem.CARTE_ID
        });
        this.layerIndex = index;
    }
};

/**
 * 检查是否选中
 */
Carte.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Carte.seItem = selected[0];
        var resultCode = 0;
        var queryData = {};
        queryData["DATE"]=Carte.seItem.DATE;
        var ajax = new $ax(Feng.ctxPath + "/food/checkDate", function (data) {
            if(data["code"]==1){
                resultCode = 1;
            }else {
                Feng.info(data["msg"]);
                return false;
            }
        }, function (data) {
            Feng.info("校验日期失败!" + data.responseJSON.message + "!");
            return false;
        });
        ajax.setData(queryData);
        ajax.start();
        if(resultCode==1){
            return true;
        }
    }
};

/**
 * 搜索
 */
Carte.search = function () {
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    Carte.table.server_init(queryData);
}


$(function () {
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    var defaultColunms = Carte.initColumn();
    var table = new BSTable(Carte.id, "/food/carteList", defaultColunms);
    Carte.table = table.server_init(queryData);
});


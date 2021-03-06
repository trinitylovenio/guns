/**
 *
 */
var CarteAdd = {
    id: "carteAddTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
};


/**
 * 提交
 */
CarteAdd.addSubmit = function () {
    var queryData = {};
    var DATE = $("#DATE").val();
    var set_meal_standard = $("#set_meal_standard_id").val();
    var set_meal_1 = $("#set_meal_1_id").val();
    var set_meal_2 = $("#set_meal_2_id").val();
    var set_meal_3 = $("#set_meal_3_id").val();
    if( Feng.isEmpty(DATE) || Feng.isEmpty(set_meal_standard)){
        return Feng.info("请先将数据填写完整")
    } else{
        queryData["set_meal_standard"]=set_meal_standard;
        queryData["set_meal_1"]=set_meal_1;
        queryData["set_meal_2"]=set_meal_2;
        queryData["set_meal_3"]=set_meal_3;
        queryData["DATE"]=DATE;
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/food/mergeCarte", function (data) {
            if(data.code==1){
                Feng.success("添加成功!");
                window.parent.Carte.table.refresh();
                CarteAdd.close();
            }else {
                Feng.error(data.msg)
            }

        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.setData(queryData);
        ajax.start();
    }
};

CarteAdd.showSetMealDemo = function(param){
    $("#setMealDemo").empty();
    var queryData = {};
    var standard_name = $("#"+param).val();
    queryData["standard_name"]=standard_name;
    queryData["offset"] = 0;
    queryData["limit"] = 10;
    var ajax = new $ax(Feng.ctxPath + "/food/standardRecommendList", function (data) {
        var foodList = data["rows"]
        var name = null;
        var id = null;
        var STAPLE_FOOD_1_NAME = null;
        var NON_STAPLE_FOOD_1_NAME = null;
        var NON_STAPLE_FOOD_2_NAME = null;
        var DRINKS_1_NAME = null;
        var SNACKS_1_NAME = null;
        for(var i=0;i<foodList.length;i++){
            name = foodList[i]["standard_name"]
            id = foodList[i]["STANDARD_ID"]
            STAPLE_FOOD_1_NAME = foodList[i]["STAPLE_FOOD_1_NAME"]
            NON_STAPLE_FOOD_1_NAME = foodList[i]["NON_STAPLE_FOOD_1_NAME"]
            NON_STAPLE_FOOD_2_NAME = foodList[i]["NON_STAPLE_FOOD_2_NAME"]
            DRINKS_1_NAME = foodList[i]["DRINKS_1_NAME"]
            SNACKS_1_NAME = foodList[i]["STAPLE_FOOD_1_NAME"]
            $("#setMealDemo").append("<li value='"+id+"' onclick='CarteAdd.clickSetMealDemo(\""+param+"\",\""+name+"\",\""+id+"\"," +
                "\""+STAPLE_FOOD_1_NAME+"\",\""+NON_STAPLE_FOOD_1_NAME+"\",\""+NON_STAPLE_FOOD_2_NAME+"\",\""+DRINKS_1_NAME+"\",\""+SNACKS_1_NAME+"\")' >"+name+"</li>")
        }

    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(queryData);
    ajax.start();

    var cityObj = $("#"+param);
    var cityOffset = $("#"+param).offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

CarteAdd.clickSetMealDemo = function (param,name,id,STAPLE_FOOD_1_NAME,NON_STAPLE_FOOD_1_NAME,
                                      NON_STAPLE_FOOD_2_NAME,DRINKS_1_NAME,SNACKS_1_NAME) {

    $("#"+param).val(name);
    $("#"+param+"_id").attr("value",id);
    $("#"+param+"_staple_food_1").val(STAPLE_FOOD_1_NAME);
    $("#"+param+"_non_staple_food_1").val(NON_STAPLE_FOOD_1_NAME);
    $("#"+param+"_non_staple_food_2").val(NON_STAPLE_FOOD_2_NAME);
    $("#"+param+"_drinks_1").val(DRINKS_1_NAME);
    $("#"+param+"_snacks_1").val(SNACKS_1_NAME);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        CarteAdd.hideDeptSelectTree();
    }
}

/**
 *
 */
CarteAdd.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 关闭此对话框
 */
CarteAdd.close = function () {
    parent.layer.close(window.parent.Carte.layerIndex);
};

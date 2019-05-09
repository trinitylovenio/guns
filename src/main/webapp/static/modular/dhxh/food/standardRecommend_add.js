/**
 *
 */
var StandardRecommendAdd = {
    id: "standardRecommendAddTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
};

/**
 * 提交添加用户
 */
StandardRecommendAdd.addSubmit = function () {
    var queryData = {};
    var standard_name = $("#standard_name").val();
    var standard_img = $("#standard_img").val();
    var standard_desc = $("#standard_desc").val();

    var STAPLE_FOOD_1 = $("#STAPLE_FOOD_1_ID").val();
    var NON_STAPLE_FOOD_1 = $("#NON_STAPLE_FOOD_1_ID").val();
    var NON_STAPLE_FOOD_2 = $("#NON_STAPLE_FOOD_2_ID").val();
    var DRINKS_1 = $("#DRINKS_1_ID").val();
    var SNACKS_1 = $("#SNACKS_1_ID").val();
    if( Feng.isEmpty(standard_name)
        ||Feng.isEmpty(standard_img)
        ||Feng.isEmpty(standard_desc)
        // ||Feng.isEmpty(STAPLE_FOOD_1)
        // ||Feng.isEmpty(NON_STAPLE_FOOD_1)
        // ||Feng.isEmpty(NON_STAPLE_FOOD_2)
        // ||Feng.isEmpty(DRINKS_1)
        // ||Feng.isEmpty(SNACKS_1)
    ){
        return Feng.info("请先将数据填写完整")
    } else{
        queryData["standard_name"]=standard_name;
        queryData["standard_img"]=standard_img;
        queryData["standard_desc"]=standard_desc;

        queryData["STAPLE_FOOD_1"]=STAPLE_FOOD_1;
        queryData["NON_STAPLE_FOOD_1"]=NON_STAPLE_FOOD_1;
        queryData["NON_STAPLE_FOOD_2"]=NON_STAPLE_FOOD_2;
        queryData["DRINKS_1"]=DRINKS_1;
        queryData["SNACKS_1"]=SNACKS_1;
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/food/mergeStandardRecommend", function (data) {
            if(data.code==1){
                Feng.success("添加成功!");
                window.parent.StandardRecommend.table.refresh();
                StandardRecommendAdd.close();
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

StandardRecommendAdd.showFoodDemo = function(param){
    $("#foodDemo").empty();
    var queryData = {};
    var food_name = $("#"+param).val();
    queryData["FOOD_NAME"] = food_name;
    queryData["offset"] = 0;
    queryData["limit"] = 10;
    var ajax = new $ax(Feng.ctxPath + "/food/foodList", function (data) {
        var foodList = data["rows"]
        var name = null;
        var id = null;
        for(var i=0;i<foodList.length;i++){
            name = foodList[i]["FOOD_NAME"]
            id = foodList[i]["FOOD_ID"]
            $("#foodDemo").append("<li value='"+id+"' onclick='StandardRecommendAdd.clickFoodDemo(\""+param+"\",\""+name+"\",\""+id+"\")' >"+name+"</li>")
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

StandardRecommendAdd.clickFoodDemo = function (param,name,id) {
    $("#"+param).val(name);
    $("#"+param+"_ID").attr("value",id);
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        StandardRecommendAdd.hideDeptSelectTree();
    }
}

/**
 * 隐藏部门选择的树
 */
StandardRecommendAdd.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};


/**
 * 关闭此对话框
 */
StandardRecommendAdd.close = function () {
    parent.layer.close(window.parent.StandardRecommend.layerIndex);
};

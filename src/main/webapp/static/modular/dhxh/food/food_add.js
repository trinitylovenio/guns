/**
 *
 */
var FoodAdd = {
    id: "foodTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
};

/**
 * 提交添加用户
 */
FoodAdd.addSubmit = function () {
    var queryData = {};
    var FOOD_NAME = $("#FOOD_NAME").val();
    var FOOD_TYPE = $("#FOOD_TYPE").val();
    var FOOD_IMG = $("#FOOD_IMG").val();
    var FOOD_DES = $("#FOOD_DES").val();
    if(Feng.isEmpty(FOOD_NAME) || Feng.isEmpty(FOOD_IMG) || Feng.isEmpty(FOOD_DES)){
        return Feng.info("请先将数据填写完整")
    }else if(FOOD_DES.length>200){
        return Feng.info("描述最多200字符")
    } else{
        queryData["FOOD_NAME"]=FOOD_NAME;
        queryData["FOOD_TYPE"]=FOOD_TYPE;
        queryData["FOOD_IMG"]=FOOD_IMG;
        queryData["FOOD_DES"]=FOOD_DES;
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/food/mergeFood", function (data) {
            if(data.code==1){
                Feng.success("添加成功!");
                window.parent.Food.refresh();
                FoodAdd.close();
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

/**
 * 关闭此对话框
 */
FoodAdd.close = function () {
    parent.layer.close(window.parent.Food.layerIndex);
};

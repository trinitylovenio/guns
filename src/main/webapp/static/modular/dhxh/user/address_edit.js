/**
 *
 */
var AddressEdit = {
    id: "addressTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    PARENT_ID:-1,
};

/**
 * 提交添加用户
 */
AddressEdit.editSubmit = function () {
    var queryData = {};
    var name = $("#ADDRESS_NAME").val();
    var parent_id = $("#deptid").val();
    if(Feng.isEmpty(name) || Feng.isEmpty(parent_id)){
        return Feng.info("请先将数据填写完整")
    }else {
        queryData["ID"]=$("#ID").val();
        queryData["NAME"]=name;
        queryData["PARENT_ID"]=parent_id;
        queryData["TYPE"]=$("#TYPE").val();
        queryData["accurate_address"]=$("#accurate_address").val();
        queryData["lng_lat"]=$("#lng_lat").val();
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/user/mergeAddress", function (data) {
            if(data.code==1){
                Feng.success("添加成功!");
                window.parent.Address.refresh();
                AddressEdit.close();
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
AddressEdit.close = function () {
    parent.layer.close(window.parent.Address.layerIndex);
};


/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
AddressEdit.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};
/**
 * 显示部门选择的树
 *
 * @returns
 */
AddressEdit.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        AddressEdit.hideDeptSelectTree();
    }
}
/**
 * 隐藏部门选择的树
 */
AddressEdit.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

$(function () {
    var ADDRESS_TYPE = $("#TYPE").val();
    var STATUS_OLD =$("#STATUS_OLD").val();
    var ADDRESS_LEVEL =$("#ADDRESS_LEVEL").val();
    if(ADDRESS_LEVEL == 5 || ADDRESS_LEVEL == '5'){
        $("#TYPE_BOX").show();
        $("#ADDRESS_TYPE").val(ADDRESS_TYPE)
        $("#STATUS_BOX").show();
        $("#status").val(STATUS_OLD)
    }else if(ADDRESS_LEVEL == 6 || ADDRESS_LEVEL == '6'){
        $("#STATUS_BOX").show();
        $("#status").val(STATUS_OLD)
        $("#ACCURATE_BOX").show();
    }

    var ztree = new $ZTree("treeDemo", "/user/addressTree");
    ztree.bindOnClick(AddressEdit.onClickDept);
    ztree.init();
    instance = ztree;
});
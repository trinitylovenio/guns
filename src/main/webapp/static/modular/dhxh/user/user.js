/**
 * 角色管理的单例
 */
var User = {
    id: "userTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', sortable: false},
        {title: '用户名称', field: 'user_name', align: 'center', valign: 'middle', sortable: false},
        {title: '用户电话', field: 'telephone', align: 'center', valign: 'middle', sortable: false},
        {title: '用户昵称', field: 'nick_name', align: 'center', valign: 'middle', sortable: false},
        {title: '送达时间', field: 'service_time', align: 'center', valign: 'middle', sortable: false},
        {title: '商圈', field: 'NAME4', align: 'center', valign: 'middle', sortable: false},
        {title: '大厦/小区', field: 'NAME5', align: 'center', valign: 'middle', sortable: false},
        {title: '公司', field: 'NAME6', align: 'center', valign: 'middle', sortable: false},
        {title: '详细地址', field: 'accurate_address', align: 'center', valign: 'middle', sortable: false},
        {title: '头像', field: 'user_pic', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var user_pic = row.user_pic;
                if(user_pic==null || user_pic=='' || user_pic==undefined){
                    return '--'
                }
                var id = row.id;
                var img = '<div id="cutPic'+id+'"><img onclick="User.showPic(\''+id+'\')" src="'+user_pic+'" width="50" height="50"></div>'
                    +'<div id="pic'+id+'" hidden="hidden"><img onclick="User.hidePic(\''+id+'\')" src="'+user_pic+'"></div>';
                return img;
            }
        },
        {title: '用户类型', field: 'user_type', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var user_type = row.user_type;
                var dir={"-1":"测试用户","0":"初始用户","1":"注册用户","2":"试用用户","3":"付费用户"};
                return dir[user_type];
            }
        },
        {title: '用户状态', field: 'status', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var status = row.status;
                var dir={"1":"正常","-1":"失效"};
                return dir[status];
            }
        },
        {title: '用户注册时间', field: 'create_time', align: 'center', valign: 'middle', sortable: false},
        {title: '用户付费状态', field: 'charge_status', align: 'center', valign: 'middle', sortable: false,
            formatter:function (value,row) {
                var personal_charge_status = row.personal_charge_status;
                var group_charge_status = row.group_charge_status;
                if(personal_charge_status=='10' || group_charge_status =='10'){
                    return '付费';
                }else {
                    return '非付费';
                }
            }
        }
    ];
    return columns;
};

/**
 *点击缩略图查看大图
 */
User.showPic=function (ID) {
    $("#cutPic"+ID).hide();
    $("#pic"+ID).show();
}
/**
 *点击大图隐藏大图
 */
User.hidePic=function (ID) {
    $("#cutPic"+ID).show();
    $("#pic"+ID).hide();
}

/**
 * 检查是否选中
 */
User.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        User.seItem = selected[0];
        return true;
    }
};


/**
 * 搜索
 */
User.search = function () {
    var queryData = {};
    queryData["USER_NAME"]=$("#USER_NAME").val();
    queryData["SERVICE_TIME"]=$("#SERVICE_TIME").val();
    queryData["NAME5"]=$("#NAME5").val();
    queryData["NAME6"]=$("#NAME6").val();
    queryData["ACCURATE_ADDRESS"]=$("#ACCURATE_ADDRESS").val();
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["USER_TYPE"]=$("#USER_TYPE").val();
    User.table.server_init(queryData);
}

/**
 * 导出Excel表格
 */
User.Export = function () {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    if(Feng.isEmpty(beginTime) || Feng.isEmpty(endTime)){
        Feng.info("请先选择时间范围然后再导出表格")
    }else{
        window.location.href =Feng.ctxPath+ "/user/exportUserExcel/"+beginTime+"/"+endTime

    }
}


User.getBeginDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 08:00:00";
    return nowDay;
}

User.getEndDate=function(date){
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day)+" 13:00:00";
    return nowDay;
}


$(function () {
    //自动填充开始结束时间 为 昨天和今天
    var today = new Date();
    $("#beginTime").val(User.getBeginDate(today))
    $("#endTime").val(User.getEndDate(today))
    var queryData = {};
    queryData["beginTime"]=$("#beginTime").val();
    queryData["endTime"]=$("#endTime").val();
    queryData["USER_NAME"]=$("#USER_NAME").val();
    queryData["User_TYPE"]=$("#User_TYPE").val();
    queryData["ACCURATE_ADDRESS"]=$("#ACCURATE_ADDRESS").val();
    var defaultColunms = User.initColumn();
    var table = new BSTable(User.id, "/user/userList", defaultColunms);
    User.table = table.server_init(queryData);
});
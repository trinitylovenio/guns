/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author GUOBJ
 */
(function () {
    var BSTable = function (bstableId, url, columns) {
        this.btInstance = null;					//jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = Feng.ctxPath + url;
        this.method = "post";
        this.paginationType = "server";			//默认分页方式是服务器分页,可选项"client"
        this.pagination=true;
        this.showFooter = false;               //默认显示底栏
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.height = "";						//默认表格高度665
        this.data = {};
        this.queryParams = {}; // 向后台传递的自定义参数
        this.obj = null;
    };

    BSTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            this.btInstance =
                $('#' + tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    url: this.url,				//请求地址
                    method: this.method,		//ajax方式,post还是get
                    ajaxOptions: {				//ajax请求的附带参数
                        data: this.data
                    },
                    toolbar: "#" + this.toolbarId,//顶部工具条
                    striped: true,     			//是否显示行间隔色
                    cache: false,      			//是否使用缓存,默认为true
                    pagination: this.pagination,     		//是否显示分页（*）
                    sortable: false,      		//是否启用排序
                    sortOrder: "desc",     		//排序方式
                    pageNumber: 1,      			//初始化加载第一页，默认第一页
                    pageSize: 50,      			//每页的记录行数（*）
                    pageList: [10, 20, 50, 100, 200],  	//可供选择的每页的行数（*）
                    queryParamsType: 'limit', 	//默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    //queryParams: function (param) {
                    //    return $.extend(this.queryParams, param);
                    //}, // 向后台传递的自定义参数
                    queryParams: this.queryParams, // 向后台传递的自定义参数
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: false,      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    strictSearch: true,			//设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: true,     		//是否显示所有的列
                    showRefresh: false,     		//是否显示刷新按钮
                    minimumCountColumns: 2,    	//最少允许的列数
                    clickToSelect: true,    	//是否启用点击选中行
                    searchOnEnterKey: true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,		//列数组
                    detailView: false,
                    height: this.height,
                    icons: {
                        refresh: 'glyphicon-repeat',
                        toggle: 'glyphicon-list-alt',
                        columns: 'glyphicon-list'
                    },
                    iconSize: 'outline',
                    showFooter: this.showFooter
                });
            return this;
        },
        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {
            this.queryParams = param;
        },
        /**
         * 设置分页方式：server 或者 client
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },
        /**
         * 设置是否分页方式：true 或者 false
         */
        setPagination: function (flag) {
            this.pagination = flag;
        },
        /**
         * 设置是table底栏是否显示：true 或者 false
         */
        setShowFooter:function(flag){
            this.showFooter = flag;
        },
        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },
        /**
         * 设置ajax post请求时候附带的参数
         */
        setUrl: function (url) {
            this.url = Feng.ctxPath + url;
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query deliParams specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        },

        /**
         * 服务器端分页
         */
        setServicePaging: function () {
            this.paginationType = "server";
            this.queryParams = queryParams;
        },
        server_init : function (searchArgs) {
            var tableId = this.bstableId;
            this.obj = $('#' + tableId);
            //---先销毁表格 ---
            this.obj.bootstrapTable('destroy');
            this.btInstance = this.obj.bootstrapTable({
                contentType: "application/x-www-form-urlencoded",
                url: this.url,				//请求地址
                method: this.method,		//ajax方式,post还是get
                ajaxOptions: {				//ajax请求的附带参数
                    data: this.data
                },
                toolbar: "#" + this.toolbarId,//顶部工具条
                striped: true,     			//是否显示行间隔色
                cache: false,      			//是否使用缓存,默认为true
                pagination: true,     		//是否显示分页（*）
                sortarder: "desc",     		//排序方式
                pageble: false,      		//是否启用排序
                sortONumber: 1,      			//初始化加载第一页，默认第一页
                pageSize: 50,      			//每页的记录行数（*）
                pageList: [10, 20, 50, 100, 200],  	//可供选择的每页的行数（*）
                queryParamsType: 'limit', 	//默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                queryParams: function(params){
                    var param = {
                        limit: params.limit,
                        offset:params.offset,
                        sort: params.sort,  //排序列名
                        sortOrder: params.order//排位命令（desc，asc）
                    };
                    for(var key in searchArgs){
                        param[key]=searchArgs[key];
                    }
                    if(!Feng.isEmpty(param.beginTime) && !Feng.isEmpty(param.endTime)){
                        if(param.beginTime>=param.endTime){
                            Feng.info("开始时间必须小于结束数据")
                        }
                    }
                    if(!Feng.isEmpty(param.startTime) && !Feng.isEmpty(param.lastTime)){
                        if(param.startTime>=param.lastTime){
                            Feng.info("开始时间必须小于结束数据")
                        }
                    }
                    return param;
                },
                sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
                search: false,      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                strictSearch: true,			//设置为 true启用 全匹配搜索，否则为模糊搜索
                showColumns: true,     		//是否显示所有的列
                showRefresh: true,     		//是否显示刷新按钮
                minimumCountColumns: 2,    	//最少允许的列数
                clickToSelect: true,    	//是否启用点击选中行
                searchOnEnterKey: true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                columns: this.columns,		//列数组
                height: this.height,
                icons: {
                    refresh: 'glyphicon-repeat',
                    toggle: 'glyphicon-list-alt',
                    columns: 'glyphicon-list'
                },
                iconSize: 'outline',
                showFooter: this.showFooter
            });
            return this;
        }
    };

    window.BSTable = BSTable;

    function queryParams(params){
        return{
            //每页多少条数据
            limit: params.limit,
            //请求第几页
            offset:params.offset,
            sort: params.sort,  //排序列名
            sortOrder: params.order//排位命令（desc，asc）
        }
    }
}());


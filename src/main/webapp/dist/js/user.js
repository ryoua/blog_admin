document.write("<script type='text/javascript' src='../../plugins/kindeditor-4.1.10/kindeditor-all.js'></script>");
$(function () {
    //隐藏错误提示框
    $('.add-error-info').css("display", "none");
    $('.edit-error-info').css("display", "none");

    $("#jqGrid").jqGrid({
        url: 'users/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true},
            {label: '登录名', name: 'userName', index: 'userName', sortable: false, width: 80},
            {label: '添加时间', name: 'createTime', index: 'createTime', sortable: false, width: 80}
        ],
        height: 485,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: true,
        rownumWidth: 35,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

function userAdd() {
    //点击添加按钮后执行操作
    var modal = new Custombox.modal({
        content: {
            effect: 'fadein',
            target: '#modalAdd'
        }
    });
    modal.open();
}

function userEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }

    $('#userId').val(id);

    //点击编辑按钮后执行操作
    var modal = new Custombox.modal({
        content: {
            effect: 'fadein',
            target: '#modalEdit'
        }
    });
    modal.open();
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObjectForAdd()) {
        //一切正常后发送网络请求
        //ajax
        var userName = $("#userName").val();
        var password = $("#password").val();
        var data = {"userName": userName, "password": password};
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'users/save',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (request) {
                //设置header值
                request.setRequestHeader("token", getCookie("token"));
            },
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                checkResultCode(result.resultCode);
                if (result.resultCode == 200) {
                    closeModal();
                    alert("保存成功");
                    //reload
                    reload();
                }
                else {
                    closeModal();
                    alert(result.message);
                }
                ;
            },
            error: function () {
                reset();
                alert("操作失败");
            }
        });

    }
});

//绑定modal上的编辑按钮
$('#editButton').click(function () {
    //验证数据
    if (validObjectForEdit()) {
        //一切正常后发送网络请求
        var password = $("#passwordEdit").val();
        var id = $("#userId").val();
        var data = {"id": id, "password": password};
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: 'users/updatePassword',//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (request) {
                //设置header值
                request.setRequestHeader("token", getCookie("token"));
            },
            success: function (result) {
                checkResultCode(result.resultCode);
                console.log(result);//打印服务端返回的数据
                if (result.resultCode == 200) {
                    closeModal();
                    alert("修改成功");
                    //reload
                    reload();
                }
                else {
                    closeModal();
                    alert(result.message);
                }
                ;
            },
            error: function () {
                reset();
                alert("操作失败");
            }
        });

    }
});


//添加Modal关闭
$('#cancelAdd').click(function () {
    closeModal();
})

//编辑Modal关闭
$('#cancelEdit').click(function () {
    closeModal();
})

/**
 * 数据验证
 */
function validObjectForAdd() {
    var userName = $('#userName').val();
    if (isNull(userName)) {
        showErrorInfo("用户名不能为空!");
        return false;
    }
    if (!validUserName(userName)) {
        showErrorInfo("请输入符合规范的用户名!");
        return false;
    }
    var password = $('#password').val();
    if (isNull(password)) {
        showErrorInfo("密码不能为空!");
        return false;
    }
    if (!validPassword(password)) {
        showErrorInfo("请输入符合规范的密码!");
        return false;
    }
    return true;
}

/**
 * 数据验证
 */
function validObjectForEdit() {
    var userId = $('#userId').val();
    if (isNull(userId) || userId < 1) {
        showErrorInfo("数据错误！");
        return false;
    }
    var password = $('#passwordEdit').val();
    if (isNull(password)) {
        showErrorInfo("密码不能为空!");
        return false;
    }
    if (!validPassword(password)) {
        showErrorInfo("请输入符合规范的密码!");
        return false;
    }
    return true;
}

/**
 * 关闭modal
 */
function closeModal() {
    //关闭前清空输入框数据
    reset();
    Custombox.modal.closeAll();
}

/**
 * 重置
 */
function reset() {
    //隐藏错误提示框
    $('.add-error-info').css("display", "none");
    $('.edit-error-info').css("display", "none");
    //清空数据
    $('#password').val('');
    $('#passwordEdit').val('');
    $('#userName').val('');
}

/**
 * jqGrid重新加载
 */
function reload() {
    reset();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}
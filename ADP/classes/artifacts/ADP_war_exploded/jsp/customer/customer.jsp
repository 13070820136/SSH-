<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
    <script>
        $(function () {
            addClose();
            editClose();
            $('#test').datagrid({
                title: '员工列表',
                iconCls: 'icon-save',
                width: 950,
                height: 550,
                nowrap: true,
                striped: true,
                rownumbers: true,
                url: '<%=request.getContextPath()%>/customerList',
                sortName: 'managerId',
                singleSelect: true,
                sortOrder: 'desc',
                idField: 'managerId',
                frozenColumns: [[
                    {field: 'ck', checkbox: true},
                    //   {title:'编号',field:'managerId',width:80,align:'center',sortable:true},
                    {title: '用户名', field: 'name', width: 120, align: 'center'},
                    //	{title:'密码',field:'password',width:120,align:'center',rowspan:2},
                    {title: '角色', field: 'role', width: 120, align: 'center', rowspan: 2},
                    {title: '状态', field: 'status', width: 120, align: 'center', rowspan: 2},
                ]],
                columns: [[
                    {
                        field: 'opt', title: '操作', width: 100, align: 'center', rowspan: 2,
                        formatter: function (value, rec) {
                            return '<span style="color:red"><a href="javascript:editStaff()">编辑</a> <a href="javascript:delStaff();">删除</a> </span>';
                        }
                    }
                ]],

                toolbar: [{
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        addOpen();
                    }
                }, {
                    text: '编辑',
                    iconCls: 'icon-edit',
                    handler: function () {
                        editStaff();
                    }
                }, {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        delStaff();
                    }
                }
                ],

                pagination: true,

            });

            var p = $('#test').datagrid('getPager');
            if (p) {
                $(p).pagination({
                    onBeforeRefresh: function () {
                        $('#pp').pagination('refresh');
                    }
                });
            }
        });
        function resize() {
            $('#test').datagrid({
                title: 'New Title',
                striped: true,
                width: 650,
                queryParams: {
                    p: 'param test',
                    name: 'My Name'
                }
            });
        }
        function getSelected() {
            var selected = $('#test').datagrid('getSelected');
            alert(selected.managerId + ":" + selected.name);
        }
        function getSelections() {
            var ids = [];
            var rows = $('#test').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].name);
            }
            alert(ids.join(':'))
        }

        function optfresh() {
            $('#test').datagrid('reload');
            $('#test').datagrid("acceptChanges");
        }

        function addOpen() {
            $('#add').window('open');
        }
        function delStaff() {
            var selected = $('#test').datagrid('getSelected');
            $.messager.confirm('系统提示', '您确定要删除' + selected.name + '吗?', function (r) {
                if (r) {
                    $.post('mgrdel?managerId=' + selected.managerId, function (msg) {
                        if (msg == 'ok') {
                            msgShow('系统提示', '删除成功', 'info');
                        } else {
                            msgShow('系统提示', '删除失败', 'info');
                        }
                        optfresh();
                    });
                }
            });
        }
        function editStaff() {
            var selected = $('#test').datagrid('getSelected');
            if (selected == null) {
                msgShow('系统提示', '请选择编辑项', 'info');
            } else {
                editOpen();
                var p = selected.password;
                $('#ee').form('load', {
                    managerId: selected.managerId,
                    name: selected.name,
                    password: selected.password,
                    rpwd1: selected.password,
                    role: selected.role,
                    status: selected.status,
                    descrition: selected.descrition
                });
            }
        }
        function addClose() {
            $('#add').window('close');
        }

        function editOpen() {
            $('#edit').window('open');
        }

        function editClose() {
            $('#edit').window('close');
        }

        function submitForm() {
            var name = $('#name').val();
            var pwd = $('#pwd').val();
            var rpwd = $('#rpwd').val();
            if (name.length < 6 || name.length > 20) {
                msgShow('系统提示', '用户名6-20个字符', 'info');
            } else if (pwd.length < 6 || pwd.length > 20) {
                msgShow('系统提示', '密码6-20个字符', 'info');
            } else if (pwd != rpwd) {
                msgShow('系统提示', '两次输入密码不一致', 'info');
            } else {
                $.messager.progress();	// display the progress bar
                $('#ff').form('submit', {
                    success: function () {
                        $.messager.progress('close');	// hide progress bar while submit successfully
                        $('#name').val('');
                        $('#pwd').val('');
                        $('#rpwd').val('');
                        addClose();
                        msgShow('系统提示', '添加成功', 'info');
                        optfresh();
                    }
                });
            }
        }

        function clearForm() {
            $('#ff').form('clear');
        }

        function submitEdit() {
            var name = $('#name1').val();
            var pwd = $('#pwd1').val();
            var rpwd = $('#rpwd1').val();
            if (name.length < 6 || name.length > 20) {
                msgShow('系统提示', '用户名6-20个字符', 'info');
            } else if (pwd.length < 6 || pwd.length > 20) {
                msgShow('系统提示', '密码6-20个字符', 'info');
            } else if (pwd != rpwd) {
                msgShow('系统提示', '两次输入密码不一致', 'info');
            } else {
                $.messager.progress();	// display the progress bar
                $('#ee').form('submit', {
                    success: function () {
                        $.messager.progress('close');	// hide progress bar while submit successfully
                        editClose();
                        msgShow('系统提示', '修改成功', 'info');
                        optfresh();
                    }
                });
            }
        }

        function cancelEdit() {
            editClose();
        }

        //弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
        function msgShow(title, msgString, msgType) {
            $.messager.alert(title, msgString, msgType);
        }

        $.extend($.fn.validatebox.defaults.rules, {
            equals: {
                validator: function (value, param) {
                    return value == $(param[0]).val();
                },
                message: '请输出相同的密码.'
            }
        });

    </script>

</head>


<body>
<table id="test"></table>
<div id="add" class="easyui-window" title="添加管理员" style="width:400px;height:300px"
     data-options="iconCls:'icon-add',modal:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" action="mgradd" method="post">
            <table cellpadding="5">
                <tr>
                    <td>用户名:</td>
                    <td><input id="name" class="easyui-validatebox textbox" type="text" name="name" maxlength="20"
                               validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填',invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input id="pwd" name="password" type="password" class="easyui-validatebox textbox"
                               maxlength="20" validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填' ,invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input id="rpwd" name="rpwd" type="password" class="easyui-validatebox textbox" maxlength="20"
                               missingMessage="请以上重复密码" required="required" validType="equals['#pwd']"/></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td>
                        <select class="easyui-combobox" name="role">
                            <option value="superManager">超级管理员</option>
                            <option value="manager" selected>管理员</option>
                        </select>
                    </td>
                </tr>
            </table>
            <br>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">添加</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
            </div>
        </form>
    </div>
</div>

<div id="edit" class="easyui-window" title="编辑管理员" style="width:400px;height:400px"
     data-options="iconCls:'icon-edit',modal:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="ee" action="mgrUpdateInfo" method="post">
            <table cellpadding="5">
                <tr>
                    <td>用户名:</td>
                    <td><input id="managerId" class="easyui-validatebox textbox" type="hidden" name="managerId"/>
                        <input id="name1" class="easyui-validatebox textbox" type="text" name="name" maxlength="20"
                               validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填',invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input id="pwd1" name="password" type="password" class="easyui-validatebox textbox"
                               maxlength="20" validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填' ,invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input id="rpwd1" name="rpwd1" type="password" class="easyui-validatebox textbox" maxlength="20"
                               missingMessage="请以上重复密码" required="required" validType="equals['#pwd1']"/></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td>
                        <select class="easyui-combobox" name="role">
                            <option value="superManager">超级管理员</option>
                            <option value="manager">管理员</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td>
                        <select class="easyui-combobox" name="status">
                            <option value="NORMAL">正常</option>
                            <option value="FORBID">禁用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>描述:</td>
                    <td><input id="descrition" class="easyui-validatebox textbox" name="descrition" type="text"
                               maxlength="20"/></td>
                </tr>
            </table>
            <br>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()">修改</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelEdit()">取消</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>

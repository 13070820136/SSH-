<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户列表</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
    <script>
        $(function () {
            addClose();
            editClose();
            $('#test').datagrid({
                title: '客户列表',
                iconCls: 'icon-save',
                width: 880,
                height: 550,
                nowrap: true,
                striped: true,
                rownumbers: true,
                url: '<%=request.getContextPath()%>/customerList',
                sortName: 'customerId',
                singleSelect: true,
                sortOrder: 'desc',
                idField: 'customerId',
                columns: [[
                    {field: 'ck', checkbox: true},
                    {title: '账号', field: 'account', width: 100, align: 'center'},
                    {title: '密码', field: 'password', width: 100, align: 'center', rowspan: 2},
                    {title: '姓名', field: 'name', width: 50, align: 'center', rowspan: 2},
                    {
                        field: 'size', title: '性别', width: 100, align: 'center', rowspan: 2,
                        formatter: function (value, rowData, rowIndex) {
                            if (value == 0) {
                                return "女";
                            } else if (value == 1) {
                                return "男";
                            }
                        }
                    },
                    {title: '年龄', field: 'age', width: 50, align: 'center', rowspan: 2},
                    {title: '手机号', field: 'phone', width: 100, align: 'center', rowspan: 2},
                    {title: '状态', field: 'status', width: 100, align: 'center', rowspan: 2},
                    {
                        title: '创建时间', field: 'createTime', width: 120, align: 'center', rowspan: 2,
                        formatter: function (value, rowData, rowIndex) {
                            return new Date(parseInt(value) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                        }
                    },
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
            $.messager.confirm('系统提示', '您确定要删除' + selected.account + '吗?', function (r) {
                if (r) {
                    $.post('customerDel?customerid=' + selected.customerid, function (msg) {
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
                    customerid: selected.customerid,
                    account: selected.account,
                    password: selected.password,
                    name: selected.name,
                    size: selected.size,
                    age: selected.age,
                    phone: selected.phone,
                    status: selected.status
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
            $.messager.progress();	// display the progress bar
            $('#ff').form('submit', {
                success: function (result) {
                    $.messager.progress('close');
                    $('#account').val('');
                    $('#password').val('');
                    $('#name').val('');
                    $('#age').val('');
                    $('#phone').val('');
                    if (result == 102) {
                        msgShow('系统提示', '添加失败', 'info');
                    } else {
                        msgShow('系统提示', '添加成功', 'info');
                    }
                    addClose();
                    optfresh();
                }
            });
        }

        function clearForm() {
            $('#ff').form('clear');
        }

        function submitEdit() {
            $.messager.progress();	// display the progress bar
            $('#ee').form('submit', {
                success: function (result) {
                    $.messager.progress('close');
                    if (result == 102) {
                        msgShow('系统提示', '编辑失败', 'info');
                    } else {
                        msgShow('系统提示', '编辑成功', 'info');
                    }
                    editClose();
                    optfresh();
                }
            });
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
<div id="add" class="easyui-window" title="添加客户" style="width:400px;height:300px"
     data-options="iconCls:'icon-add',modal:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" action="customerAdd" method="post">
            <table cellpadding="5">
                <tr>
                    <td>账号:</td>
                    <input type="hidden" name="customerid"/>
                    <td><input id="account" class="easyui-validatebox textbox" type="text" name="account" maxlength="20"
                               validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填',invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input id="password" name="password" type="password" class="easyui-validatebox textbox"
                               maxlength="20" validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填' ,invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input id="name" name="name" type="text" class="easyui-validatebox textbox"
                               maxlength="20"
                               data-options="required:true,missingMessage:'必填'"/></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <select class="easyui-combobox" name="size">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td><input id="age" name="age" type="text" class="easyui-numberbox "
                               maxlength="3" onkeyup="value=value.replace(/\D/g,'')"/></td>
                </tr>
                <tr>
                    <td>手机号:</td>
                    <td><input id="phone" name="phone" type="text" maxlength="11"
                               onkeyup="value=value.replace(/\D/g,'')"></td>
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

<div id="edit" class="easyui-window" title="编辑客户" style="width:400px;height:400px"
     data-options="iconCls:'icon-edit',modal:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="ee" action="customerUpdateInfo" method="post">
            <table cellpadding="5">
                <tr>
                    <td>账号:</td>
                    <input type="hidden" name="customerid"/>
                    <td><input id="account0" class="easyui-validatebox textbox" type="text" name="account"
                               maxlength="20"
                               validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填',invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input id="password0" name="password" type="password" class="easyui-validatebox textbox"
                               maxlength="20" validType="length[6,20]"
                               data-options="required:true,missingMessage:'必填' ,invalidMessage:'6-20个字符' "/></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input id="name0" name="name" type="text" class="easyui-validatebox textbox"
                               maxlength="20"
                               data-options="required:true,missingMessage:'必填'"/></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <select id="size0" class="easyui-combobox" name="size">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td><input id="age0" name="age" type="text" class="easyui-numberbox "
                               maxlength="3" onkeyup="value=value.replace(/\D/g,'')"/></td>
                </tr>
                <tr>
                    <td>手机号:</td>
                    <td><input id="phone0" name="phone" type="text" maxlength="11"
                               onkeyup="value=value.replace(/\D/g,'')"></td>
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

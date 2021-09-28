<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/common_head.jsp" %>
    <script>
        jQuery(function ($) {
            // 请求后端接口获取列表数据
            function list() {
                $.ajax({
                    url: "/type/types.json",
                    success(types) {
                        var html = "";
                        $(types).each(function (index) {
                            // 使用this来获取遍历的对象
                            html += '<tr class="' + (index % 2 == 0 ? "active" : "") + '">';
                            html += '	<td><input value="'+this.id+'" name="id" type="checkbox" /></td>';
                            html += '	<td>' + (index + 1) + '</td>';
                            html += '	<td>' + this.id + '</td>';
                            html += '	<td>' + this.name + '</td>';
                            html += '	<td>' + this.description + '</td>';
                            html += '</tr>';
                        })
                        $("#dataBody").html(html);
                    }
                })
            }
            list();

            $("#saveBtn").click(function () {
                $.ajax({
                    url: "/type/save.do",
                    type: "post",
                    data: {
                        id: $("#create-id").val(),
                        name: $("#create-name").val(),
                        description: $("#create-description").val()
                    },
                    success(data) {
                        if(data.success) {
                            // 关闭窗口、刷新列表
                            $("#createTypeModal").modal('hide');
                            list();
                            // 清空输入框中的值
                            $("#create-id").val("");
                            $("#create-name").val("");
                            $("#create-description").val("");
                        }
                        alert(data.msg);
                    }
                })
            })
        
            $("#toEditBtn").click(function () {
                // 获取name为id、选中的复选框
                var $cks = $(":checkbox[name=id]:checked");
                if ($cks.size() != 1) {
                    alert("必须且只能选择一个！");
                    return false;
                }
                $.ajax({
                    url: "/type/type.json?id="+$cks.val(),
                    success(type) {
                        $("#edit-id").val(type.id);
                        $("#edit-name").val(type.name);
                        $("#edit-description").val(type.description);
                    }
                })
            })

            $("#updateBtn").click(function () {
                $.ajax({
                    url: "/type/update.do",
                    type: "put",
                    data: {
                        id: $("#edit-id").val(),
                        name: $("#edit-name").val(),
                        description: $("#edit-description").val()
                    },
                    success(data) {
                        if(data.success) {
                            // 关闭窗口、刷新列表
                            $("#editTypeModal").modal('hide');
                            list();
                        }
                        alert(data.msg);
                    }
                })
            })
            
            $("#delBtn").click(function () {
                // 获取选中的复选框
                var $cks = $(":checkbox[name=id]:checked");
                if($cks.size() == 0) {
                    alert("请选择删除项！");
                    return ;
                }
                if (!confirm("确认删除吗？")) return ;

                var ids = "";
                $cks.each(function () {
                    ids += "," + this.value;
                });

                // ",1,2,3" ==> "1,2,3"
                ids = ids.substring(1);

                // 发送ajax进行检测！如果所有选项都没有投入使用，
                // 则可以删除，如果有使用中的，则不给删除，并给出提示
                $.ajax({
                    url: "/value/xxx?ids="+ids, // 查询哪些是使用中的
                    success(using) {
                        if (using.length == 0) {
                            del();
                        } else {
                            alert("选择的"+using+"正在投入使用，不可删除！");
                        }
                    }
                })

                function del() {
                    $.ajax({
                        url: "/type/delete.do?ids="+ids,
                        type: "delete",
                        success(data) {
                            if(data.success) {
                                list();
                            }
                            alert(data.msg);
                        }
                    })
                }


            })
        })
    </script>
</head>
<body>

<%--添加字典类型--%>
<div class="modal fade" id="createTypeModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-plus"></span> 新增字典类型</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-id" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-id" style="width: 200%;" placeholder="编码作为主键，不能是中文">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-name" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-name" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <textarea class="form-control" rows="3" id="create-description" style="width: 200%;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<%--修改字典类型--%>
<div class="modal fade" id="editTypeModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-plus"></span> 修改字典类型</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="edit-id" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" readonly id="edit-id" style="width: 200%;" placeholder="编码作为主键，不能是中文">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-name" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <textarea class="form-control" rows="3" id="edit-description" style="width: 200%;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="updateBtn" type="button" class="btn btn-primary">修改</button>
            </div>
        </div>
    </div>
</div>

<div>
    <div style="position: relative; left: 30px; top: -10px;">
        <div class="page-header">
            <h3>字典类型列表</h3>
        </div>
    </div>
</div>
<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
    <div class="btn-group" style="position: relative; top: 18%;">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createTypeModal">
            <span class="glyphicon glyphicon-plus"></span> 创建
        </button>
        <button id="toEditBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#editTypeModal"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button id="delBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
</div>
<div style="position: relative; left: 30px; top: 20px;">
    <table class="table table-hover">
        <thead>
        <tr style="color: #B3B3B3;">
            <td><input type="checkbox"/></td>
            <td>序号</td>
            <td>编码</td>
            <td>名称</td>
            <td>描述</td>
        </tr>
        </thead>
        <tbody id="dataBody">
        <tr class="active">
            <td><input type="checkbox"/></td>
            <td>1</td>
            <td>sex</td>
            <td>性别</td>
            <td>性别包括男和女</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
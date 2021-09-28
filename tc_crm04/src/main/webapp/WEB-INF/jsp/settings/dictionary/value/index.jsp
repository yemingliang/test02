<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/common_head.jsp" %>
    <script>
        jQuery(function ($) {
            $.ajax({
                url: "/type/types.json",
                success(types) {
                    // 保留前1个选项
                    $("#create-dicTypeCode").prop("length", 1);
                    var html = "";
                    $(types).each(function () {
                        html += "<option value='"+this.id+"'>"+this.name+"</option>";
                    });
                    $("#create-dicTypeCode").append(html);
                }
            })
        })
    </script>
</head>
<body>

<%--添加--%>
<div class="modal fade" id="createModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-plus"></span> 新增字典值</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-dicTypeCode" style="width: 200%;">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-dicValue" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-text" class="col-sm-2 control-label">文本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-text" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-orderNo" style="width: 200%;">
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

<%--修改--%>
<div class="modal fade" id="editModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-plus"></span> 修改字典值</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="edit-dicTypeCode" class="col-sm-2 control-label">字典类型编码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-dicTypeCode" style="width: 200%;" value="性别" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-dicValue" style="width: 200%;" value="m">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-text" class="col-sm-2 control-label">文本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-text" style="width: 200%;" value="男">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-orderNo" class="col-sm-2 control-label">排序号</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-orderNo" style="width: 200%;" value="1">
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
            <h3>字典值列表</h3>
        </div>
    </div>
</div>
<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
    <div class="btn-group" style="position: relative; top: 18%;">
        <button id="createBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#createModal">
            <span class="glyphicon glyphicon-plus"></span> 创建
        </button>
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editModal"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
</div>
<div style="position: relative; left: 30px; top: 20px;">
    <table class="table table-hover">
        <thead>
        <tr style="color: #B3B3B3;">
            <td><input type="checkbox"/></td>
            <td>序号</td>
            <td>字典值</td>
            <td>文本</td>
            <td>排序号</td>
            <td>字典类型编码</td>
        </tr>
        </thead>
        <tbody>
        <tr class="active">
            <td><input type="checkbox"/></td>
            <td>1</td>
            <td>m</td>
            <td>男</td>
            <td>1</td>
            <td>sex</td>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td>2</td>
            <td>f</td>
            <td>女</td>
            <td>2</td>
            <td>sex</td>
        </tr>
        <tr class="active">
            <td><input type="checkbox"/></td>
            <td>3</td>
            <td>1</td>
            <td>一级部门</td>
            <td>1</td>
            <td>orgType</td>
        </tr>
        <tr>
            <td><input type="checkbox"/></td>
            <td>4</td>
            <td>2</td>
            <td>二级部门</td>
            <td>2</td>
            <td>orgType</td>
        </tr>
        <tr class="active">
            <td><input type="checkbox"/></td>
            <td>5</td>
            <td>3</td>
            <td>三级部门</td>
            <td>3</td>
            <td>orgType</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
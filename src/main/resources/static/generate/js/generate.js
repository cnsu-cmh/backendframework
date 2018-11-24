var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

$('#form').bootstrapValidator();
$("#detail").hide();
$("#save").hide();

$("#showInfo").click(function(){
    var bootstrapValidator = $("#form").data('bootstrapValidator');
    bootstrapValidator.validate();
    if(!bootstrapValidator.isValid()){
        return;
    }
    var beanFields = $("#beanFields");
    beanFields.html("");
    $.ajax({
        type : 'get',
        url : '/generate',
        data : "tableName=" + $("#tableName").val(),
        success : function(data) {
            $("#detail").show();
            $("#save").show();

            var beanName = data.beanName;
            $("#beanName").val(beanName);
            $("#mapperName").val(beanName + "Mapper");
            $("#serviceName").val(beanName + "Service");
            $("#serviceImplName").val(beanName + "ServiceImpl");
            $("#controllerName").val(beanName + "Controller");
            var fields = data.fields;
            var length = fields.length;

            for(var i=0;i<length;i++){
                var f = fields[i];
                var tr = $("<tr></tr>");

                var columnName = f['columnName'];
                var columnNameInput = "<input type='hidden' name='columnNames' value='" + columnName +"'>"

                tr.append("<td>" + columnNameInput + columnName + "</td>");
                tr.append("<td>" + f['columnType'] + "</td>");
                tr.append("<td>" + f['columnComment'] + "</td>");
                var name = f['name'];
                var nameInput = "<input name='beanFieldName' class='form-control' value='" + name +"'>";
                tr.append("<td>" + nameInput + "</td>");

                var type = f['type'];
                var typeInput = "<input name='beanFieldType' class='form-control' value='" + type +"'>"
                tr.append("<td>" + typeInput + "</td>");

                var val = f['columnDefault'];
                var valInput = "<input name='beanFieldValue' class='form-control' value='" + val +"'>"
                tr.append("<td>" + valInput + "</td>");

                beanFields.append(tr);
            }
        }
    });
});

$("#save").click(function(){

    var bootstrapValidator = $("#form").data('bootstrapValidator');
    bootstrapValidator.validate();
    if(!bootstrapValidator.isValid()){
        return;
    }
    if($("#detail").is(':hidden')) {
        return;
    }

    var formdata = $("#form").serializeObject();

    $.ajax({
        type : 'post',
        url : '/generate',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(formdata),
        success : function(data) {
            $("#detail").hide();
            $("#save").hide();
            layer.msg("生成成功");
        }
    });
});

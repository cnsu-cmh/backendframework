var layer;
layui.use('layer', function(){
    layer = layui.layer;
});
function init(){
    $.ajax({
        type : 'get',
        url : '/users/current',
        async : false,
        data : $("#form").serialize(),
        success : function(data) {
            $("#username").val(data.username);
        }
    });

}

init();

function update() {
    $('#form').bootstrapValidator();
    var bootstrapValidator = $("#form").data('bootstrapValidator');
    bootstrapValidator.validate();
    if(!bootstrapValidator.isValid()){
        return;
    }

    $.ajax({
        type : 'put',
        url : '/users/'+$("#username").val(),
        data : $("#form").serialize(),
        success : function(data) {
            layer.msg("修改成功", {shift: -1, time: 1000}, function(){
                deleteCurrentTab();
            });
        }
    });
}
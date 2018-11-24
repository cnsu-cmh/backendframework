var layer;
layui.use([ 'layer' ], function() {
    layer = layui.layer;
});

var userStatus = showDictSelect("status", "userStatus", true);

var pers = checkPermission();

var example;

function init(){
    example =
        $('#dt-table').DataTable({
            "searching": false,
            "processing": false,
            "serverSide" : true,
            "language": {
                "url": "/static/js/plugin/datatables/Chinese.lang"
            },
            "ajax": {
                "url" : "/users",
                "type": "get",
                "data": function(d){
                    d.username = $('#username').val();
                    d.nickname = $('#nickname').val();
                    d.status = $('#status').val();
                },
                "error":function(xhr, textStatus, errorThrown){
                    var msg = xhr.responseText;
                    console.log(msg)
                }
            },
            "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-10 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-10' p v>>",
            "columns": [
                { "data": "username", "defaultContent": ""},
                { "data": "nickname", "defaultContent": ""},
                { "data": "phone", "defaultContent": ""},
                { "data": "email", "defaultContent": ""},
                {
                    "data": "status",
                    "defaultContent": "",
                    "render": function (data, type, row) {
                        return userStatus[data];
                    }
                },
                {
                    "data": "",
                    "defaultContent": "",
                    "orderable":false,
                    "render": function (data, type, row) {
                        var id = row['id'];
                        var href = "/pages/user/updateUser?id=" + id;
                        var edit = buttonEdit(href, "sys:user:add", pers);
                        return edit;
                    }
                },

            ],
            "order": [[ 0, "desc" ],[1, "asc"]]
        } );
}

$("#searchBt").click(function(){
    example.ajax.reload();
});

init();
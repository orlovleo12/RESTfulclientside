$(function () {

    function roleToStr(roles) {
        let strRole = '';
        for (let i = 0; i < roles.length; i++) {
            strRole = strRole + roles[i].name + ' ';
        }
        return strRole.substring(0, strRole.length - 1);
    }

    $.ajax({
        url: '/rest/read',
        contentType: 'application/json; charset=UTF-8;',
        mimeType: 'application/json',
        type: 'GET',
        dataType: 'JSON',
        success: function (user) {
            $("#id").text(user.id);
            $("#username").text(user.username);
            $("#email").text(user.email);
            $("#password").text(user.password);
            $("#roles").text(roleToStr(user.roles));
        }
    });
});



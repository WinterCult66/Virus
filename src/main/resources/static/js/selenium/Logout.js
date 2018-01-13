$(document).ready(function () {
    $("#logout").on("submit", function (event) {
        event.preventDefault();
        var data = ($(this).serialize());                     
        if (data !== undefined && data !== null) {            
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/logoutpost",
                data: data
            }).done(function (msg) {
                $('#modal-1').modal('hide');
                $.sweetModal({
                    title: 'LOGOUT RESPONSE',
                    content: msg.data,
                    theme: $.sweetModal.THEME_DARK
                });
                $('.ajax-loading').hide(1);
            }).success(function (data) {                
                console.log(data.data);
                var result = $(data.data).find("Version").text();
                console.log(result);
            }).error(function (error) {
                console.log("ERROR: " +error)
            });
        }

    });
});
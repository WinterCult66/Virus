$(document).ready(function () {
    $("#selenium").on("submit", function (event) {
        event.preventDefault();
        var data = ($(this).serialize()); 
        if (data !== undefined && data !== null) {            
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/loginselenium",
                data: data
            }).done(function () {
                $('#modal-7').modal('hide');
                $('#modal-8').modal('show');
                
//                $.sweetModal({
//                    title: 'SELENIUM RESPONSE',
//                    content: 'D:\imagenes\End Task.png',
//                    //content: msg.data,
//                    theme: $.sweetModal.THEME_DARK
//                });
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



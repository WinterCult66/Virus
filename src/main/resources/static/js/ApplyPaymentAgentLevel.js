$(document).ready(function () {
    $("#applypaymentagentlevel").on("submit", function (event) {
        event.preventDefault();
        var data = ($(this).serialize()); 
        if (data !== undefined && data !== null) {            
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/applypaymentagentlevelpost",
                data: data
            }).done(function (msg) {
                $('#modal-6').modal('hide');
                $.sweetModal({
                    title: 'APPLYPAYMENTAGENTLEVEL RESPONSE',
                    content: msg.data,
                    theme: $.sweetModal.THEME_DARK
                });
                $('.ajax-loading').hide(1);
            }).success(function (data) {                
                console.log(data.data);
                console.log(result);
            }).error(function (error) {
                console.log("ERROR: " +error)
            });
        }
    });
});
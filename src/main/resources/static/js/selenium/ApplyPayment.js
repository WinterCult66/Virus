$(document).ready(function () {
    $("#applypayment").on("submit", function (event) {
        event.preventDefault();
        var data = ($(this).serialize()); 
        if (data !== undefined && data !== null) {            
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/applypaymentpost",
                data: data
            }).done(function (msg) {
                $('#modal-1').modal('hide');
                $.sweetModal({
                    title: 'APPLYPAYMENT RESPONSE',
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
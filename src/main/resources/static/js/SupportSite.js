$(document).ready(function () {
    $("#ss").on("submit", function (event) {
        event.preventDefault();  
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/testing",                
            })        
    });
});
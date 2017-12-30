$(document).ready(function () {
console.log("TTTTTTTTTTTTTTTTEST");
    $("#btnSubmit").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        // Get form
        var form = $('#fileUploadForm')[0];
        // Create an FormData object
        var data = new FormData(form);

        // disabled the submit button
        $("#btnSubmit").prop("disabled", true);

        if (data !== undefined && data !== null) {
            $('.ajax-loading').show(10);
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/methods/api/selenium/dynamic",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000
            }).done(function (msg) {                
                if (msg !== undefined && msg !== null &&
                        msg.gs !== undefined && msg.gs !== null) {
                    console.log("gs : " + msg.gs);
                    var pathImages = msg.gs.replace("[", "").replace("]", "").split(",");
                    for (var i = 0; i < pathImages.length; i++) {
                        var emidaImage = pathImages[i].trim().replace("\\", "/");
                        var npath = (pathImages.length);
                        var name = emidaImage.split("/");
                        var carouselClass = (i == 0) ? "carousel-item active" : "carousel-item";
                        console.log("emidaImage " + emidaImage);
                        console.log("npath " + npath);
                        console.log("name " + name);
                        console.log("carouselClass " + carouselClass);
                        $("#ol").append("<li data-target='#carousel-keyboard' data-slide-to='" + npath + "' class='active'></li>");
                        $("#automationImages").append("<div class='" + carouselClass + "'><center><img src='" + emidaImage + "' style='width:100%'  /></center> <div class='carousel-caption'><h3>" + name[name.length - 1] + "</h3></div></div>");
                    }
                }
                $('#modal-2').modal('show');
                msg.gs = null;
                console.log("SUCCES");
                $('.ajax-loading').hide(1);
            }).error(function (error) {
                console.log("ERROR: " + error);
            });
        }
    });

});
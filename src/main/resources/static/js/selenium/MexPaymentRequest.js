$(document).ready(function () {

    formRandom();

    $('#modal-3').on('shown.bs.modal', function () {
        formRandom();
    });

    function formRandom() {
        //Start Var Random
        var account = Math.round(Math.random() * 9999999999999999);
        var day = Math.round((Math.random() * 30) + 1);
        var month = Math.round((Math.random() * 11) + 1);
        var value = Math.round(Math.random() * 200);
        //End Var Random

        //Restart Div 
        $("#acco").html("");
        $("#dat").html("");
        $("#valu").html("");

        //Start Var Input

        var accountField = "<label for='exampleInputEmail1'>Account</label> <input type='text' class='form-control' id='acc' name ='acc' placeholder='Enter Account' value ='" + account + "' />";
        var date = "<label for='exampleInputEmail1'>Date</label> <input type='text' class='form-control' id='date' name ='date' placeholder='Enter Date' value ='" + day + '/' + month + '/2017' + "' />";
        var valueField = "<label for='exampleInputEmail1'>Value</label><input type='text' class='form-control' id='val' name ='val' placeholder='Enter Value' value ='" + value + "'/>";
        //End Var Input        

        //Start Set Fields
        $("#acco").append(accountField);
        $("#dat").append(date);
        $("#valu").append(valueField);
        //End  Set Fields
    }


    $("#paymentrequest").on("submit", function (event) {
        event.preventDefault();
        var us = $('#us').val();
        var pas = $('#pas').val();
        var acc = $('#acc').val();
        var date = $('#date').val();
        var val = $('#val').val();


        $('.ajax-loading').show(10);
        $.ajax({
            method: "POST",
            url: "/methods/api/selenium/mexpaymentrequest",
            data: {
                us: us,
                pas: pas,
                acc: acc,
                date: date,
                val: val
            }
        }).done(function (msg) {

            $("#question").html("");
            $("#automationImages").html("");
            $("#ol").html("").empty();

            if (msg !== undefined && msg !== null &&
                    msg.gs !== undefined && msg.gs !== null &&
                    msg.result !== undefined) {
                console.log("gs : " + msg.gs);
                console.log("Result : " + msg.result);
                var pathImages = msg.gs.replace("[", "").replace("]", "").split(",");
                for (var i = 0; i < pathImages.length; i++) {
                    var emidaImage = pathImages[i].trim().replace("\\", "/");
                    var npath = (pathImages.length);
                    var name = emidaImage.split("/");
                    var carouselClass = (i == 0) ? "carousel-item active" : "carousel-item";
//                            var olClass = (i == 0) ? "carousel-item active" : "carousel-item active";
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
            $('.ajax-loading').hide(1);
        }).error(function (error) {
            console.log("ERROR: " + error);
        });

    });
});
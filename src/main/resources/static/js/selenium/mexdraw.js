$(document).ready(function () {
    $("#radios").on("submit", function (event) {
        event.preventDefault();
        var user = $('#user').val();
        var pass = $('#pass').val();
        var log = $('#log').is(':checked');
        var top = $('#top').is(':checked');
        var pin = $('#pin').is(':checked');
        var log2 = $('input:checked').val();
        console.log(user + pass + log + pin + top);
        if (user !== undefined && user !== null &&
                pass !== undefined && pass !== null &&
                log !== undefined && log !== null &&
                top !== undefined && top !== null &&
                pin !== undefined && pin !== null) {
            if (log == false) {
                log = "off";
            } else {
                log = "on";
            }
            if (top == false) {
                top = "off";
            } else {
                top = "on";
            }
            if (pin == false) {
                pin = "off";
            } else {
                pin = "on";
            }
            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/selenium",
                data: {
                    user: user,
                    pass: pass,
                    log: log,
                    top: top,
                    pin: pin
                }
            }).done(function (msg) {

                var enableDraw = false;
                $("#question").html("");
                $("#automationImages").html("");
                $("#ol").html("").empty();

                if (msg !== undefined && msg !== null &&
                        msg.gs !== undefined && msg.gs !== null &&
                        msg.result !== undefined) {

                    if (log == "on" && top == "off" && pin == "off") {
                        graphics.test4(1, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && top == "on" && pin == "off") {
                        graphics.test4(2, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && top == "off" && pin == "on") {
                        graphics.test4(3, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && top == "on" && pin == "on") {
                        graphics.test4(4, msg.result);
                        enableDraw = true;
                    }
                    if (enableDraw) {
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
                }
                $('#modal-2').modal('show');
                msg.gs = null;
                $('.ajax-loading').hide(1);
            }).error(function (error) {
                console.log("ERROR: " + error);
            });
        }
    });
});
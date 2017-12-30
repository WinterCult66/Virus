$(document).ready(function () {

    formRandom();

    $('#modal-1').on('shown.bs.modal', function () {
        formRandom();
    });

    function formRandom() {

        var sim = Math.round(Math.random() * 99999999999999999999);
        $("#el").html("");
        var simField = "<label for='exampleInputEmail1'>Sim Lyca/Locus</label><input type='text' class='form-control border-green' id='sim' name ='sim' placeholder='Enter Sim' value ='" + sim + "'/>";
        $("#el").append(simField);
    }
    
    $("#dom").on("submit", function (event) {
        event.preventDefault();
        var user = $('#user').val();
        var pass = $('#pass').val();
        var clerk = $('#clerk').val();
        var sim = $("#sim").val();
        var log = $('#log').is(':checked');
        var email = $("#email").val();
        var zip = $("#zip").val();
        var esn = $("#esn").val();
        var npa = $("#npa").val();
        var act = $('#act').is(':checked');
        var actlo = $('#actlo').is(':checked');
        var log2 = $('input:checked').val();
        console.log(user + pass + log + act);
        if (user !== undefined && user !== null &&
                pass !== undefined && pass !== null &&
                log !== undefined && log !== null &&
                act !== undefined && act !== null) {
            if (log === false) {
                log = "off";
            } else {
                log = "on";
            }
            if (act === false) {
                act = "off";
            } else {
                act = "on";
            }
            if (actlo === false) {
                actlo = "off";
            } else {
                actlo = "on";
            }

            $('.ajax-loading').show(10);
            $.ajax({
                method: "POST",
                url: "/methods/api/selenium/dom",
                data: {
                    user: user,
                    pass: pass,
                    clerk: clerk,
                    sim: sim,
                    zip: zip,
                    email: email,
                    esn: esn,
                    npa: npa,
                    log: log,
                    act: act,
                    actlo: actlo
                }
            }).done(function (msg) {

                var enableDraw = false;
                $("#question").html("");
                $("#automationImages").html("");
                $("#ol").html("").empty();
                if (msg !== undefined && msg !== null &&
                        msg.gs !== undefined && msg.gs !== null) {

                    if (log == "on" && act == "off" && actlo == "off") {
                        graphics.test4(1, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && act == "on" && actlo == "off") {
                        graphics.test4(2, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && act == "off" && actlo == "on") {
                        graphics.test4(3, msg.result);
                        enableDraw = true;
                    } else if (log == "on" && act == "on" && actlo == "on") {
                        graphics.test4(4, msg.result);
                        enableDraw = true;
                    }
                    if (enableDraw) {
                        console.log("gs : " + msg.gs);
                        // console.log("Result : " + msg.result);
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
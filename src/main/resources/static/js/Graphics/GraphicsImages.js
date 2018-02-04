/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function GraphicsImages() {

    var instance = this;
    instance.init = function () {
        $(document).ready(function () {
        });
    };


    instance.DrawGraphic = function (msg) {


        if (msg !== undefined && msg !== null) {
            var pathImages = msg.gs.replace("[", "").replace("]", "").split(",");
            var pathEdges = msg.es.replace("[", "").replace("]", "").split(",");

            try {
                for (var i = 0; i < pathImages.length; i++) {
                    var emidaImage = pathImages[i].trim().replace("\\", "/");
                    var npath = (pathImages.length);
                    var name = emidaImage.split("/");
                    var carouselClass = (i == 0) ? "carousel-item active" : "carousel-item";
//                            var olClass = (i == 0) ? "carousel-item active" : "carousel-item active";
                    //console.log("emidaImage " + emidaImage);
                    //console.log("carouselClass " + carouselClass);
                    $("#ol").append("<li data-target='#carousel-keyboard' data-slide-to='" + npath + "' class='active'></li>");
                    $("#automationImages").append("<div class='" + carouselClass + "'><center><img src='" + emidaImage + "' style='width:100%'  /></center> <div class='carousel-caption'><h3>" + name[name.length - 1] + "</h3></div></div>");
                }
            } catch (ex) {
                toastr.error("Error Draw " + ex);
                toastr.options = {
                    "closeButton": true
                };
            }


            try {
                for (var i = 0; i < pathEdges.length; i++) {
                    var emidaImage = pathEdges[i].trim().replace("\\", "/");
                    var npath = (pathEdges.length);
                    var name = emidaImage.split("/");
                    var carouselClass = (i == 0) ? "carousel-item active" : "carousel-item";
//                            var olClass = (i == 0) ? "carousel-item active" : "carousel-item active";
                    //console.log("emidaImage " + emidaImage);
                    //console.log("carouselClass " + carouselClass);
                    $("#ol2").append("<li data-target='#carousel-keyboard' data-slide-to='" + npath + "' class='active'></li>");
                    $("#edgeImages").append("<div class='" + carouselClass + "'><center><img src='" + emidaImage + "' style='width:100%'  /></center> <div class='carousel-caption'><h3>" + name[name.length - 1] + "</h3></div></div>");
                }
            } catch (ex) {
                toastr.error("Error Draw " + ex);
                toastr.options = {
                    "closeButton": true
                };
            }
        }
    }
}

var graphicsImages = new GraphicsImages();

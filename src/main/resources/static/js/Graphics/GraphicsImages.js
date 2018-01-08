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

var graphicsImages = new GraphicsImages();
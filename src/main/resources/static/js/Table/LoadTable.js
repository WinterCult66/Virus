/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var list = [];
    list = $("#list").val();
    var json = JSON.parse(list);
    $.each(json, function (i) {
        var getColor = arrayColors(numberRandom());
        var getIcon = arrayIcon(numberRandom());
        var dataCard = "<div  class='col-xl-3 col-lg-6 col-xs-12'><a><div class='card " + getColor + "'><div id =" + json[i].a[2] + " class='card-body' ><div class='card-block'><div class='media'><div class='media-left media-middle'><i class='" + getIcon + " white font-large-2 float-xs-left'></i></div><div class='media-body white text-xs-right'><h3>" + json[i].a[1] + "</h3><span>" + json[i].a[0] + "</span></div></div></div></div></div></a></div> ";
        $("#cardDynamic").append(dataCard);
    });

    function arrayColors(position) {
        var colors = ["bg-success",
            "bg-black",
            "bg-danger",
            "bg-info"];
        return colors[position];
    }

    function arrayIcon(position) {
        var icon = ["icon-clubs",
            "icon-diamonds",
            "icon-spades",
            "icon-heart3"
        ];
        return icon[position];
    }

    function numberRandom() {
        var colorPosition;
        colorPosition = Math.round((Math.random() * 3));
        return colorPosition;
    }


    $(".card-body").on("click", function () {
        var id = ($(this).prop("id"));
        if (id !== undefined && id !== null) {
            $('.ajax-loading').show(10);            
            $.ajax({
                type: "POST",
                url: "/records/processrecords/" + id,
                timeout: 100000
            }).success(function () {
                console.log("SUCCESS");
            }).done(function (msg) {
                $("#question").html("");
                $("#automationImages").html("");
                $("#ol").html("").empty();
                graphicsImages.DrawGraphic(msg);
                $('#modal-2').modal('show');
                msg.gs = null;
                $('.ajax-loading').hide(1);
            }).error(function (error) {
                console.log(error);
            });
        }

    });



});


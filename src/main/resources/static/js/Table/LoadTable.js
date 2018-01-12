/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var list = [];
    list = $("#list").val();
    //console.log(list);
    var json = JSON.parse(list);
    console.log(json);
    $.each(json, function (i) {
        var getColor = arrayColors(numberRandom());
        var dataCard = "<div  class='col-xl-3 col-lg-6 col-xs-12'><div class='card " + getColor + "'><div class='card-body' ><div class='card-block'><div class='media'><div class='media-left media-middle'><div clas= 'col-md-2 clickPlay'><a><i id =" + json[i].a[2] + " class='icon-play3 white font-large-1 float-xs-left'></i></a></div><div class ='col-md-2'><a><i id =d" + json[i].a[2] + " class=' icon-bin white font-large-1 float-xs-left'></i></a></div></div><div class='media-body white text-xs-right'><h3>" + json[i].a[1] + "</h3><span>" + json[i].a[0] + "</span></div></div></div></div></div></div> ";
        $("#cardDynamic").append(dataCard);
    });
    
    

    function arrayColors(position) {
        var colors = ["bg-success",
            "bg-black",
            "bg-danger",
            "bg-info",
            "bg-purple"
        ];
        return colors[position];
    }
    function numberRandom() {
        var colorPosition;
        colorPosition = Math.round((Math.random() * 4));
        return colorPosition;
    }

    $(".icon-play3").on("click", function () {
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


    $(".icon-bin").on("click", function () {
        var id = ($(this).prop("id"));
        var idSub = id.substring(1);

        if (idSub !== undefined && id !== null) {
            $('.ajax-loading').show(10);
            $.ajax({
                type: "POST",
                url: "/records/deleterecords/" + idSub,
                timeout: 10000
            }).success(function () {
                console.log("DELTE SUCCES");
            }).done(function (msg) {
                $('.ajax-loading').hide(10);
                if (msg === true) {
                    toastr.success("Delete Success");
                    toastr.options = {
                        "closeButton": true
                    };
                    var result = json.push(0,0);
                    console.log(json);
                    console.log(result);
                }

            }).error(function (error) {
                console.log("ERROR : " + error);
            });

        }
    });

    function removeItem(obj, prop, val) {
        var c, found = false;
        for (c in obj) {
            if (obj[c][prop] == val) {
                found = true;
                break;
            }
        }
        if (found) {
            delete obj[c];
        }
    }





});



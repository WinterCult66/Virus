/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    var list = [];
    list = $("#list").val();
    console.log("List" + list);
    var json = JSON.parse(list);
    
    //console.log(json[0].a[1]);
    //var json = JSON.stringify(list);
    //console.log(json);
    //
//    console.log(JSON.parse(a));
    //var jsonData = JSON.parse(list);
    //console.log(jsonData);
   $.each(json, function (i) {
       var getColor = arrayColors(numberRandom());
        var dataCard = "<div  class='col-xl-3 col-lg-6 col-xs-12'><div class='card "+getColor+"'><div class='card-body'><div id ="+json[i].a[2]+" class='card-block'><div class='media'><div class='media-left media-middle'><i class='icon-trending_up white font-large-2 float-xs-left'></i></div><div class='media-body white text-xs-right'><h3>"+json[i].a[1]+"</h3><span>"+json[i].a[0]+"</span></div></div></div></div></div></div> ";
        $("#cardDynamic").append(dataCard);
//        console.log();
    });

    function arrayColors(position) {
        var colors = ["bg-success",
            "bg-black",
            "bg-danger",
            "bg-info"];
        return colors[position];
    }
        function numberRandom() {
        var colorPosition;
        colorPosition = Math.round((Math.random() * 3));
        return colorPosition;
    }

});


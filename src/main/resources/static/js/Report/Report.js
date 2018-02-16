/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    list = $("#list").val();
    var json = JSON.parse(list);
    //console.log(json);
    $.each(json, function (i) {
        var dataCard = "<tr><th>" + i + "</th><th>" + json[i].a[0] + "</th><th>" + json[i].a[1] + "</th><th>" + json[i].a[2] + "</th><th><a><i id = " + json[i].a[4] + " class='icon-search1 red font-large-2 float-xs-right'></i></th></a></tr>";
        $("#tableReport").append(dataCard);
    });
    $("body").on("click", ".icon-search1", function () {
        var id = ($(this).prop("id"));
        $('.ajax-loading').show(10);
        $.ajax({
            type: "POST",
            url: "/records/showreportss/" + id,
            timeout: 100000
        }).success(function (msg) {
            $('.ajax-loading').hide(1);
            console.log(msg);
        });

    });
})
        ;


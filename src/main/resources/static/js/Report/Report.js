/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    list = $("#list").val();
    var json = JSON.parse(list);
    $.each(json, function (i) {
        var item = i+1;
        var dataCard = "<tr><th>" + item + "</th><th>" + json[i].a[0] + "</th><th>" + json[i].a[5] + "</th><th>" + json[i].a[1] + "</th><th>" + json[i].a[2] + "</th><th><a><i id = " + json[i].a[4] + " class='icon-search1 green font-large-1 '></i></th></a></tr>";
        $("#tableReport").append(dataCard);
    });
    $("body").on("click", ".icon-search1", function () {
        $("#detailEvent").empty();
$("#detailEvent").append("<div class='col-md-11'><span><strong>Event</strong></span> </div><div class='col-md-1'><span><strong>Status</strong></span> </div>");
        var id = ($(this).prop("id"));
        $('.ajax-loading').show(10);
        $.ajax({
            type: "POST",
            url: "/records/showreportss/" + id,
            timeout: 100000
        }).success(function (msg) {
            var jsonDetail = JSON.parse(msg.history);
            $.each(jsonDetail, function (i) {
                var status = jsonDetail[i].a[1];
                if (status.toString() == "1") {
                    classs = "icon-checkmark2 red font-large-2 float-xs-right";
                } else {
                    classs = "icon-cross  font-large-2 float-xs-right";
                }
                var dataCard = "<div class='col-md-11'><span>" + jsonDetail[i].a[0] + "</span> </div><div class='col-md-1'><span><i class=" + classs + "></i></span> </div>";
                $("#detailEvent").append(dataCard);
            });
            $('.ajax-loading').hide(1);
            $("#reportDetail").modal('show');
        });

    });
});


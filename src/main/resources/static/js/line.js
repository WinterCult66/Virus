///*=========================================================================================
// File Name: line.js
// Description: Chartjs simple line chart
// ----------------------------------------------------------------------------------------
// Item Name: Robust - Responsive Admin Theme
// Version: 1.2
// Author: PIXINVENT
// Author URL: http://www.themeforest.net/user/pixinvent
// ==========================================================================================*/
//
//// Line chart
//// ------------------------------
//
//$(window).on("load", function () {
//
////Get the context of the Chart canvas element we want to select
//    var ctx = $("#line-chart");
//
//    // Chart Options
//    var chartOptions = {
//        responsive: true,
//        maintainAspectRatio: false,
//        legend: {
//            position: 'bottom',
//        },
//        hover: {
//            mode: 'label'
//        },
//        scales: {
//            xAxes: [{
//                    display: true,
//                    gridLines: {
//                        color: "#f3f3f3",
//                        drawTicks: false,
//                    },
//                    scaleLabel: {
//                        display: true,
//                        labelString: ''
//                    }
//                }],
//            yAxes: [{
//                    display: true,
//                    gridLines: {
//                        color: "#f3f3f3",
//                        drawTicks: false,
//                    },
//                    scaleLabel: {
//                        display: true,
//                        labelString: 'Value'
//                    }
//                }]
//        },
//        title: {
//            display: true,
//            text: 'Result Automation'
//        }
//    };
//
//    var a=3;
//    if (a === 1) {
//        var datos = [0, 30];
//        // Chart Data
//        var chartData = {
//            labels: ["start", "Login"],
//            datasets: [{
//                    label: "Press",
//                    data: datos,
//                    lineTension: 0,
//                    fill: false,
//                    borderColor: "#FF5722",
//                    pointBorderColor: "#FF5722",
//                    pointBackgroundColor: "#FFF",
//                    pointBorderWidth: 2,
//                    pointHoverBorderWidth: 2,
//                    pointRadius: 4,
//                }]
//        };
//    } else if (a === 2) {
//        var datos = [0, 30, 60];
//        // Chart Data
//        var chartData = {
//            labels: ["start", "Login", "Pin"],
//            datasets: [{
//                    label: "Press",
//                    data: datos,
//                    lineTension: 0,
//                    fill: false,
//                    borderColor: "#FF5722",
//                    pointBorderColor: "#FF5722",
//                    pointBackgroundColor: "#FFF",
//                    pointBorderWidth: 2,
//                    pointHoverBorderWidth: 2,
//                    pointRadius: 4,
//                }]
//        };
//    } else if (a === 3) {
//        var datos = [0, 30, 0, 0];
//        // Chart Data
//        var chartData = {
//            labels: ["start", "Login", "Pin", "TopUp"],
//            datasets: [{
//                    label: "Press",
//                    data: datos,
//                    lineTension: 0,
//                    fill: false,
//                    borderColor: "#FF5722",
//                    pointBorderColor: "#FF5722",
//                    pointBackgroundColor: "#FFF",
//                    pointBorderWidth: 2,
//                    pointHoverBorderWidth: 2,
//                    pointRadius: 4,
//                }]
//        };
//
//    }
//
//    var config = {
//        type: 'line',
//
//        // Chart Options
//        options: chartOptions,
//
//        data: chartData
//    };
// 
//
//    // Create the chart
//    var lineChart = new Chart(ctx, config);
//});

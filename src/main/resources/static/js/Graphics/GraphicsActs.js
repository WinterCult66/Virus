function Graphics() {

    var Instance = this;
    Instance.init = function () {
        $(document).ready(function () {
        });
    };

    function dibujar() {
        console.log("Dibujop");
    }

    Instance.test4 = function (option, result) {

        var dataSuccesLabel;
        var dataFailLabel;
        var dataSucces;
        var dataFail;
        window.onload = dibujar;
        var ctx = $("#column-chart").empty();
        var chartOptions = {};
        chartOptions = {
            // Elements options apply to all of the options unless overridden in a dataset
            // In this case, we are setting the border of each bar to be 2px wide and green
            elements: {
                rectangle: {
                    borderWidth: 2,
                    borderColor: 'rgb(0, 255, 0)',
                    borderSkipped: 'bottom'
                }
            },
            responsive: true,
            maintainAspectRatio: false,
            responsiveAnimationDuration: 500,
            legend: {
                position: 'top',
            },
            scales: {
                xAxes: [{
                        display: true,
                        gridLines: {
                            color: "#f3f3f3",
                            drawTicks: false,
                        },
                        scaleLabel: {
                            display: true,
                        }
                    }],
                yAxes: [{
                        display: true,
                        gridLines: {
                            color: "#f3f3f3",
                            drawTicks: false,
                        },
                        scaleLabel: {
                            display: true,
                        }
                    }]
            },
            title: {
                display: true,
                text: 'Chart Success vs Fails'
            }
        };
        switch (option) {

            case 1:
                if (result == 1) {
                    dataSuccesLabel = [": Login"];
                    dataFailLabel = [""];
                    dataSucces = 1;
                    dataFail = 0;
                } else if (result !== 1) {
                    dataSuccesLabel = [""];
                    dataFailLabel = [": Login"];
                    dataSucces = 0;
                    dataFail = 1;
                }
                drawGraph(dataSuccesLabel, dataFailLabel, dataSucces, dataFail, chartOptions, ctx);
                break;

            case 2:

                if (result == 2) {
                    dataSuccesLabel = [": Login, ACTLyca"];
                    dataFailLabel = [""];
                    dataSucces = 2;
                    dataFail = 0;
                } else if (result == 1) {
                    dataSuccesLabel = [": Login"];
                    dataFailLabel = [": ACTLyca"];
                    dataSucces = 1;
                    dataFail = 1;
                } else if (result !== 1 && result !== 2) {
                    dataSuccesLabel = [""];
                    dataFailLabel = [": Login, ACTLyca"];
                    dataSucces = 0;
                    dataFail = 2;

                }
                drawGraph(dataSuccesLabel, dataFailLabel, dataSucces, dataFail, chartOptions, ctx);
                break;

            case 3:

                if (result == 2) {
                    dataSuccesLabel = [": Login, ACTLocus"];
                    dataFailLabel = [""];
                    dataSucces = 2;
                    dataFail = 0;
                } else if (result == 1) {
                    dataSuccesLabel = [": Login"];
                    dataFailLabel = [": ACTLocus"];
                    dataSucces = 1;
                    dataFail = 1;
                } else if (result !== 1 && result !== 2) {
                    dataSuccesLabel = [""];
                    dataFailLabel = [": Login, ACTLocus"];
                    dataSucces = 0;
                    dataFail = 2;

                }
                drawGraph(dataSuccesLabel, dataFailLabel, dataSucces, dataFail, chartOptions, ctx);
                break;

            case 4:

                if (result == 3) {
                    dataSuccesLabel = [": Login, ACTLyca, ACTLocus"];
                    dataFailLabel = [""];
                    dataSucces = 3;
                    dataFail = 0;
                } else if (result == 2) {
                    dataSuccesLabel = [": Login, ACTLyca"];
                    dataFailLabel = [": Pin"];
                    dataSucces = 2;
                    dataFail = 1;
                } else if (result == 1) {
                    dataSuccesLabel = [": Login"];
                    dataFailLabel = [": ACTLyca,ACTLocus"];
                    dataSucces = 1;
                    dataFail = 2;
                } else if (result !== 3 && result !== 2 && result !== 1) {
                    dataSuccesLabel = [""];
                    dataFailLabel = [": Login, ACTLyca, ACTLocus"];
                    dataSucces = 0;
                    dataFail = 3;
                }
                drawGraph(dataSuccesLabel, dataFailLabel, dataSucces, dataFail, chartOptions, ctx);
                break;
        }
    };
    function  drawGraph(dataSuccesLabel, dataFailLabel, dataSucces, dataFail, optionschar, ctx) {
        var chartData = {};
        // Chart Data
        chartData = {

            labels: ["Test Result"],
            datasets: [{
                    label: "Succes " + dataSuccesLabel,
                    data: [dataSucces, 1, 0, 10],

                    backgroundColor: [
                        'rgba(70, 235, 55, 0.2)'
                    ],
                    borderColor: [
                        'rgba(103, 235, 55, 1)'
                    ]
                }, {
                    label: "Fail " + dataFailLabel,
                    data: [dataFail, 1, 0, 10],

                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgba(111, 11, 28, 1)'
                    ]

                }]
        };
        var config = {};
        config = {
            type: 'bar',

            // Chart Options
            options: optionschar,

            data: chartData
        };

        // Create the chart

        var lineChart = new Chart(ctx, config);
    }

    Instance.init();
}
var graphics = new Graphics();
var value;
var arraySelect = [];
var count = 0;
var jsonObj = [];
var dataJson;
var buttonCount = 0;
$(document).ready(function () {
    //Start Variables Hide
    $("#1").hide();
    $("#2").hide();
    $("#3").hide();
    $("#4").hide();
    $("#5").hide();
    //End Variables Hide
    selectChange();
    //START OPEN MODAL
    $('#modal-1').on('shown.bs.modal', function () {
    });
    //END OPEN MODAL
    //SAVE TABLE START
    $("#saveTable").click(function () {

        var valueExit = selectChange();
        var valueExitSelectVal = valueExit[0];
        var valueExitSelectText = valueExit[1];

        if (valueExitSelectVal == undefined) {
            toastr.error('Select A Option');
            toastr.options = {
                "closeButton": true
            }
        } else {
            $('#modal-1').modal('hide');
            var positionx = 0;
            var positiony = 0;
            switch (valueExitSelectVal) {
                case "1":
                    positionx = 1;
                    break;
                case "2":
                    positionx = 2;
                    positiony = 3;
                    break;
                case "3":
                    positionx = 4;
                    break;
                case "4":
                    positionx = 5;
                    break;
                case "5":
                    positionx = 6;
                    break;
            }
            var getValue = getData(valueExitSelectVal, positionx, positiony);
            var a = "";
            if (typeof getValue == 'string') {
                if (getValue.length <= 0) {
                    toastr.error('Field Empty');
                    toastr.options = {
                        "closeButton": true
                    };
                    return false;
                } else if (getValue.length >= 1) {
                    toastr.success('Add Success ' + valueExitSelectText);
                    toastr.options = {
                        "closeButton": true
                    };
                }
            } else if (typeof getValue == 'object') {
                var a = getValue[0];
                getValue = getValue[1];
                if (a.length <= 0 || getValue <= 0) {
                    toastr.error('Field Empty');
                    toastr.options = {
                        "closeButton": true
                    };
                    return false;

                } else {
                    toastr.success('Add Success ' + getValue);
                    toastr.options = {
                        "closeButton": true
                    };
                }
            }
            count++;
            buttonCount++;
            var getColor = arrayColors(numberRandom());
            var tr = "<tr class=" + getColor + "><td scope='row'>" + count + "</td><td>" + valueExitSelectText + "</td>a<td>" + a + "</td><td>" + getValue + "</td><td><button value = " + count + " type='button' class='btn btn-black remove'><i class='icon-emoticon25'></i> </button></td></tr>";
            $("#tableDynamic").append(tr);
            dataJson = getValueFromForm(valueExitSelectVal, a, getValue);            
        }
    });

    $("body").on("click", ".remove", function () {
        var valueToDelete = ($(this).prop("value"));
        $(this).closest("tr").remove();
        valueToDelete = valueToDelete - 1;
        getPositionArray(valueToDelete);
        toastr.success('Delete Success');
        toastr.options = {
            "closeButton": true
        };
        console.log(JSON.stringify(dataJson));
    });
    $("#upload").on("click", function () {
        console.log(dataJson);
        var JSONKey = JSON.stringify(dataJson);        
        if (dataJson !== undefined) {
            $('.ajax-loading').show(10);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/methods/api/selenium/dynamicform",
                data: JSONKey,
                dataType: 'json',
                timeout: 100000
            }).success(function () {
                $('.ajax-loading').hide(1);
                toastr.success('Success Send Data');
                toastr.options = {
                    "closeButton": true,
                    "hideAfter": 10000
                };
            }).done(function () {
                //window.location = '/';
            }).error(function (thrownError) {
                console.log("ERROR From Dynamic Form: " + thrownError);
                toastr.error('Error Send Data');
                toastr.options = {
                    "closeButton": true
                };
                $('.ajax-loading').hide(1);
            });
        }
    });
    //SAVE TABLE END
    //START FUNCTIONS
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
    function selectChange() {

        $("select").on("change", function () {
            value = ($(this).val());
            var selectOption;
            selectOption = $("select option:selected").text();
            hideModals();
            $("#" + value).show();
            arraySelect = [value, selectOption];
        });
        return arraySelect;
    }

    function hideModals() {
        $("#1").hide();
        $("#2").hide();
        $("#3").hide();
        $("#4").hide();
        $("#5").hide();
    }

    function getData(option, positionX, positionY) {
        var formSerialize = $("#dom").serializeArray();
        var valor = [];
        switch (option) {
            case "1":
                valor = formSerialize[positionX].value;
                break;
            case "2":
                valor = [formSerialize[positionX].value, formSerialize[positionY].value];
                break;
            case "3":
                valor = formSerialize[positionX].value;
                break;
            case "4":
                valor = formSerialize[positionX].value;
                break;
            case "5":
                valor = formSerialize[positionX].value;
                break;
        }
        return valor;
    }

    function getValueFromForm(select, field1, field2) {
        var productList = {};
        productList ["optionselect"] = select;
        productList["divxpath"] = field1;
        productList ["valuetosend"] = field2;
//        productList ["option"] = select;
//        productList ["div"] = field1;
//        productList ["value"] = field2;
        //console.log(productList);
        jsonObj.push(productList);
        //jsonString = JSON.stringify(jsonObj); 
        return jsonObj;
    }

    function getPositionArray(position) {
        delete(jsonObj[position]);
    }


    //END FUNCTIONS
});


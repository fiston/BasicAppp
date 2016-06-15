// <![CDATA[

$(document).ready(function () {
    $('.adh').change(function () {
        var level = $(this).attr('id');
        //alert("Level : "+level);
        var selectedValue = $(this).val();
        //alert("Level : "+level+" Value : "+selectedValue);
        if (selectedValue != "") {
            loadFile(selectedValue, level);
        }

    });
    $('.filter').change(function () {
        var val = "";
        var voidValues = '<option value=""> --Select-- </option>';
        var elmId = $(this).attr('id');
        if (elmId == "ad_province") {
            val = $("#ad_province :selected").text();
            if($(this).val()== ""){
                $("#province").val("");
                document.getElementById("ad_district").innerHTML = voidValues;
                document.getElementById("ad_sector").innerHTML = voidValues;
            }
            else{
                $("#province").val(val);
            }
            $("#district").val("");
            $("#sector").val("");
        }
        else if (elmId == "ad_district") {
            val = $("#ad_district :selected").text();
            if($(this).val()== ""){
                document.getElementById("ad_sector").innerHTML = voidValues;
                $("#district").val("");
            }
            else{
                $("#district").val(val);
            }

            $("#sector").val("");
        }
        else if (elmId == "ad_sector") {
            val = $("#ad_sector :selected").text();
            if($(this).val()== ""){
                $("#sector").val("");
            }
            else{
                $("#sector").val(val);}
        }
        /*else if (elmId == "ad_cell") {
         val = $("#ad_cell :selected").text();
         //alert(val);
         $("#cellbox").val(val);
         }
         else if (elmId == "ad_village") {
         val = $("#ad_village :selected").text();
         //alert(val);
         $("#villagebox").val(val);
         }*/

    });


});

function loadFile(addressValue, level) {
    var path = "locations/";
    //var path = ctx+"moduleResources/hiepatientsearch/";

    //var path = "";
    if (level == "ad_province") {

        path += "District.txt";
    }
    if (level == "ad_district") {
        path += "Sector.txt";
    }
    /*if (level == "ad_sector") {
     path += "Cell.txt";
     }
     if (level == "ad_cell") {
     path += "Village.txt";
     }*/
    //alert(addressValue+" "+level+" "+path);
    $.ajax({
        type: "GET",
        url: path,
        dataType: "text",
        success: function (data) {
            processData(data, addressValue, level);
        }
    });

}
function processData(allText, id, level) {
    //alert("test");
    var allTextLines = allText.split(/\r\n|\n/);
    var hea = allTextLines[0].split(',');
    //alert(hea);
    //var lines = [];
    var optionsValues = '<option value=""> --Select-- </option>';
    var voidValues = '<option value=""> --Select-- </option>';


    for (var i = 0; i < allTextLines.length; i++) {
        var data = allTextLines[i].split(',');
        var selectToChange = "";
        if (level == "ad_province") {
            if (data[1] == id) {
                //lines.push(data[2]);
                optionsValues += '<option value="' + data[0] + '">'
                    + data[2] + '</option>';
            }


            selectToChange = "ad_district";
            /*document.getElementById("ad_sector").innerHTML = voidValues;
            document.getElementById("ad_cell").innerHTML = voidValues;
             document.getElementById("ad_village").innerHTML = voidValues;*/

        }
        if (level == "ad_district") {
            if (data[1] == id) {
                //lines.push(data[2]);
                optionsValues += '<option value="' + data[0] + '">'
                    + data[2] + '</option>';
            }


            selectToChange = "ad_sector";
            /*document.getElementById("ad_cell").innerHTML = voidValues;
             document.getElementById("ad_village").innerHTML = voidValues;*/
        }



    }



    // alert(optionsValues);
    // alert(selectToChange);
    document.getElementById(selectToChange).innerHTML = optionsValues;


}
// ]]>


const host = "http://localhost:8080/city/"

function callFindAll(success) {
    $.ajax({
        method: "GET",
        dataType: "json",
        url: host,
        success: success
    })
}

function callSave(city, success) {
    $.ajax({
        method: "POST",
        dataType: "json",
        contentType: 'application/json',
        url: host,
        data: JSON.stringify(city),
        success: success
    })
}

function callDelete(city, success) {
    $.ajax({
        method: "DELETE",
        url: host + city.id,
        success: success
    })
}
//----------------------------------------

function fillTable() {
    $("#tableBody").empty();
    callFindAll(function(response) {
        response.forEach((item, index) => {
            addTableLine(item);
        })
    })
}

function addTableLine(city) {
        var line = $('<tr>').append(
                $('<td>').text(city.id),
                $('<td id="name">').text(city.name),
                $('<button>')
                .text('Modifier')
                .click(function() {
                   showEditForm(city);
                }),
                $('<button>')
                .text('Supprimer')
                .click(function() {
                    if (window.confirm("Etes vous sur de vouloir supprimer cette ville ?")) {
                        callDelete(city, function() {
                            fillTable();
                        })
                    }
                })
            );
            $("#tableBody").append(line);
}

function showEditForm(city) {
    $("#edit_id").val(city.id);
    $("#edit_name").val(city.name);
    $("#form").show();
}

//--------------------------------------
    $(document).ready(function() {
    $("#form").hide();
    fillTable();

    $("#buttonDisplayCreateCity").click(function() {
            var newCity = {
                "id": -1,
                "name": "",
            }
            showEditForm(newCity);
     });

     $("#buttonSubmitEditCity").click(function() {
             var city
             var success

             if($("#edit_id").val() > 0) {
                 city = {
                     "id": $("#edit_id").val(),
                     "name": $("#edit_name").val()
                 }
                 success = function(response) {
                    fillTable();
                    $("#form").hide();
                 }
             } else {
                 city = {
                     "name": $("#edit_name").val()
                 }
                 success = function(response) {
                    addTableLine(response);
                    $("#form").hide();
                 }
             }
             callSave(city, success);
        });
    });
//--------------------------------------


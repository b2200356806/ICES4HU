$(document).ready(function () {
    $('#courseTable').dataTable({
        pageLength: 5,
        lengthChange: false
    });
    $('#myTable').dataTable({
        pageLength: 5,
        lengthChange: false,
        info: false
    });
});



const dbParam = JSON.stringify();
const xmlhttp = new XMLHttpRequest();
xmlhttp.onload = function() {
    myObj = JSON.parse(this.responseText);
    let text = "<table border='2'>"
    for (let x in myObj) {
        text += "<tr><td>" + myObj[x].name + "</td>";
        text += "<td>" + myObj[x].department + "</td>";
        text += "<td>" + myObj[x].courseCode + "</td>";
        text += "<td>" + "<input type='checkbox'>" + "</td></tr>";
    }
    text += "</table>"
    document.getElementById("available").innerHTML = text;
}
xmlhttp.open("GET", "http://localhost:8080/api/courses/all");
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(dbParam);


function GetSelected() {
    //Reference the Table.
    var grid = document.getElementById("courseTable");

    //Reference the CheckBoxes in Table.
    var checkBoxes = grid.getElementsByTagName("input");
    var message = "";

    //Loop through the CheckBoxes.
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            var row = checkBoxes[i].parentNode.parentNode;
            message += row.cells[2].innerHTML;
            message += "\n";
        }
    }

    var xhr = new XMLHttpRequest();
    const codes = message.split("\n");
    codes.pop();
    for (let i in codes) {
        xhr.open("POST", "http://localhost:8080/api/students/1/enroll/"+codes[i]);
        xhr.send();
    }
    location.reload();
}



const mydbParam = JSON.stringify();
const myxmlhttp = new XMLHttpRequest();
myxmlhttp.onload = function() {
    myObj2 = JSON.parse(this.responseText);
    let mytext = "<table border='2'>"
    for (let x in myObj2) {
        mytext += "<tr><td>" + myObj2[x].name + "</td>";
        mytext += "<td>" + myObj2[x].department + "</td>";
        mytext += "<td>" + myObj2[x].courseCode + "</td>";
        mytext += "<td>" + "<input type='checkbox'</input>" + "</td></tr>";
    }
    mytext += "</table>"
    document.getElementById("enrolled").innerHTML = mytext;
}
myxmlhttp.open("GET", "http://localhost:8080/api/students/1/courses");
myxmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
myxmlhttp.send(mydbParam);


function DropSelected() {
    //Reference the Table.
    var grid = document.getElementById("myTable");

    //Reference the CheckBoxes in Table.
    var checkBoxes = grid.getElementsByTagName("input");
    var message = "";

    //Loop through the CheckBoxes.
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            var row = checkBoxes[i].parentNode.parentNode;
            message += row.cells[2].innerHTML;
            message += "\n";
        }
    }

    var xhr = new XMLHttpRequest();
    const codes = message.split("\n");
    codes.pop();
    for (let i in codes) {
        xhr.open("POST", "http://localhost:8080/api/students/1/drop/"+codes[i]);
        xhr.send();
    }
    location.reload();
}
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


let enrollCourses = [];

// watch for change events on the checkbox
$("#courseTable").on('change', 'input[type="checkbox"]' , function () {
    let value = $(this).closest("tr")[0];
    value = value.cells[2].innerHTML;

    if(this.checked) {
        enrollCourses.push(value);	// record the value of the checkbox to valArray
    } else {
        let index = enrollCourses.indexOf(value);
        if (index !== -1) {
            enrollCourses.splice(index, 1);
        }	// remove the recorded value of the checkbox
    }
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
    const codes = enrollCourses;
    for (let i in codes) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/api/students/1/enroll/"+codes[i], false);
        xhr.send(null);
    }
    location.reload();
}



let dropCourses = [];

// watch for change events on the checkbox
$("#myTable").on('change', 'input[type="checkbox"]' , function () {
    let value = $(this).closest("tr")[0];
    value = value.cells[2].innerHTML;

    if(this.checked) {
        dropCourses.push(value);	// record the value of the checkbox to valArray
    } else {
        let index = dropCourses.indexOf(value);
        if (index !== -1) {
            dropCourses.splice(index, 1);
        }	// remove the recorded value of the checkbox
    }
});

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
    const codes = dropCourses;
    for (let i in codes) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/api/students/1/drop/"+codes[i], false);
        xhr.send(null);
    }
    location.reload();
}

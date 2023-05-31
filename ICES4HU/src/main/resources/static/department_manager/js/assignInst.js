//Get Instructors of same department
const dbParam = JSON.stringify();
const xmlhttp = new XMLHttpRequest();
xmlhttp.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "";
    for (let x in myObj) {
        text += `<option value="${myObj[x].userId}">` + myObj[x].firstName + " " + myObj[x].lastName + "</option>";
    }
    document.getElementById("inst").innerHTML = text;
}
xmlhttp.open("GET", "http://localhost:8080/api/department-managers/500/instructors");
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(dbParam);


//Get Courses of same department
const dbParam2 = JSON.stringify();
const xmlhttp2 = new XMLHttpRequest();
xmlhttp2.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "";
    for (let x in myObj) {
        text += `<option value="${myObj[x].courseCode}">` + myObj[x].courseCode + " - " + myObj[x].name + "</option>";
    }
    document.getElementById("course").innerHTML = text;
}
xmlhttp2.open("GET", "http://localhost:8080/api/department-managers/500/courses");
xmlhttp2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp2.send(dbParam2);


//Assign instructor to course
var form = document.getElementById('assignInstForm');
form.onsubmit = function (event) {
    var xhr = new XMLHttpRequest();
    var formData = new FormData(form);

    //Select course code from dropdown
    var courses = document.getElementById("course").options;
    var selectedCourse = document.getElementById("course").selectedIndex;
    let selectCode = courses[selectedCourse].value

    //Select instructor id from dropdown
    var instructors = document.getElementById("inst").options;
    var selectedInstructor = document.getElementById("inst").selectedIndex;
    let selectId = instructors[selectedInstructor].value

    //open the request
    xhr.open('POST', 'http://localhost:8080/api/department-managers/500/assign/' + selectCode)
    xhr.setRequestHeader("Content-Type", "application/json");

    //send the form data
    xhr.send(JSON.stringify({userId: selectId}));

    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            window.createNotification({
                closeOnClick: true,
                displayCloseButton: false,
                positionClass: 'nfc-top-right',
                onclick: false,
                showDuration: 3500,
                theme: 'success'
            })({
                title: 'Success',
                message: 'Instructor assigned successfully'
            });
            form.reset(); //reset form after AJAX success or do something else
        }
    }
    //Fail the onsubmit to avoid page refresh.
    return false;
}

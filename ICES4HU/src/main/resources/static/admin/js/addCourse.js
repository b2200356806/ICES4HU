$(document).ready(function () {
    $('#courseTable').dataTable({
        pageLength: 5,
        lengthChange: false
    });
});


var form = document.getElementById('addCourseForm');
form.onsubmit = function(event)
{
    var xhr = new XMLHttpRequest();
    var formData = new FormData(form);
    //open the request
    xhr.open('POST','http://localhost:8080/api/admin/1/courses/add')
    xhr.setRequestHeader("Content-Type", "application/json");

    let courseAdded = {
        courseCode: formData.get("courseCode"),
        name: formData.get("name"),
        department: {
            name: formData.get("department")
        }
    };

    //send the form data
    xhr.send(JSON.stringify(courseAdded));

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            window.createNotification({
                closeOnClick: true,
                displayCloseButton: false,
                positionClass: 'nfc-top-right',
                onclick: false,
                showDuration: 3500,
                theme: 'success'
            })({
                title: 'Success',
                message: 'Course added successfully'
            });
            form.reset(); //reset form after AJAX success or do something else
        }
    }
    //Fail the onsubmit to avoid page refresh.
    return false;
}



let dropCourses = [];

// watch for change events on the checkbox
$("#courseTable").on('change', 'input[type="checkbox"]' , function () {
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
    let myObj2 = JSON.parse(this.responseText);
    let mytext = "<table border='2'>"
    console.log(myObj2)
    // for (let x in myObj2) {
    //     mytext += "<tr><td>" + myObj2[x].name + "</td>";
    //     mytext += "<td>" + myObj2[x].department.name + "</td>";
    //     mytext += "<td>" + myObj2[x].courseCode + "</td>";
    //     mytext += "<td>" + "<input type='checkbox'</input>" + "</td></tr>";
    // }
    // mytext += "</table>"
    document.getElementById("available").innerHTML = mytext;
}
myxmlhttp.open("GET", "http://localhost:8080/api/admin/1/courses");
myxmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
myxmlhttp.setRequestHeader("Authorization", "Bearer "+localStorage.getItem("token"))
myxmlhttp.send(mydbParam);


function RemoveSelected() {
    const codes = dropCourses;
    for (let i in codes) {
        console.log(codes[i])
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/api/admin/1/courses/remove", false);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.setRequestHeader("Authorization", "Bearer "+localStorage.getItem("token"))
        xhr.send(JSON.stringify({courseCode: codes[i]}));
    }
    location.reload();
}
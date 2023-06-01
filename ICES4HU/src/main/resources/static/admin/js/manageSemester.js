document.addEventListener("DOMContentLoaded", function (event) {
    var scrollpos = localStorage.getItem('scrollpos');
    if (scrollpos) window.scrollTo(0, scrollpos);
});


window.onbeforeunload = function (e) {
    localStorage.setItem('scrollpos', window.scrollY);
};

const dbParam = JSON.stringify();
const xmlhttp = new XMLHttpRequest();
xmlhttp.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let flagSem = "Not Started";
    let flagAdd = "Not Started";
    let flagEval = "Not Started";
    if (myObj["semesterStarted"])
        flagSem = "Started";
    if (myObj["addOrDropStarted"])
        flagAdd = "Started";
    if (myObj["evaluationStarted"])
        flagEval = "Started";

    document.getElementById("semester-status").value = flagSem;
    document.getElementById("addDrop-status").value = flagAdd;
    document.getElementById("eval-status").value = flagEval;
}
xmlhttp.open("GET", "http://localhost:8080/api/admin/1/semester");
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(dbParam);

//Semester Start Button
document.getElementById("start-btn").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/semester/start");
    xhr.send();
});

//Semester Update Button
document.getElementById("update-btn").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/semester/start");
    xhr.send();
});

//Semester End Button
document.getElementById("end-btn").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/semester/finish");
    xhr.send();
});


//AddDrop Start Button
document.getElementById("ad-start").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/add-or-drop/start");
    xhr.send();
});

//AddDrop Update Button
document.getElementById("ad-update").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/add-or-drop/start");
    xhr.send();
});

//AddDrop End Button
document.getElementById("ad-end").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/add-or-drop/finish");
    xhr.send();
});


//Evaluation Start Button
document.getElementById("eval-start").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/evaluation/start");
    xhr.send();
});

//Evaluation Update Button
document.getElementById("eval-update").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/evaluation/start");
    xhr.send();
});

//Evaluation End Button
document.getElementById("eval-end").addEventListener("click", function () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/1/evaluation/finish");
    xhr.send();
});


//Datepicker
startDate.min = new Date().toISOString().split("T")[0];
addDropStartDate.min = new Date().toISOString().split("T")[0];
evalStartDate.min = new Date().toISOString().split("T")[0];
document.getElementById("startDate").addEventListener("change", function () {
    var input = this.value;
    endDate.min = input
});
document.getElementById("addDropStartDate").addEventListener("change", function () {
    var input = this.value;
    addDropEndDate.min = input
});
document.getElementById("evalStartDate").addEventListener("change", function () {
    var input = this.value;
    evalEndDate.min = input
});


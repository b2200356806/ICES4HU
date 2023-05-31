//On page load
(function () {
    var theme = localStorage.getItem("sem");
    if (theme === "started") {
        document.getElementById("semester-status").value = "Started"
        document.getElementById("end-btn").disabled = false
        document.getElementById("start-btn").disabled = true
    }
    else {
        document.getElementById("semester-status").value = "Not Started"
        document.getElementById("start-btn").disabled = false
        document.getElementById("end-btn").disabled = true;
    }
})();


//Start Button
document.getElementById("start-btn").addEventListener("click", function()
{
    document.getElementById("semester-status").value = "Started"
    document.getElementById("end-btn").disabled = false
    this.disabled = true
    localStorage.setItem("sem", "started")
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/semester/start");
    xhr.send();
});

//End Button
document.getElementById("end-btn").addEventListener("click", function()
{
    document.getElementById("semester-status").value = "Not Started"
    document.getElementById("start-btn").disabled = false
    this.disabled = true
    localStorage.setItem("sem", "ended")
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/admin/semester/finish");
    xhr.send();
});

//Datepicker
startDate.min = new Date().toISOString().split("T")[0];
document.getElementById("startDate").addEventListener("change", function() {
    var input = this.value;
    endDate.min=input
});


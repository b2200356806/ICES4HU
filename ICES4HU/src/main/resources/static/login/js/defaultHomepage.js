//Get User Type
const dbParam13 = JSON.stringify();
const xmlhttp13 = new XMLHttpRequest();
xmlhttp13.onload = function () {
    let myObj = this.responseText;
    console.log(myObj)
    if (myObj === "STUDENT") {
        window.location.href = "http://localhost:8080/student/homePage"
    }
    else if (myObj === "ADMIN") {
        window.location.href = "http://localhost:8080/admin/homePage"
    }
    else if (myObj === "INSTRUCTOR") {
        window.location.href = "http://localhost:8080/instructor/homePage"
    }
    else if (myObj === "DEPARTMENT_MANAGER") {
        window.location.href = "http://localhost:8080/department_manager/homePage"
    }
}
xmlhttp13.open("GET", "http://localhost:8080/api/Authentication/getUserType");
xmlhttp13.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp13.setRequestHeader("Authorization", "Bearer "+localStorage.getItem("token"))
xmlhttp13.send(dbParam13);


//Get User ID
const dbParam14 = JSON.stringify();
const xmlhttp14 = new XMLHttpRequest();
xmlhttp14.onload = function () {
    let myObj = this.responseText;
    console.log(myObj)
    localStorage.setItem("userID",myObj)
}
xmlhttp14.open("GET", "http://localhost:8080/api/Authentication/getUserId");
xmlhttp14.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp14.setRequestHeader("Authorization", "Bearer "+localStorage.getItem("token"))
xmlhttp14.send(dbParam14);
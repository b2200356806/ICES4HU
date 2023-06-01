function logout () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/Authentication/logout", false);
    xhr.send();
    localStorage.clear()
    window.location.href = "http://localhost:8080/login"
}
function getToken () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/Authentication/authenticate");
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify({
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }));

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            localStorage.setItem("token",JSON.parse(xhr.responseText)["access_token"])
        }
    }
}
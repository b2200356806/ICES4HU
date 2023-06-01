function getToken () {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/Authentication/authenticate", false);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify({
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }));

    localStorage.setItem("token",JSON.parse(xhr.responseText)["access_token"])
}

function redirect() {
    window.location.href = "http://localhost:8080/register"
}
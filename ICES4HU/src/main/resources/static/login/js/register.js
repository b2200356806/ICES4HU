function createAccount() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/Authentication/register", false);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify({
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        userType: "STUDENT"
    }));
    console.log("hello")
    window.location.href = "http://localhost:8080/login"


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
                message: 'Registered successfully'
            });
            form.reset(); //reset form after AJAX success or do something else
        }
    }
    //Fail the onsubmit to avoid page refresh.
    return false;
}

function redirectLogin() {
    window.location.href = "http://localhost:8080/login"
}
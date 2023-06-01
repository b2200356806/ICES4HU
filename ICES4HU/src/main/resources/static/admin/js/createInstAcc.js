var form = document.getElementById('instructorForm');
form.onsubmit = function(event)
{
    var xhr = new XMLHttpRequest();
    var formData = new FormData(form);
    //open the request
    xhr.open('POST','http://localhost:8080/api/admin/1/create-instructor')
    xhr.setRequestHeader("Content-Type", "application/json");
    let depCheck = document.getElementById('flexCheckDefault')

    let instructorAdded = {
        firstName: formData.get("firstName"),
        lastName: formData.get("lastName"),
        department: {
            name: formData.get("department")
        },
        username: formData.get("email"),
        password: formData.get("password"),
        isDepartmentManager: depCheck.checked
    };

    //send the form data
    xhr.send(JSON.stringify(instructorAdded));

    let notification_message = ""

    if (depCheck.checked)
        notification_message = "Department manager created successfully"
    else notification_message = "Instructor created successfully"

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
                message: notification_message
            });
            form.reset(); //reset form after AJAX success or do something else
        }
    }
    //Fail the onsubmit to avoid page refresh.
    return false;
}

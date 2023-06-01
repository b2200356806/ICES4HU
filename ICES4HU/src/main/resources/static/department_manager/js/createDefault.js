$(function()
{
    $(document).on('click', '.btn-add', function(e)
    {
        e.preventDefault();

        var controlForm = $('.controls form:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).appendTo(controlForm);

        newEntry.find('input').val('');
        controlForm.find('.entry:not(:last) .btn-add')
            .removeClass('btn-add').addClass('btn-remove')
            .removeClass('btn-success').addClass('btn-danger')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-remove', function(e)
    {
        $(this).parents('.entry:first').remove();

        e.preventDefault();
        return false;
    });
});



//Get default questions preview
const dbParam5 = JSON.stringify();
const xmlhttp5 = new XMLHttpRequest();
xmlhttp5.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "";
    for (let x in myObj["defaultEvaluationQuestions"]) {
        text += `<div class="entry input-group col-xs-3" style="margin-left: 22%">`
        text += `<input type='text' style="color: black" class="form-control" readonly 
                        value="${myObj["defaultEvaluationQuestions"][x].questionText}">`;
        text += `<span class="input-group-btn">
                 <button class="btn btn-delete btn-danger" type="button">
                 <span class="glyphicon glyphicon-minus"></span>
                 </button></span></div>`;
    }
    document.getElementById("preview").innerHTML = text;
}
xmlhttp5.open("GET", "http://localhost:8080/api/department-managers/500/department");
xmlhttp5.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp5.send(dbParam5);


// Remove question from course
$(function()
{
    $(document).on('click', '.btn-delete', function()
    {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/api/department-managers/500/remove-evaluation-question");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify({id: 0}));
        location.reload()
    });
});

//Add form questions
function submitQuestion()
{
    var form = document.getElementById('evaluation-form');
    var xhr = new XMLHttpRequest();
    var formData = new FormData(form);
    let questions = formData.getAll("fields[]")
    questions = questions.filter((str) => str !== '');

    for (let x in questions) {
        let questionAdded = {
            questionText: questions[x]
        };

        //open the request
        xhr.open('POST','http://localhost:8080/api/department-managers/500/add-evaluation-question', false)
        xhr.setRequestHeader("Content-Type", "application/json");

        // send the form data
        xhr.send(JSON.stringify(questionAdded));
    }

    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            form.reset(); //reset form after AJAX success or do something else
        }
    }
    location.reload()
}
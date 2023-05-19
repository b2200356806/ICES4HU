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


//Get Courses of instructor
const dbParam3 = JSON.stringify();
const xmlhttp3 = new XMLHttpRequest();
xmlhttp3.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "<option></option>";
    for (let x in myObj) {
        text += `<option value="${myObj[x].id}">` + myObj[x].course.courseCode + " - " + myObj[x].course.name + "</option>";
    }
    document.getElementById("course").innerHTML = text;
}
xmlhttp3.open("GET", "http://localhost:8080/api/instructors/501/evaluation");
xmlhttp3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp3.send(dbParam3);


//Get evaluation preview
const dbParam4 = JSON.stringify();
const xmlhttp4 = new XMLHttpRequest();
xmlhttp4.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "";
    for (let x in myObj["defaultEvaluationQuestions"]) {
        text += `<div class="entry input-group col-xs-3" style="margin-left: 22%">`
        text += `<input type='text' style="color: black" class="form-control" readonly 
                        value="${myObj["defaultEvaluationQuestions"][x].questionText}"></div>`;
    }
    for (let x in myObj["instructorEvaluationQuestions"]) {
        text += `<div class="entry input-group col-xs-3" style="margin-left: 22%">`
        text += `<input type='text' style="color: black" class="form-control" readonly 
                        value="${myObj["instructorEvaluationQuestions"][x].questionText}">`;
        text += `<span class="input-group-btn">
                 <button class="btn btn-delete btn-danger" type="button">
                 <span class="glyphicon glyphicon-minus"></span>
                 </button></span></div>`;
    }
    document.getElementById("preview").innerHTML = text;
}

// Select course to create evaluate form
function getSelectID() {
    var courses = document.getElementById("course").options;
    var selectedCourse = document.getElementById("course").selectedIndex;
    return courses[selectedCourse].value;
}

// Get questions for preview
function selectEval() {
    let selectId = getSelectID();
    xmlhttp4.open("GET", "http://localhost:8080/api/instructors/501/evaluation/"+selectId);
    xmlhttp4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp4.send(dbParam4);
}

// Remove question from course
$(function()
{
    $(document).on('click', '.btn-delete', function()
    {
        var xhr = new XMLHttpRequest();
        let selectId = getSelectID();
        xhr.open("POST", "http://localhost:8080/api/instructors/501/evaluation/" + selectId + "/remove-question");
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
    let selectId = getSelectID();
    let questions = formData.getAll("fields[]")
    console.log(questions)
    questions = questions.filter((str) => str !== '');
    console.log(questions)

    for (let x in questions) {
        let questionAdded = {
            questionText: questions[x]
        };

        //open the request
        xhr.open('POST','http://localhost:8080/api/instructors/501/evaluation/' + selectId + '/add-question', false)
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
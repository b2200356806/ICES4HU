//Get Courses of student
const dbParam5 = JSON.stringify();
const xmlhttp5 = new XMLHttpRequest();
xmlhttp5.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "<option></option>";
    for (let x in myObj) {
        text += `<option value="${myObj[x].id}">` + myObj[x].course.courseCode + " - " + myObj[x].course.name + "</option>";
    }
    document.getElementById("course").innerHTML = text;
}
xmlhttp5.open("GET", "http://localhost:8080/api/students/1/evaluation");
xmlhttp5.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp5.send(dbParam5);


//Get evaluation preview
const dbParam6 = JSON.stringify();
const xmlhttp6 = new XMLHttpRequest();
xmlhttp6.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "";
    let number = 0;
    for (let x in myObj["evaluationQuestions"]) {
        let response = "question"+number
        let qID = myObj["evaluationQuestions"][x].id
        text += `<div class="mx-0 mx-sm-auto">
                    <div class="text-center">
                        <p>
                            <strong>${myObj["evaluationQuestions"][x].questionText}</strong>
                        </p>
                    </div>
                    <div class="text-center mb-3">
                    
                        <div class="d-inline mx-3">
                            Bad
                        </div>

                        <div class="form-check form-check-inline ${qID}">
                            <input class="form-check-input" type="radio" id="inlineRadio1" name="${response}"
                                   value=1>
                            <label class="form-check-label" for="inlineRadio1">1</label>
                        </div>
    
                        <div class="form-check form-check-inline ${qID}">
                            <input class="form-check-input" type="radio" id="inlineRadio2" name="${response}"
                                   value=2>
                            <label class="form-check-label" for="inlineRadio2">2</label>
                        </div>
    
                        <div class="form-check form-check-inline ${qID}">
                            <input class="form-check-input" type="radio" id="inlineRadio3" name="${response}"
                                   value=3>
                            <label class="form-check-label" for="inlineRadio3">3</label>
                        </div>
    
                        <div class="form-check form-check-inline ${qID}">
                            <input class="form-check-input" type="radio" id="inlineRadio4" name="${response}"
                                   value=4>
                            <label class="form-check-label" for="inlineRadio4">4</label>
                        </div>
    
                        <div class="form-check form-check-inline ${qID}">
                            <input class="form-check-input" type="radio" id="inlineRadio5" name="${response}"
                                   value=5>
                            <label class="form-check-label" for="inlineRadio5">5</label>
                        </div>
                        <div class="d-inline me-4">
                            Excellent
                        </div>
                    </div>
                </div>`
        number++
    }
    text += `<div class="text-end">
             <button type="button" class="btn btn-primary" onclick="getSelected()">Submit</button>
             </div>`
    document.getElementById("preview").innerHTML = text;
}


// Select course to answer
function getSelectCourse() {
    var courses = document.getElementById("course").options;
    var selectedCourseCode = document.getElementById("course").selectedIndex;
    return courses[selectedCourseCode].text.split("-")[0];
}

// Select evaluation form ID to evaluate
function getSelectID() {
    var courses = document.getElementById("course").options;
    var selectedEval = document.getElementById("course").selectedIndex;
    return courses[selectedEval].value;
}


// Get questions for evaluation
function selectEval() {
    let selectId = getSelectID();
    xmlhttp6.open("GET", "http://localhost:8080/api/students/1/evaluation/" + selectId);
    xmlhttp6.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp6.send(dbParam6);
}


let responses = {};

// watch for change events on the radio
$("#preview").on('change', 'input[type="radio"]' , function () {
    let value = $(this).closest("div")[0];
    let qid = value.className.split(" ").slice(-1)[0]

    let value2 = $(this).closest("input")[0];
    responses[qid] = parseInt(value2.value);
});


function getSelected() {
    const answers = responses;
    let xhr2 = new XMLHttpRequest();
    let selectId = getSelectCourse();
    xhr2.open("POST", "http://localhost:8080/api/students/1/evaluation/"+selectId, false);
    xhr2.setRequestHeader("Content-Type", "application/json");
    let final_responses = []
    for (let i in answers) {
        let response = {
            evaluationQuestionId: parseInt(i),
            response: answers[i]
        };
        final_responses.push(response);
    }
    xhr2.send(JSON.stringify(final_responses));
    location.reload();
}

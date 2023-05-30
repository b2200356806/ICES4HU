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
    for (let x in myObj["evaluationQuestions"]) {
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

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                                   value="option1"/>
                            <label class="form-check-label" for="inlineRadio1">1</label>
                        </div>
    
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                                   value="option2"/>
                            <label class="form-check-label" for="inlineRadio2">2</label>
                        </div>
    
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3"
                                   value="option3"/>
                            <label class="form-check-label" for="inlineRadio3">3</label>
                        </div>
    
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio4"
                                   value="option4"/>
                            <label class="form-check-label" for="inlineRadio4">4</label>
                        </div>
    
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio5"
                                   value="option5"/>
                            <label class="form-check-label" for="inlineRadio5">5</label>
                        </div>
                        <div class="d-inline me-4">
                            Excellent
                        </div>
                    </div>
                    <div class="text-end">
                        <button type="button" class="btn btn-primary">Submit</button>
                    </div>
                </div>`
    }
    document.getElementById("preview").innerHTML = text;
}

// Select course to evaluate
function getSelectID() {
    var courses = document.getElementById("course").options;
    var selectedCourse = document.getElementById("course").selectedIndex;
    return courses[selectedCourse].value;
}


// Get questions for evaluation
function selectEval() {
    let selectId = getSelectID();
    xmlhttp6.open("GET", "http://localhost:8080/api/students/1/evaluation/" + selectId);
    xmlhttp6.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp6.send(dbParam6);
}


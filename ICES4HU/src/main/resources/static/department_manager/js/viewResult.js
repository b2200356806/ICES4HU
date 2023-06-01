let insID = 500;

//Get Instructor ID of department manager
const dbParam12 = JSON.stringify();
const xmlhttp12 = new XMLHttpRequest();
xmlhttp12.onload = function () {
    insID = JSON.parse(this.responseText)
}
xmlhttp12.open("GET", "http://localhost:8080/api/department-managers/500/get-instructor-id");
xmlhttp12.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp12.send(dbParam12);



//Get Courses of instructor
const dbParam13 = JSON.stringify();
const xmlhttp13 = new XMLHttpRequest();
xmlhttp13.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "<option></option>";
    for (let x in myObj) {
        text += `<option value="${myObj[x].id}">` + myObj[x].course.courseCode + " - " + myObj[x].course.name + "</option>";
    }
    document.getElementById("course").innerHTML = text;
}
xmlhttp13.open("GET", "http://localhost:8080/api/instructors/" + insID + "/evaluation");
xmlhttp13.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp13.send(dbParam13);


//Get evaluation results
const dbParam14 = JSON.stringify();
const xmlhttp14 = new XMLHttpRequest();
xmlhttp14.onload = function () {
    let myObj = JSON.parse(this.responseText);
    for (let x in myObj["responses"]) {
        let temp = myObj["responses"][x]
        const counts = {}
        for (const num of temp) {
            counts[num] = counts[num] ? counts[num] + 1 : 1;
        }
        const results = [counts[1], counts[2], counts[3], counts[4], counts[5]];
        const myChart = new Chart(document.getElementById('chart').getContext("2d"), {
            type: 'horizontalBar',
            data: {
                labels: ['Very Bad', 'Bad', 'Normal', 'Good', 'Very Good'],
                datasets: [{
                    label: 'My data',
                    data: results,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255,99,132,1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    xAxes: [{
                        ticks: {
                            min: 0 // Edit the value according to what you need
                        }
                    }],
                },
                animation: {
                    duration: 1500,
                }
            }
        });

        document.getElementById('btn-download').onclick = function () {
            // Trigger the download
            var a = document.createElement('a');
            a.href = myChart.toBase64Image();
            a.download = 'my_file_name.png';
            a.click();
        }
    }
}


// Get results for evaluation
function selectEval() {
    let selectCode = getSelectCourse();
    xmlhttp14.open("GET", "http://localhost:8080/api/instructors/" + insID + "/evaluation/result/" + selectCode);
    xmlhttp14.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp14.send(dbParam14);
}


// Select course to view result
function getSelectCourse() {
    var courses = document.getElementById("course").options;
    var selectedCourseCode = document.getElementById("course").selectedIndex;
    return courses[selectedCourseCode].text.split("-")[0];
}

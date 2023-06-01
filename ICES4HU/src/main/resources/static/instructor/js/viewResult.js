//Get Courses of instructor
const dbParam10 = JSON.stringify();
const xmlhttp10 = new XMLHttpRequest();
xmlhttp10.onload = function () {
    let myObj = JSON.parse(this.responseText);
    let text = "<option></option>";
    for (let x in myObj) {
        text += `<option value="${myObj[x].id}">` + myObj[x].course.courseCode + " - " + myObj[x].course.name + "</option>";
    }
    document.getElementById("course").innerHTML = text;
}
xmlhttp10.open("GET", "http://localhost:8080/api/instructors/501/evaluation");
xmlhttp10.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp10.send(dbParam10);


//Get evaluation results
const dbParam11 = JSON.stringify();
const xmlhttp11 = new XMLHttpRequest();
xmlhttp11.onload = function () {
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
    xmlhttp11.open("GET", "http://localhost:8080/api/instructors/501/evaluation/result/" + selectCode);
    xmlhttp11.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp11.send(dbParam11);
}


// Select course to view result
function getSelectCourse() {
    var courses = document.getElementById("course").options;
    var selectedCourseCode = document.getElementById("course").selectedIndex;
    return courses[selectedCourseCode].text.split("-")[0];
}

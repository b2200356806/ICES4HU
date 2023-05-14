# Penguin_BBM384_Project


## API Endpoints

### Admin
| Description                 | Method | RequestBody        | Endpoint                                                 | Notes                    |
| :---------------------------| :------| :------------------| :--------------------------------------------------------| :----------------------- |
| Get all courses             | GET    |                    | /api/admin/{user_id}/courses                             ||
| Add a course                | POST   | Course             | /api/admin/{user_id}/courses/add                         ||
| Remove a course             | POST   | Course             | /api/admin/{user_id}/courses/remove                      |Only courseCode attribute is required in the requestBody|
| Start semester              | POST   |                    | /api/admin/{user_id}/semester/start                      ||
| Finish semester             | POST   |                    | /api/admin/{user_id}/semester/finish                     ||
| Start evaluation            | POST   |                    | /api/admin/{user_id}/evaluation/start                    ||
| Finish evaluation           | POST   |                    | /api/admin/{user_id}/evaluation/finish                   ||
| Start add/drop              | POST   |                    | /api/admin/{user_id}/add-or-drop/start                   ||
| Finish add/drop             | POST   |                    | /api/admin/{user_id}/add-or-drop/finish                  ||
| Get semester info           | GET    |                    | /api/admin/{user_id}/semester                            ||
| Update account info         | POST   | UserUpdateRequest  | /api/admin/{user_id}/update-info                         ||
| Admit student               | POST   |                    | /api/admin/{user_id}/admit/{student_id}                  ||
| Create first admin account  | POST   |                    | /api/admin/{user_id}/create-first-admin                  ||
| Add some examples           | POST   |                    | /api/admin/{user_id}/add-examples                        ||

### Department Manager
| Description                     | Method | RequestBody        | Endpoint                                                               | Notes |
| :-------------------------------| :------| :------------------| :----------------------------------------------------------------------| :-----|
| Get account info                | GET    |                    | /api/department-managers/{user_id}/                                    ||
| Get courses in department       | GET    |                    | /api/department-managers/{user_id}/courses                             ||
| Get instructors in department   | GET    |                    | /api/department-managers/{user_id}/instructors                         ||
| Get department info             | GET    |                    | /api/department-managers/{user_id}/department                          ||
| Assign instructor to course     | POST   | Instructor         | /api/department-managers/{user_id}/assign/{course_code}                | Only Instructor.userId attribute is required in the requestBody|
| Add evaluation question         | POST   | EvaluationQuestion | /api/department-managers/{user_id}/add-evaluation-question             ||
| Remove evaluation question      | POST   | EvaluationQuestion | /api/department-managers/{user_id}/remove-evaluation-question          ||
| Update account info             | POST   | UserUpdateRequest  | /api/department-managers/{user_id}/update-info                         ||


### Instructor
| Description                            | Method | RequestBody        | Endpoint                                                                 | Notes |
| :--------------------------------------| :------| :------------------| :--------------------------------------------------------------          | :-----|
| Get account info                       | GET    |                    | /api/instructors/{user_id}/                                              ||
| Get courses                            | GET    |                    | /api/instructors/{user_id}/courses                                       ||
| Create/get EvaluationForms for courses | GET    |                    | /api/instructors/{user_id}/evaluation                                    ||
| Get EvaluationForm for a course          | GET    |                    | /api/instructors/{user_id}/evaluation/{evaluationform_id}                ||
| Add evaluation question to a course      | POST   | EvaluationQuestion | /api/instructors/{user_id}/evaluation/{evaluationform_id}/add-question    ||
| Remove evaluation question from a course | POST   | EvaluationQuestion | /api/instructors/{user_id}/evaluation/{evaluationform_id}/remove-question | Only questionId is required in the requestBody|
| Get evaluation result for a course     | GET    |                    | /api/instructors/{user_id}/evaluation/result/{course_code}                ||
| Update account info                    | POST   | UserUpdateRequest  | /api/instructors/{user_id}/update-info                                   ||


### Student
| Description                            | Method | RequestBody        | Endpoint                                                              | Notes |
| :--------------------------------------| :------| :------------------| :--------------------------------------------------------------       | :----|
| Get account info                       | GET    |                    | /api/students/{user_id}/                                              ||
| Get courses                            | GET    |                    | /api/students/{user_id}/courses                                       ||
| Enroll to a course                     | POST   |                    | /api/students/{user_id}/courses/enroll/{course_code}                  ||
| Drop a course                          | POST   |                    | /api/students/{user_id}/courses/drop/{course_code}                    ||
| Get EvaluationForms for courses        | GET    |                    | /api/students/{user_id}/evaluation                                    ||
| Evaluate a course                      | POST   | List of EvaluationResponseDto | /api/students/{user_id}/evaluation/{course_code}             ||
| Update account info                    | POST   | UserUpdateRequest  | /api/students/{user_id}/update-info                                   ||




## URLs

1. Admin homepage: http://localhost:8080/admin/homePage
2. Admin add courses: http://localhost:8080/admin/addCourse
3. Admin manage semester: http://localhost:8080/admin/manageSemester

4. Student homepage: http://localhost:8080/student/homePage
5. Student enroll courses: http://localhost:8080/student/enrollCourses

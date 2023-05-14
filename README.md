# Penguin_BBM384_Project


## API Endpoints

### Admin
| Description                 | Method | RequestBody        | Endpoint                                                 |
| :---------------------------| :------| :------------------| :--------------------------------------------------------|
| Get all courses             | GET    |                    | /api/admin/{user_id}/courses                             |
| Add a course                | POST   | Course             | /api/admin/{user_id}/courses/add                         |
| Remove a course             | POST   | Course             | /api/admin/{user_id}/courses/remove                      | 
| Start semester              | POST   |                    | /api/admin/{user_id}/semester/start                      |
| Finish semester             | POST   |                    | /api/admin/{user_id}/semester/finish                     |
| Start evaluation            | POST   |                    | /api/admin/{user_id}/evaluation/start                    |
| Finish evaluation           | POST   |                    | /api/admin/{user_id}/evaluation/finish                   |
| Start add/drop              | POST   |                    | /api/admin/{user_id}/add-or-drop/start                   |
| Finish add/drop             | POST   |                    | /api/admin/{user_id}/add-or-drop/finish                  |
| Get semester info           | GET    |                    | /api/admin/{user_id}/semester                            |
| Update account info         | POST   | UserUpdateRequest  | /api/admin/{user_id}/update-info                         |
| Admit student               | POST   |                    | /api/admin/{user_id}/admit/{student_id}                  |
| Create first admin account  | POST   |                    | /api/admin/{user_id}/create-first-admin                  |

### Department Manager
| Description                     | Method | RequestBody        | Endpoint                                                               |
| :-------------------------------| :------| :------------------| :----------------------------------------------------------------------|
| Get account info                | GET    |                    | /api/department-managers/{user_id}/                                    |
| Get courses in department       | GET    |                    | /api/department-managers/{user_id}/courses                             |
| Get instructors in department   | GET    |                    | /api/department-managers/{user_id}/instructors                         |
| Get department info             | GET    |                    | /api/department-managers/{user_id}/department                          |
| Assign instructor to course     | POST   | Instructor         | /api/department-managers/{user_id}/assign/{course_code}                |
| Add evaluation question         | POST   | EvaluationQuestion | /api/department-managers/{user_id}/add-evaluation-question             |
| Remove evaluation question      | POST   | EvaluationQuestion | /api/department-managers/{user_id}/remove-evaluation-question          |
| Update account info             | POST   | UserUpdateRequest  | /api/department-managers/{user_id}/update-info                         |




Add some examples of students and courses to the database: /api/admin/add-examples

1. List all the students: /api/students/all
2. Get a student info: /api/students/{user_id}
3. Get courses taken by a student: /api/students/{user_id}/courses
4. Enroll a student to a course: /api/students/{user_id}/enroll/{course_code}
5. Drop a student from a course: /api/students/{user_id}/drop/{course_code}

6. List all the courses: /api/courses/all

7. Add a course: /api/admin/courses/add
8. Start the semester: /api/admin/semester/start
9. Finish the semester: /api/admin/semester/finish
10. Get semester status: /api/admin/semester/status


## URLs

1. Admin homepage: http://localhost:8080/admin/homePage
2. Admin add courses: http://localhost:8080/admin/addCourse
3. Admin manage semester: http://localhost:8080/admin/manageSemester

4. Student homepage: http://localhost:8080/student/homePage
5. Student enroll courses: http://localhost:8080/student/enrollCourses

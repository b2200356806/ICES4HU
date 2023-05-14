# Penguin_BBM384_Project


## API Endpoints

### Admin
| Description     | Method | RequestBody | Endpoint |
| Get all courses | GET    |             |/api/admin/{user_id}/courses |
| Add a course    | POST   |    Course   | /api/admin/{user_id}/courses/add |



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

package penguins.backend.Evaluation.CourseEvaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import penguins.backend.Course.Course;
import penguins.backend.Student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@Repository
public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation, Long> {
    /*private final List<CourseEvaluation> courseEvaluations = new ArrayList<>();


    public List<CourseEvaluation> findByCourse(Course course) {
        List<CourseEvaluation> output = new ArrayList<>();
        for (CourseEvaluation courseEvaluation : courseEvaluations) {
            if (courseEvaluation.getCourse().equals(course)) {
                output.add(courseEvaluation);
            }
        }
        return output;
    }


    public boolean existsByCourseAndStudent(Course course, Student student) {
        for (CourseEvaluation courseEvaluation : courseEvaluations) {
            if (courseEvaluation.getCourse().equals(course) && courseEvaluation.getStudent().equals(student)) {
                return true;
            }
        }
        return false;
    }

    public CourseEvaluation save(CourseEvaluation courseEvaluation) {
        courseEvaluations.remove(courseEvaluation);
        courseEvaluations.add(courseEvaluation);
        return courseEvaluation;
    }*/

    List<CourseEvaluation> findByCourse(Course course);

    boolean existsByCourseAndStudent(Course course, Student student);

}

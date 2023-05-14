package penguins.backend.Evaluation.CourseEvaluation;

import lombok.Data;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponse;
import penguins.backend.Student.Student;

import java.util.List;

@Data
public class CourseEvaluation {

    private long id;
    private Course course;
    private Student student;
    private List<EvaluationResponse> responses;

}

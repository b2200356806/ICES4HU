package penguins.backend.Evaluation.CourseEvaluation;

import lombok.Data;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

import java.util.List;
import java.util.Map;

@Data
public class CourseEvaluationDto {

    private long id;
    private Course course;
    private Map<EvaluationQuestion, List<Integer>> responses;

}

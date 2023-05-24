package penguins.backend.Evaluation.EvaluationForm;

import lombok.Data;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

import java.util.ArrayList;
import java.util.List;

@Data
public class EvaluationForm {

    private long id;
    private Course course;
    private List<EvaluationQuestion> defaultEvaluationQuestions;
    private List<EvaluationQuestion> instructorEvaluationQuestions;

    public List<EvaluationQuestion> getEvaluationQuestions() {
        List<EvaluationQuestion> output = new ArrayList<>(defaultEvaluationQuestions);
        output.addAll(instructorEvaluationQuestions);
        return output;
    }
}

package penguins.backend.Department;

import lombok.Data;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

import java.util.List;

@Data
public class Department {
    private long id;
    private String name;
    private List<EvaluationQuestion> defaultEvaluationQuestions;
}

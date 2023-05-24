package penguins.backend.Evaluation.EvaluationResponse;

import lombok.Data;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

@Data
public class EvaluationResponse {

    private long id;
    private EvaluationQuestion evaluationQuestion;
    private int response;
}

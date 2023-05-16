package penguins.backend.Evaluation.EvaluationResponse;

import lombok.Data;

@Data
public class EvaluationResponseDto {

    private long id;
    private long evaluationQuestionId;
    private int response;

}

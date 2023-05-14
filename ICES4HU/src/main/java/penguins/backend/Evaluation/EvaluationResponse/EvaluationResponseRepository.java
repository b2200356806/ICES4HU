package penguins.backend.Evaluation.EvaluationResponse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EvaluationResponseRepository {
    private final List<EvaluationResponse> responses = new ArrayList<>();

    public EvaluationResponse save(EvaluationResponse evaluationResponse) {
        responses.remove(evaluationResponse);
        responses.add(evaluationResponse);
        return evaluationResponse;
    }

}

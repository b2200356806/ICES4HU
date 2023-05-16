package penguins.backend.Evaluation.EvaluationException;

public class EvaluationQuestionNotFoundException extends RuntimeException {
    public EvaluationQuestionNotFoundException(String message) {
        super(message);
    }
}

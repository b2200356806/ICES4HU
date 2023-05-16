package penguins.backend.Evaluation.EvaluationException;

public class CourseAlreadyEvaluatedException extends RuntimeException {
    public CourseAlreadyEvaluatedException(String message) {
        super(message);
    }
}

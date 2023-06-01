package penguins.backend.Semester.SemesterException;

public class EvaluationNotStartedException extends RuntimeException {
    public EvaluationNotStartedException(String message) {
        super(message);
    }
}

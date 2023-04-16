package penguins.backend.Semester.Exception;

public class SemesterNotStartedException extends RuntimeException {
    public SemesterNotStartedException(String message) {
        super(message);
    }
}

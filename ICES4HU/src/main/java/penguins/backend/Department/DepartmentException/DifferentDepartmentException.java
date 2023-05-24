package penguins.backend.Department.DepartmentException;

public class DifferentDepartmentException extends RuntimeException {
    public DifferentDepartmentException(String message) {
        super(message);
    }
}

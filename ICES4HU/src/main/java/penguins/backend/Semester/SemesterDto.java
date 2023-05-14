package penguins.backend.Semester;

import lombok.Data;

@Data
public class SemesterDto {
    private boolean semesterStarted = false;
    private boolean evaluationStarted = false;
    private boolean addOrDropStarted = false;
}

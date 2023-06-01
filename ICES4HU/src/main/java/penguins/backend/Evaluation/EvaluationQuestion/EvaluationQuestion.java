package penguins.backend.Evaluation.EvaluationQuestion;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Department.Department;

@Data
@Entity
public class EvaluationQuestion {

    @Id
    private long id;
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}

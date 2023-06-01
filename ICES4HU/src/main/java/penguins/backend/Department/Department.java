package penguins.backend.Department;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Student.Student;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Department {

    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<EvaluationQuestion> defaultEvaluationQuestions;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Student> students;
}

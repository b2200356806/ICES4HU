package penguins.backend.Evaluation.EvaluationForm;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class EvaluationForm {

    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    @JoinColumn(name = "default_evaluation_form_id")
    private List<EvaluationQuestion> defaultEvaluationQuestions;

    @OneToMany
    @JoinColumn(name = "instructor_evaluation_form_id")
    private List<EvaluationQuestion> instructorEvaluationQuestions;

    public List<EvaluationQuestion> getEvaluationQuestions() {
        List<EvaluationQuestion> output = new ArrayList<>(defaultEvaluationQuestions);
        output.addAll(instructorEvaluationQuestions);
        return output;
    }
}

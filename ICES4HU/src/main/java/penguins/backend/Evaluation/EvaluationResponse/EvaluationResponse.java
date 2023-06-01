package penguins.backend.Evaluation.EvaluationResponse;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluation;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;

@Data
@Entity
public class EvaluationResponse {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private EvaluationQuestion evaluationQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_evaluation_id")
    private CourseEvaluation courseEvaluation;


    private int response;
}

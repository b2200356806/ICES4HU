package penguins.backend.Evaluation.EvaluationForm;

import org.springframework.stereotype.Component;
import penguins.backend.Course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EvaluationFormRepository {
    private final List<EvaluationForm> evaluationForms = new ArrayList<>();


    public Optional<EvaluationForm> findById(long id) {
        for (EvaluationForm evaluationForm : evaluationForms) {
            if (evaluationForm.getId() == id) {
                return Optional.of(evaluationForm);
            }
        }

        return Optional.empty();
    }


    public Optional<EvaluationForm> findByCourse(Course course) {
        for (EvaluationForm evaluationForm : evaluationForms) {
            if (evaluationForm.getCourse().equals(course)) {
                return Optional.of(evaluationForm);
            }
        }

        return Optional.empty();
    }

    public EvaluationForm save(EvaluationForm evaluationForm) {
        evaluationForms.remove(evaluationForm);
        evaluationForms.add(evaluationForm);
        return evaluationForm;
    }

}

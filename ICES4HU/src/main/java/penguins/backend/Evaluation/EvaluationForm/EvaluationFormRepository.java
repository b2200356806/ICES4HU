package penguins.backend.Evaluation.EvaluationForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import penguins.backend.Course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@Repository
public /*class */interface EvaluationFormRepository extends JpaRepository<EvaluationForm, Long> {
    /*private final List<EvaluationForm> evaluationForms = new ArrayList<>();


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
    }*/

    Optional<EvaluationForm> findById(long id);

    Optional<EvaluationForm> findByCourse(Course course);



}

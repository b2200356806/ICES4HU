package penguins.backend.Evaluation.EvaluationResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Component
@Repository
public interface EvaluationResponseRepository extends JpaRepository<EvaluationResponse, Long> {
    /*private final List<EvaluationResponse> responses = new ArrayList<>();

    public EvaluationResponse save(EvaluationResponse evaluationResponse) {
        responses.remove(evaluationResponse);
        responses.add(evaluationResponse);
        return evaluationResponse;
 }*/


}

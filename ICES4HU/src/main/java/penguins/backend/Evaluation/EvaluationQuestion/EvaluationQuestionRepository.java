package penguins.backend.Evaluation.EvaluationQuestion;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EvaluationQuestionRepository {
    private final List<EvaluationQuestion> questions = new ArrayList<>();

    public Optional<EvaluationQuestion> findById(long questionId) {
        for (EvaluationQuestion evaluationQuestion : questions) {
            if (evaluationQuestion.getId() == questionId) {
                return Optional.of(evaluationQuestion);
            }
        }
        return Optional.empty();
    }

    public void deleteById(long questionId) {
        questions.removeIf(evaluationQuestion -> evaluationQuestion.getId() == questionId);
    }

    public EvaluationQuestion save(EvaluationQuestion evaluationQuestion) {
        questions.remove(evaluationQuestion);
        questions.add(evaluationQuestion);
        return evaluationQuestion;
    }
}

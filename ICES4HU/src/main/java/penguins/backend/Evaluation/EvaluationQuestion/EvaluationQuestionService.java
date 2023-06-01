package penguins.backend.Evaluation.EvaluationQuestion;

import org.springframework.stereotype.Service;
import penguins.backend.Evaluation.EvaluationException.EvaluationQuestionNotFoundException;

@Service
public class EvaluationQuestionService {

    private final EvaluationQuestionRepository evaluationQuestionRepository;

    public EvaluationQuestionService(EvaluationQuestionRepository evaluationQuestionRepository) {
        this.evaluationQuestionRepository = evaluationQuestionRepository;
    }

    /**
     * Creates and returns an EvaluationQuestion object based on the given question text.
     * @param questionText the question text
     * @return EvaluationQuestion object created
     */
    public EvaluationQuestion createEvaluationQuestion(String questionText) {
        EvaluationQuestion evaluationQuestion = new EvaluationQuestion();
        evaluationQuestion.setQuestionText(questionText);
        evaluationQuestionRepository.save(evaluationQuestion);
        return evaluationQuestion;
    }


    /**
     * Removes the evaluationQuestion from the database.
     * @param questionId Question id
     */
    public void removeEvaluationQuestion(long questionId) {
        evaluationQuestionRepository.deleteById(questionId);
    }


    /**
     * Finds and returns the evaluation question with the given id.
     * @param questionId question id
     * @return Evaluation question with the given id
     * @throws EvaluationQuestionNotFoundException if there is no evaluation question with the given id
     */
    public EvaluationQuestion getEvaluationQuestionById(long questionId) throws EvaluationQuestionNotFoundException {
        return evaluationQuestionRepository.findById(questionId)
                .orElseThrow(() -> new EvaluationQuestionNotFoundException("Evaluation question not found. Question i d: " + questionId));
    }

}

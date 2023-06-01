package penguins.backend.Evaluation.EvaluationForm;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestionService;

import java.util.ArrayList;

@Service
public class EvaluationFormService {

    private final EvaluationFormRepository evaluationFormRepository;
    private final EvaluationQuestionService evaluationQuestionService;

    public EvaluationFormService(EvaluationFormRepository evaluationFormRepository,
                                 EvaluationQuestionService evaluationQuestionService) {
        this.evaluationFormRepository = evaluationFormRepository;
        this.evaluationQuestionService = evaluationQuestionService;
    }


    /**
     * Creates and evaluationForm for the given course if it doesn't already exist.
     * Otherwise, it returns the existing evaluationForm.
     *
     * @param course Course to create an evaluationForm for
     * @return EvaluationForm for the course
     */
    public EvaluationForm createOrGetEvaluationForm(Course course) {
        EvaluationForm evaluationForm = evaluationFormRepository.findByCourse(course).orElse(null);
        if (evaluationForm == null) {
            evaluationForm = new EvaluationForm();
            evaluationForm.setCourse(course);
            evaluationForm.setDefaultEvaluationQuestions(course.getDepartment().getDefaultEvaluationQuestions());
            evaluationForm.setInstructorEvaluationQuestions(new ArrayList<>());
            evaluationFormRepository.save(evaluationForm);
        }

        return evaluationForm;
    }


    /**
     * Gets evaluationForm for the given course.
     *
     * @param course Course to create an evaluationForm for
     * @return EvaluationForm for the course
     */
    public EvaluationForm getEvaluationForm(Course course) {
        return evaluationFormRepository.findByCourse(course).orElse(null);
    }


    /**
     * Adds an evaluationQuestion to the evaluationForm for the given course.
     *
     * @param evaluationFormId The evaluationForm id for which the question is being created
     * @param questionText     EvaluationQuestion text
     * @return The evaluationForm for the course with the new question
     */
    public EvaluationForm addEvaluationQuestion(long evaluationFormId, String questionText) {
        EvaluationForm evaluationForm = getEvaluationForm(evaluationFormId);
        EvaluationQuestion evaluationQuestion = evaluationQuestionService.createEvaluationQuestion(questionText);
        evaluationForm.getInstructorEvaluationQuestions().add(evaluationQuestion);
        evaluationFormRepository.save(evaluationForm);
        return evaluationForm;
    }


    /**
     * Removes the evaluationQuestion from the given evaluationForm.
     *
     * @param evaluationFormId Evaluation form id
     * @param questionId       Question id
     * @return the updated evaluation form
     */
    public EvaluationForm removeEvaluationQuestion(long evaluationFormId, long questionId) {
        EvaluationForm evaluationForm = getEvaluationForm(evaluationFormId);
        evaluationForm.getInstructorEvaluationQuestions().removeIf(evaluationQuestion -> evaluationQuestion.getId() == questionId);
        evaluationFormRepository.save(evaluationForm);
        evaluationQuestionService.removeEvaluationQuestion(questionId);
        return evaluationForm;
    }


    /**
     * Finds the evaluation form using the given id
     *
     * @param id evaluation form id
     * @return Evaluation form object with the given id
     */
    public EvaluationForm getEvaluationForm(long id) {
        return evaluationFormRepository.findById(id).orElse(null);
    }
}

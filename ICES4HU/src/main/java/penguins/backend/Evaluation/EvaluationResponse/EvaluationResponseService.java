package penguins.backend.Evaluation.EvaluationResponse;

import org.springframework.stereotype.Service;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationResponseService {
    private final EvaluationResponseRepository evaluationResponseRepository;
    private final EvaluationQuestionService evaluationQuestionService;

    public EvaluationResponseService(EvaluationResponseRepository evaluationResponseRepository,
                                     EvaluationQuestionService evaluationQuestionService) {
        this.evaluationResponseRepository = evaluationResponseRepository;
        this.evaluationQuestionService = evaluationQuestionService;
    }


    /**
     * Creates and saves evaluation responses from the given evaluation response dto list
     *
     * @param evaluationResponseDtoList List of evaluation response dto
     * @return List of evaluation responses
     */
    public List<EvaluationResponse> createEvaluationResponses(List<EvaluationResponseDto> evaluationResponseDtoList) {
        List<EvaluationResponse> output = new ArrayList<>();
        for (EvaluationResponseDto dto : evaluationResponseDtoList) {
            EvaluationQuestion evaluationQuestion = evaluationQuestionService.getEvaluationQuestionById(dto.getEvaluationQuestionId());

            EvaluationResponse evaluationResponse = new EvaluationResponse();
            evaluationResponse.setEvaluationQuestion(evaluationQuestion);
            evaluationResponse.setResponse(dto.getResponse());
            evaluationResponseRepository.save(evaluationResponse);
            output.add(evaluationResponse);
        }
        return output;
    }


}

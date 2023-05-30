package penguins.backend.Evaluation.CourseEvaluation;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationException.CourseAlreadyEvaluatedException;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponseDto;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponseService;
import penguins.backend.Student.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseEvaluationService {

    private final CourseEvaluationRepository courseEvaluationRepository;
    private final EvaluationResponseService evaluationResponseService;

    public CourseEvaluationService(CourseEvaluationRepository courseEvaluationRepository,
                                   EvaluationResponseService evaluationResponseService) {
        this.courseEvaluationRepository = courseEvaluationRepository;
        this.evaluationResponseService = evaluationResponseService;
    }


    /**
     * Evaluates a course and sets the responses
     *
     * @param student   Student evaluating the course
     * @param course    The course being evaluated
     * @param responses Responses given by the student
     * @throws CourseAlreadyEvaluatedException if the course was already evaluated by the student
     */
    public void evaluate(Student student, Course course, List<EvaluationResponseDto> responses) throws CourseAlreadyEvaluatedException {
        if (courseEvaluationRepository.existsByCourseAndStudent(course, student)) {
            throw new CourseAlreadyEvaluatedException("Course already evaluated by the student.");
        }
        CourseEvaluation courseEvaluation = new CourseEvaluation();
        courseEvaluation.setCourse(course);
        courseEvaluation.setStudent(student);
        courseEvaluation.setResponses(evaluationResponseService.createEvaluationResponses(responses));
        courseEvaluationRepository.save(courseEvaluation);
    }


    /**
     * Finds and returns the course evaluations for the given course.
     *
     * @param course course
     * @return list of course evaluations for the given course
     */
    public List<CourseEvaluation> getCourseEvaluations(Course course) {
        return new ArrayList<>(courseEvaluationRepository.findByCourse(course));
    }

}

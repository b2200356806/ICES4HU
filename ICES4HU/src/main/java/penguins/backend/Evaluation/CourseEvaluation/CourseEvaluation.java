package penguins.backend.Evaluation.CourseEvaluation;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponse;
import penguins.backend.Student.Student;

import java.util.List;

@Data
@Entity
public class CourseEvaluation {

    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "courseEvaluation")
    private List<EvaluationResponse> responses;

}

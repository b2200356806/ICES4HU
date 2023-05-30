package penguins.backend.Instructor;

import org.springframework.stereotype.Component;
import penguins.backend.Department.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();

    public Optional<Instructor> findByUsername(String username) {
        for (Instructor instructor : instructors) {
            if (instructor.getUsername().equals(username)) {
                return Optional.of(instructor);
            }
        }

        return Optional.empty();
    }

    public Optional<Instructor> findById(long userId) {
        for (Instructor instructor : instructors)
            if (instructor.getUserId() == userId)
                return Optional.of(instructor);
        return Optional.empty();
    }

    public List<Instructor> findByDepartment(Department department) {
        List<Instructor> output = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getDepartment().equals(department)) {
                output.add(instructor);
            }
        }

        return output;
    }

    public Instructor save(Instructor instructor) {
        instructors.remove(instructor);
        instructors.add(instructor);
        return instructor;
    }
}

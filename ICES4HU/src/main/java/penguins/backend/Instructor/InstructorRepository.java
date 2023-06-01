package penguins.backend.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import penguins.backend.Department.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@Repository
public /*class*/ interface InstructorRepository extends JpaRepository<Instructor, Long> {
    /*private final List<Instructor> instructors = new ArrayList<>();

//    public Optional<Instructor> findByUsername(String username) {
//        for (Instructor instructor : instructors) {
//            if (instructor.getUsername().equals(username)) {
//                return Optional.of(instructor);
//            }
//        }
//
//        return Optional.empty();
//    }

    public Optional<Instructor> findByUserId(long userId) {
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

    public List<Instructor> findAll() {
        return instructors;
}
    }*/

    Optional<Instructor> findByUserId(long userId);

    Optional<Instructor> findByUsername(String username);

    List<Instructor> findByDepartment(Department department);
}

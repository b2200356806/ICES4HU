package penguins.backend.Department;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentRepository {
    private final List<Department> departments = new ArrayList<>();


    public Optional<Department> findByName(String name) {
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }


    public Department save(Department department) {
        departments.remove(department);
        departments.add(department);
        return department;
    }
}

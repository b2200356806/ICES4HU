package penguins.backend.Department;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentRepository {
    private final List<Department> departments = new ArrayList<>();

    public Department save(Department department) {
        departments.remove(department);
        departments.add(department);
        return department;
    }
}

package penguins.backend.DepartmentManager;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentManagerRepository {
    private final List<DepartmentManager> departmentManagers = new ArrayList<>();

    public Optional<DepartmentManager> findByUserId(long userId) {
        for (DepartmentManager departmentManager : departmentManagers) {
            if (departmentManager.getUserId() == userId) {
                return Optional.of(departmentManager);
            }
        }

        return Optional.empty();
    }

    public DepartmentManager save(DepartmentManager departmentManager) {
        departmentManagers.remove(departmentManager);
        departmentManagers.add(departmentManager);
        return departmentManager;
    }

}

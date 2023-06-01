package penguins.backend.DepartmentManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, Long> {
    /*private final List<DepartmentManager> departmentManagers = new ArrayList<>();

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
    }*/

    Optional<DepartmentManager> findByUserId(long userId);

}

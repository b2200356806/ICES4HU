package penguins.backend.DepartmentManager;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Instructor.Instructor;
import penguins.backend.User.UserType;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentManager extends Instructor {
    public DepartmentManager() {
        setUserType(UserType.DEPARTMENT_MANAGER);
    }
}

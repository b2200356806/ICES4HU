package penguins.backend.DepartmentManager;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Instructor.InstructorDto;
import penguins.backend.User.UserType;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentManagerDto extends InstructorDto {
    public DepartmentManagerDto() {
        setUserType(UserType.DEPARTMENT_MANAGER);
    }
}

package penguins.backend.Admin;

import penguins.backend.User.UserDto;
import penguins.backend.User.UserType;

public class AdminDto extends UserDto {
    public AdminDto() {
        setUserType(UserType.ADMIN);
    }
}

package penguins.backend.Admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends User {
    public Admin() {
        setUserType(UserType.ADMIN);
    }
}

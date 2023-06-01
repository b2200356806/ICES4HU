package penguins.backend.Admin;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Admin extends User {
    public Admin() {
        setUserType(UserType.ADMIN);
    }
}

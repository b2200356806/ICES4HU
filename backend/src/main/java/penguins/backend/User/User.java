package penguins.backend.User;

import lombok.Data;

@Data
public abstract class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}

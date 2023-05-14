package penguins.backend.User;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}

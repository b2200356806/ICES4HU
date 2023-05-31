package penguins.backend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import penguins.backend.User.UserType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private UserType userType;
}

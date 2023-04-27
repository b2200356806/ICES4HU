package penguins.backend.User;

import lombok.Data;

@Data
public abstract class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
}

package penguins.backend.domain.model;

import lombok.Data;

@Data
public abstract class User {

    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}

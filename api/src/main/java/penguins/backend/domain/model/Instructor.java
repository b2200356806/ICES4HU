package penguins.backend.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Instructor extends User {
    private String department;
}

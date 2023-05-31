package penguins.backend.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    DEPARTMENT_MANAGER_READ("department_manager:read"),
    DEPARTMENT_MANAGER_UPDATE("department_manager:update"),
    DEPARTMENT_MANAGER_CREATE("department_manager:create"),
    DEPARTMENT_MANAGER_DELETE("department_manager:delete"),

    INSTRUCTOR_READ("instructor:read"),
    INSTRUCTOR_UPDATE("instructor:update"),
    INSTRUCTOR_CREATE("instructor:create"),
    INSTRUCTOR_DELETE("instructor:delete"),

    ;

    @Getter
    private final String permission;


}

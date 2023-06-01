package penguins.backend.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static penguins.backend.User.Permission.*;

@RequiredArgsConstructor
public enum UserType {
    ADMIN(
            Set.of(
                ADMIN_READ,
                ADMIN_UPDATE,
                ADMIN_DELETE,
                ADMIN_CREATE
        )
    ),
    DEPARTMENT_MANAGER(
            Set.of(
                    DEPARTMENT_MANAGER_READ,
                    DEPARTMENT_MANAGER_UPDATE,
                    DEPARTMENT_MANAGER_CREATE,
                    DEPARTMENT_MANAGER_DELETE
            )
    ),
    INSTRUCTOR(
            Set.of(
                    INSTRUCTOR_READ,
                    INSTRUCTOR_UPDATE,
                    INSTRUCTOR_CREATE,
                    INSTRUCTOR_DELETE
            )
    ),
    STUDENT(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

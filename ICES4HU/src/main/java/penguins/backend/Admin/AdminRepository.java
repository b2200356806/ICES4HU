package penguins.backend.Admin;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminRepository {
    private final List<Admin> admins = new ArrayList<>();

    public Optional<Admin> findById(long userId) {
        for (Admin admin : admins) {
            if (admin.getUserId() == userId) {
                return Optional.of(admin);
            }
        }
        return Optional.empty();
    }

    public final List<Admin> findAll() {
        return new ArrayList<>(admins);
    }

    public Admin save(Admin admin) {
        admins.remove(admin);
        admins.add(admin);
        return admin;
    }

}

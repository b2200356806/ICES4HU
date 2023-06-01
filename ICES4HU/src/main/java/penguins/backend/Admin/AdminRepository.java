package penguins.backend.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*public class AdminRepository {
    private final List<Admin> admins = new ArrayList<>();

    public Optional<Admin> findByUserId(long userId) {
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

}*/
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUserId(long userId);


}

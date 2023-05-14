package penguins.backend.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public Optional<User> findByUserId(long userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public User save(User user) {
        users.remove(user);
        users.add(user);
        return user;
    }

}

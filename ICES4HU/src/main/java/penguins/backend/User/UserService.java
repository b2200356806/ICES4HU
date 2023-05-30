package penguins.backend.User;

import org.springframework.stereotype.Service;
import penguins.backend.User.UserException.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Updates the attribute of the user
     * @param user user object
     * @param userUpdateRequest updated user attributes
     * @return updated user
     */
    public User updateUser(User user, UserUpdateRequest userUpdateRequest) {
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setUsername(userUpdateRequest.getUsername());
        user.setPassword(userUpdateRequest.getPassword());
        userRepository.save(user);
        return user;
    }


    /**
     * Returns the user type
     * @param userId user id
     * @return userType of the user
     * @throws UserNotFoundException if there is no user with the given id
     */
    public UserType getUserType(long userId) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found. User id: " + userId));
        return user.getUserType();
    }


    /**
     * Saves the user
     * @param user the user object
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }


    /**
     * Finds if there is already a user with the given username.
     * @param username username to check
     * @return true if there is a user with the given username, false otherwise
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}

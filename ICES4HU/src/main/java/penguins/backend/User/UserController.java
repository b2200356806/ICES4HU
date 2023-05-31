package penguins.backend.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import penguins.backend.User.UserException.UserNotFoundException;

@RestController
@RequestMapping("/api/user/{userId}")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user-type")
    public ResponseEntity<UserType> getUserType(@PathVariable Integer userId) {
        try {
            UserType userType = userService.getUserType(userId);
            return ResponseEntity.ok(userType);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

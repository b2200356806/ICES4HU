package penguins.backend.config;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import penguins.backend.User.UserService;
import penguins.backend.User.UserType;

@Service
public class JwtTokenUtil {
    public String SECRET = "68566D597133743677397A24432646294A404E635266556A586E5A7234753778";

    private final UserService userService;

    public JwtTokenUtil(UserService userService) {
        this.userService = userService;
    }

    public String getUsernameByToken (HttpHeaders headers){

        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer", "");
        String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody().getSubject();

        return username ;
    }

    public String getUserIdByToken (HttpHeaders headers){

        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer", "");
        String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody().getSubject();

        Long userId = userService.getUserIdByUsername(username) ;

        return userId.toString();
    }

    public String getUserTypeByToken (HttpHeaders headers){

        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer", "");
        String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody().getSubject();

        String usertype  = String.valueOf(userService.getUserTypeByUsername(username));

        return usertype;
    }



}

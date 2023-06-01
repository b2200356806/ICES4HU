package penguins.backend.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import penguins.backend.config.JwtTokenUtil;

@RestController
@RequestMapping("/api/Authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtTokenUtil jwttokenutil;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse temp =  service.authenticate(request);
        return ResponseEntity.ok(temp);
    }
    @GetMapping("/getUsername")
    public ResponseEntity<String> getUsername(
            @RequestHeader HttpHeaders headers ) {

        String userName = jwttokenutil.getUsernameByToken(headers);

        return ResponseEntity.ok(userName);

    }
    @GetMapping("/getUserId")
    public ResponseEntity<String> getUserId(
            @RequestHeader HttpHeaders headers ) {

        String userId = jwttokenutil.getUserIdByToken(headers);

        return ResponseEntity.ok(userId);

    }
    @GetMapping("/getUserType")
    public ResponseEntity<String> getUserType(
            @RequestHeader HttpHeaders headers ) {

        String userType = jwttokenutil.getUserTypeByToken(headers);

        return ResponseEntity.ok(userType);

    }

}

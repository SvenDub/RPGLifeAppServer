package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.LoginRequest;
import questionablequality.rpglifeserver.entity.LoginResponse;
import questionablequality.rpglifeserver.entity.User;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public LoginController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> index(@RequestBody LoginRequest request) {
        User user =  userRepository.findByUsername(request.getUsername());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            LoginEntry loginEntry = new LoginEntry();
            loginEntry.setUser(user);
            loginEntry.setAccessToken(UUID.randomUUID().toString());
            LoginEntry saved = loginRepository.save(loginEntry);
            return ResponseEntity.ok(new LoginResponse(saved.getAccessToken()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

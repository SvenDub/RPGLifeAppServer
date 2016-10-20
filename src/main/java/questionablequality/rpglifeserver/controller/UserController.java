package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.User;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getSelf(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(loginEntry.getUser());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

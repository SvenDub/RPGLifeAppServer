package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/me")
    public ResponseEntity<User> getSelf(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(loginEntry.getUser());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> getById(@RequestHeader(name = "Authorization") String accessToken, @PathVariable("id") int id) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(userRepository.findOne(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

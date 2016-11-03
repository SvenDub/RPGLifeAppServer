package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.entity.User;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.QuestRepository;
import questionablequality.rpglifeserver.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    private final LoginRepository loginRepository;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestController(LoginRepository loginRepository, QuestRepository questRepository, UserRepository userRepository) {
        this.loginRepository = loginRepository;
        this.questRepository = questRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping
    public ResponseEntity<List<Quest>> getAll(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(questRepository.findByUser(loginEntry.getUser()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Quest> getById(@RequestHeader(name = "Authorization") String accessToken, @PathVariable("id") int id) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(questRepository.findOne(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Quest> addQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            quest.setUser(loginEntry.getUser());
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Quest> saveQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest, @PathVariable int id) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            User user = loginEntry.getUser();

            Quest current = questRepository.findOne(id);

            // Quest exists, is to be completed and has not been completed before
            if (current != null && quest.getProgress() >= quest.getGoal() && current.getProgress() < current.getGoal()) {
                user.setXp(user.getXp() + quest.getRewardxp());
                userRepository.save(user);
            }

            quest.setUser(user);
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

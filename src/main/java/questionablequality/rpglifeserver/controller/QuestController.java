package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.QuestRepository;

import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    private final LoginRepository loginRepository;
    private final QuestRepository questRepository;

    @Autowired
    public QuestController(LoginRepository loginRepository, QuestRepository questRepository) {
        this.loginRepository = loginRepository;
        this.questRepository = questRepository;
    }

    @GetMapping
    public ResponseEntity<List<Quest>> getAll(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(questRepository.findByUser(loginEntry.getUser()));
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

    @PostMapping("/${id}")
    public ResponseEntity<Quest> saveQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest, @RequestParam int id) {
        System.out.println("Saving " + quest.getId());
        System.out.println("Progress " + quest.getProgress());
        System.out.println("Name " + quest.getName());
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            quest.setUser(loginEntry.getUser());
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

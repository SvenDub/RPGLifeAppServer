package questionablequality.rpglifeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("")
    public ResponseEntity<List<Quest>> getAll(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(questRepository.findByUser(loginEntry.getUser()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionablequality.rpglifeserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.QuestRepository;

/**
 *
 * @author Tobi
 */

@RestController
@RequestMapping("/guildquest")
public class GuildQuestController {
    private final LoginRepository loginRepository;
    private final QuestRepository questRepository;

    @Autowired
    public GuildQuestController(LoginRepository loginRepository, QuestRepository questRepository) {
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

    @PostMapping("/{id}")
    public ResponseEntity<Quest> saveQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest, @PathVariable int id) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            quest.setUser(loginEntry.getUser());
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

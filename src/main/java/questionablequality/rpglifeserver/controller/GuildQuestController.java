/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @RequestMapping
    public ResponseEntity<List<Quest>> getAll(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(questRepository.findByGuild(loginEntry.getUser().getGuild()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Quest> addQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            quest.setGuild(loginEntry.getUser().getGuild());
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Quest> saveQuest(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Quest quest, @PathVariable int id) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            quest.setGuild(loginEntry.getUser().getGuild());
            return ResponseEntity.ok(questRepository.save(quest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

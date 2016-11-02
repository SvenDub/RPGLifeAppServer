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
import questionablequality.rpglifeserver.entity.Guild;
import questionablequality.rpglifeserver.entity.LoginEntry;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.repository.GuildRepository;
import questionablequality.rpglifeserver.repository.LoginRepository;
import questionablequality.rpglifeserver.repository.QuestRepository;

/**
 *
 * @author Tobi
 */

@RestController
@RequestMapping("/guild")
public class GuildController {
    private final LoginRepository loginRepository;
    private final GuildRepository guildRepository;

    @Autowired
    public GuildController(LoginRepository loginRepository, GuildRepository guildRepository) {
        this.loginRepository = loginRepository;
        this.guildRepository = guildRepository;
    }

    @GetMapping
    public ResponseEntity<List<Guild>> getAll(@RequestHeader(name = "Authorization") String accessToken) {
        LoginEntry loginEntry = loginRepository.findByAccessToken(accessToken);
        if (loginEntry != null) {
            return ResponseEntity.ok(guildRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

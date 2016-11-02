/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionablequality.rpglifeserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Tobi
 */
@Entity
public class Guild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    @OneToOne(mappedBy = "guild")
    private int guildLeader;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guild")
    private List<Integer> members;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guild")
    private List<Quest> quests;
    
    public Guild(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuildLeader() {
        return guildLeader;
    }

    public void setGuildLeader(int guildLeader) {
        this.guildLeader = guildLeader;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
    
    
}

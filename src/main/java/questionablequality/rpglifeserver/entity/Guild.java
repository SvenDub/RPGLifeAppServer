/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionablequality.rpglifeserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    
    @OneToOne
    private User guildLeader;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guild")
    private List<User> members;

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

    public User getGuildLeader() {
        return guildLeader;
    }

    public void setGuildLeader(User guildLeader) {
        this.guildLeader = guildLeader;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
    
    
}

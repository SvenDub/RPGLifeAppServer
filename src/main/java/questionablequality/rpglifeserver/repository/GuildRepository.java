/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionablequality.rpglifeserver.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import questionablequality.rpglifeserver.entity.Guild;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.entity.User;

/**
 *
 * @author Tobi
 */
public interface GuildRepository extends CrudRepository<Guild, Integer> {

    List<Guild> findByUser(User user);
    
    Guild findById(int id);
    
}

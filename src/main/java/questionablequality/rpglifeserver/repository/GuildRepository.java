/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionablequality.rpglifeserver.repository;

import org.springframework.data.repository.CrudRepository;
import questionablequality.rpglifeserver.entity.Guild;

import java.util.List;

/**
 *
 * @author Tobi
 */
public interface GuildRepository extends CrudRepository<Guild, Integer> {

    List<Guild> findAll();
    
    Guild findById(int id);
    
}

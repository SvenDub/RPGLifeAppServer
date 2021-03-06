package questionablequality.rpglifeserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import questionablequality.rpglifeserver.entity.Guild;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.entity.User;

import java.util.List;

@Repository
public interface QuestRepository extends CrudRepository<Quest, Integer> {

    List<Quest> findByUser(User user);

    List<Quest> findByGuild(Guild guild);
}

package questionablequality.rpglifeserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import questionablequality.rpglifeserver.entity.LoginEntry;

@Repository
public interface LoginRepository extends CrudRepository<LoginEntry, Integer> {

    LoginEntry findByAccessToken(String accessToken);
}

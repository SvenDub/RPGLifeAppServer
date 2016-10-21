package questionablequality.rpglifeserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import questionablequality.rpglifeserver.entity.Quest;
import questionablequality.rpglifeserver.entity.User;
import questionablequality.rpglifeserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RpgLifeAppServerApplication {

    private UserRepository userRepository;

    @Autowired
    public RpgLifeAppServerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;

        User user = userRepository.findByUsername("sven.dubbeld1@gmail.com");

        if (user == null) {

            // Fill dummy database
            User s = new User();
            s.setUsername("sven.dubbeld1@gmail.com");
            s.setPassword("mypassword");

            List<Quest> quests = new ArrayList<>();
            quests.add(new Quest("test", "description", 10, 5, 100, s));
            quests.add(new Quest("test2", "description", 7, 0, 110, s));
            s.setQuests(quests);

            userRepository.save(s);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RpgLifeAppServerApplication.class, args);
    }
}

package questionablequality.rpglifeserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import questionablequality.rpglifeserver.entity.User;
import questionablequality.rpglifeserver.repository.UserRepository;

@SpringBootApplication
public class RpgLifeAppServerApplication {

    private UserRepository userRepository;

    @Autowired
    public RpgLifeAppServerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;

        // Fill dummy database
        User s = new User();
        s.setUsername("sven.dubbeld1@gmail.com");
        s.setPassword("mypassword");
        userRepository.save(s);
    }

    public static void main(String[] args) {
        SpringApplication.run(RpgLifeAppServerApplication.class, args);
    }
}

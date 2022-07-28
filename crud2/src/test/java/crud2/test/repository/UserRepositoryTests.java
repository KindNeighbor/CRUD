package crud2.test.repository;

import crud2.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();

        user.setAccount("testId1");
        user.setPassword("test123");
        user.setName("choi");

        User test = userRepository.save(user);
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            System.out.println(selectUser.getName());
            System.out.println(selectUser.getAccount());
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            selectUser.setName("kim");
            selectUser.setAccount("kim123");
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete() {
        Optional<User> user = userRepository.findById(4L);
        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
    }

}
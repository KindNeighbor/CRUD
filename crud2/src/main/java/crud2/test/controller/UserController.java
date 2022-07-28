package crud2.test.controller;

import crud2.test.entity.User;
import crud2.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/index")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return user.getName() + "님의 아이디가 생성되었습니다.";
    }

    @GetMapping("/user")
    public User findUser(@RequestParam Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @PutMapping("/user")
    public String changeInfo(@RequestParam Long id, @RequestBody User user) {
        Optional<User> updateUser = userRepository.findById(id);
        updateUser.ifPresent(selectUser -> {
            selectUser.setName(user.getName());
            selectUser.setAccount(user.getAccount());
            selectUser.setPassword(user.getPassword());
            userRepository.save(selectUser);
        });

        return updateUser.get().getName() + "로 업데이트 되었습니다.";
    }

    @DeleteMapping("/user")
    public String deleteUser(@RequestParam Long id) {
        Optional<User> deleteUser = userRepository.findById(id);
        deleteUser.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        return deleteUser.get().getId() + "가 삭제되었습니다.";
    }
}

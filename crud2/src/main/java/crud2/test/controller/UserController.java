package crud2.test.controller;

import crud2.test.entity.User;
import crud2.test.repository.UserRepository;
import crud2.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/index")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody User user) {
       return userService.join(user);
    }

    @GetMapping("/user")
    public String findUser(@RequestParam Long id) {
        return userService.findUser(id);
    }

    @PutMapping("/user")
    public String updateMember(@RequestParam Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user")
    public String deleteMember(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}

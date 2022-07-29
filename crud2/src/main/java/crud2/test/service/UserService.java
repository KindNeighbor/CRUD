package crud2.test.service;

import crud2.test.entity.User;
import crud2.test.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@Getter @Setter
public class UserService {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public String join(User user) {
        if (userRepository.existsByName(user.getName()) == true ) {
            return "이미 동일한 이름이 있습니다.";
        } else {
            User newUser = userRepository.save(user);
            return user.getName() + "님의 아이디가 생성되었습니다.";
        }
    }

    public String findUser(Long id) {
        if (userRepository.existsById(id) == false ) {
            return "검색한 id를 가진 user가 없습니다.";
        } else {
            Optional<User> user = userRepository.findById(id);
            return user.get() + " 님이 조회 되었습니다.";
        }
    }

    public String updateUser(Long id, User user) {
        if (userRepository.existsById(id) == false) {
            return "업데이트 하고자 하는 id가 없습니다.";

        } else {
            Optional<User> updateUser = userRepository.findById(id);

//            updateUser.ifPresent(selectUser -> { <-- 값이 있을 때만 반응.
//                selectUser.setName(user.getName());
//                selectUser.setAccount(user.getAccount());
//                selectUser.setPassword(user.getPassword());
//                userRepository.save(selectUser);
//            });

            updateUser.get().setName(user.getName());
            updateUser.get().setAccount(user.getAccount());
            updateUser.get().setAccount(user.getPassword());
            userRepository.save(updateUser.get());
            return updateUser.get().getName() + "로 업데이트 되었습니다.";
        }
    }

    public String deleteUser(Long id) {
        if (userRepository.existsById(id) == false) {
            return "삭제 하고자 하는 id가 없습니다.";

        } else {

            Optional<User> deleteUser = userRepository.findById(id);

//            deleteUser.ifPresent(selectUser -> {
//                userRepository.delete(selectUser);
//            });

            userRepository.delete(deleteUser.get());

            return "id " + deleteUser.get().getId() + " 번이 삭제되었습니다.";
        }
    }

    @Transactional
    public boolean existsByName(String name){

        return userRepository.existsByName(name);
    }

    @Transactional
    public boolean existsById(Long id){

        return userRepository.existsById(id);
    }
}

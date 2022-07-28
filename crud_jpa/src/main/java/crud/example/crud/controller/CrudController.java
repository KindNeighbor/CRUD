package crud.example.crud.controller;

import crud.example.crud.entity.CrudEntity;
import crud.example.crud.repository.CrudEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class CrudController {

    private final CrudEntityRepository crudEntityRepository;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("search")
    public String searchAllMember() {
        return crudEntityRepository.findAll().toString();
    }

    @GetMapping("searchParam")
    public String searchParamMember(@RequestParam(value = "age") int age) {
        List<CrudEntity> resultList = em.createQuery("select name from sample_member name where age = :age", CrudEntity.class)
                .setParameter("age", age)
                .getResultList();
        return resultList.toString();
    }

    @GetMapping("searchParamRepo")
    public String searchParamRepoMember(@RequestParam(value = "name") String name) {
        return crudEntityRepository.searchParamRepo(name).toString();
    }

    @GetMapping("insert")
    public String insertMember(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        if (crudEntityRepository.findById(name).isPresent()) {
            return "이미 동일한 이름이 있습니다.";
        } else {
            CrudEntity entity = CrudEntity.builder().name(name).age(age).build();
            crudEntityRepository.save(entity);
            return "이름 : " + name + " 나이 : " + age + "으로 추가 되었습니다.";
        }
    }

    @GetMapping("update")
    public String updateMember(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 " + name + "이 존재하지 않습니다.";
        } else {
            crudEntityRepository.save(CrudEntity.builder().name(name).age(age).build());
            return name + "의 나이를 " + age + "로 변경 완료 되었습니다.";
        }
    }

    @GetMapping("delete")
    public String deleteMember(@RequestParam(value = "name") String name) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 " + name + "이 존재하지 않습니다.";
        } else {
            crudEntityRepository.delete(CrudEntity.builder().name(name).build());
            return name + " 삭제 완료 되었습니다.";
        }
    }
}

package cn.edu.hqu.javaee.springboot_vue_05.repository;

import cn.edu.hqu.javaee.springboot_vue_05.entity.Stu_info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

@SpringBootTest
class Stu_infoRepositoryTest {
    @Autowired
    private Stu_infoRepository stu_infoRepository;

    @Test
    void findAll(){
        System.out.println(stu_infoRepository.findAll());
    }
    @Test
    void save(){
        Stu_info s = new Stu_info();
        s.setUser_id("0000");
        s.setUser_name("test");
        s.setName("test");
        s.setAcademy("计算机科学与技术学院");
        Stu_info s1 = stu_infoRepository.save(s);
        System.out.println(s1);

    }
    @Test
    void update(){
        Stu_info s = new Stu_info();
        s.setUser_id("1001");
        s.setName("张三");
        s.setAcademy("计算机科学与技术学院");
        s.setUser_name("zhng");
        Stu_info s1 = stu_infoRepository.save(s);
        System.out.println(s1);
    }
    @Test
    void search(){
        Stu_info s = new Stu_info();
        s.setStatus(true);
        s.setUser_id("1002");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("user_id",
                ExampleMatcher.GenericPropertyMatcher.of(
                        ExampleMatcher.StringMatcher.CONTAINING,false));
        exampleMatcher = exampleMatcher.withMatcher("status",
                ExampleMatcher.GenericPropertyMatcher.of(
                        ExampleMatcher.StringMatcher.CONTAINING,false));
        Example<Stu_info> example1 = Example.of(s,exampleMatcher);
        System.out.println(stu_infoRepository.findAll(example1));
    }
}
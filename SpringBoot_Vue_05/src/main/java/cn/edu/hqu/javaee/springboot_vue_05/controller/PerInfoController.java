package cn.edu.hqu.javaee.springboot_vue_05.controller;

import cn.edu.hqu.javaee.springboot_vue_05.entity.History;
import cn.edu.hqu.javaee.springboot_vue_05.entity.Stu_info;
import cn.edu.hqu.javaee.springboot_vue_05.entity.login;
import cn.edu.hqu.javaee.springboot_vue_05.repository.History_repository;
import cn.edu.hqu.javaee.springboot_vue_05.repository.Login_repository;
import cn.edu.hqu.javaee.springboot_vue_05.repository.Stu_infoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/PerInfo")
public class PerInfoController {
    @Autowired
    private Stu_infoRepository stu_infoRepository;
    @Autowired
    private History_repository history_repository;
    @Autowired
    private Login_repository login_repository;

    @GetMapping("/getPersonInfo")
    public Optional<Stu_info> getInfo(@RequestParam(required = false) String user_id){
//        System.out.println(user_id);
        return stu_infoRepository.findById(user_id);
    }

    @PostMapping("/updatePersonalInfo")
    public String updatePersonalInfo(@RequestBody login l){
        l.setRole("普通用户");
        Optional<Stu_info> s =  stu_infoRepository.findById(l.getUser_id());
        s.get().setUser_name(l.getUser_name());
        System.out.println(s.get());
        Stu_info s1 = stu_infoRepository.save(s.get());
        login l1 = login_repository.save(l);
        if (s1 != null && l1 != null) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/getHistory")
    public List<History> getHistory(@RequestParam(required = false) String user_id){
        //创建查询条件的数据对象
        History h = new History();
        h.setUser_id(user_id);
        //创建匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching() //创建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串的匹配方式：模糊查询
                .withIgnoreCase(true)
                .withIgnorePaths("id","user_name","name","academy","health_status","work_status","temperature","go_out","remark","fill_time");
        Example<History> example1 = Example.of(h,exampleMatcher);
        System.out.println(example1);
        return history_repository.findAll(example1);
    }

    @PostMapping("/submitPersonInfo")
    public String update(@RequestBody Stu_info s){

        History h = new History();
        h.setUser_id(s.getUser_id());
        h.setUser_name(s.getUser_name());
        h.setName(s.getName());
        h.setAcademy(s.getAcademy());
        h.setHealth_status(s.isHealth_status());
        h.setWork_status(s.getWork_status());
        h.setTemperature(s.getTemperature());
        h.setGo_out(s.isGo_out());
        h.setFill_time(s.getFill_time());
        h.setRemark(s.getRemark());

        Stu_info s1 = stu_infoRepository.save(s);
        History h2 = history_repository.save(h);
        if (s1 != null && h2 != null) {
            return "success";
        } else {
            return "error";
        }
    }

}

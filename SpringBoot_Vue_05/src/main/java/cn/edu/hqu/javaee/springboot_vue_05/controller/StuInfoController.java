package cn.edu.hqu.javaee.springboot_vue_05.controller;

import cn.edu.hqu.javaee.springboot_vue_05.entity.Stu_info;
import cn.edu.hqu.javaee.springboot_vue_05.repository.Stu_infoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/StuInfo")
public class StuInfoController {
    @Autowired
    private Stu_infoRepository stu_infoRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Stu_info> findAll(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return stu_infoRepository.findAll(pageable);
    }

    @PostMapping("/addStu")
    public String addStu(@RequestBody Stu_info s){
        Stu_info s1 = stu_infoRepository.save(s);
        if(s1 != null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/updateStu")
    public  String updateStu(@RequestBody Stu_info s) {
        Stu_info s1 = stu_infoRepository.save(s);
        if (s1 != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/serachStuByIdOrStatus")
    public  List<Stu_info>  searchStu(@RequestParam(required = false) String user_id,
                                        @RequestParam(required = false) boolean status) {
        //创建查询条件的数据对象
        Stu_info s = new Stu_info();
        s.setHealth_status(true);
        s.setGo_out(false);
        s.set_register(true);
        if(status == true || status == false){
            s.setStatus(status);
        }
        if(user_id != ""){
            s.setUser_id(user_id);
        }
        //创建匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching() //创建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串的匹配方式：模糊查询
                .withIgnoreCase(true)
                .withIgnorePaths("health_status","go_out","is_register","user_name","name","academy"
                ,"work_status","temperature","fill_time","no_register_num","last_register_time","remark");

        Example<Stu_info> example1 = Example.of(s,exampleMatcher);
        System.out.println(example1);
        return stu_infoRepository.findAll(example1);
    }
}

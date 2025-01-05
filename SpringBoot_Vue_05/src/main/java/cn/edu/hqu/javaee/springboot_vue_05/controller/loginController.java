package cn.edu.hqu.javaee.springboot_vue_05.controller;

import cn.edu.hqu.javaee.springboot_vue_05.entity.Stu_info;
import cn.edu.hqu.javaee.springboot_vue_05.entity.login;
import cn.edu.hqu.javaee.springboot_vue_05.repository.Login_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController

public class loginController {
    @Autowired
    private Login_repository login_repository;
    @RequestMapping("/login")
    String[] login(@RequestParam(required = false) String user_id,
                 @RequestParam(required = false) String user_password){
       Optional<login> l1 = login_repository.findById(user_id);
       String[] s = new String[3];
        if (!l1.isEmpty() && Objects.equals(l1.get().getUser_password(), user_password)) {
            s[0] = l1.get().getUser_name();
            s[1] = l1.get().getRole();
            s[2] = l1.get().getUser_id();
            return s;
//            return l1.get();
        } else {
//            return "error";
            return s;
        }
    }

}

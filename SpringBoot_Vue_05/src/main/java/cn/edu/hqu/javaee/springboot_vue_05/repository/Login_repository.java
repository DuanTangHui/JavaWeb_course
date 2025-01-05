package cn.edu.hqu.javaee.springboot_vue_05.repository;


import cn.edu.hqu.javaee.springboot_vue_05.entity.login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Login_repository extends JpaRepository<login,String> {
}

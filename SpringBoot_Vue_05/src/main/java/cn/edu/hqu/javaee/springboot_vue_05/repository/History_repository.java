package cn.edu.hqu.javaee.springboot_vue_05.repository;

import cn.edu.hqu.javaee.springboot_vue_05.entity.History;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface History_repository  extends JpaRepository <History,Integer> {
//    Optional<History> findByUserId(String user_id);
}

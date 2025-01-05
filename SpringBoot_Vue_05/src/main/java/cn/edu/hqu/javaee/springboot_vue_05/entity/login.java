package cn.edu.hqu.javaee.springboot_vue_05.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class login {
    @Id
    private String user_id;
    private String user_name;
    private String user_password;
    private String role;
}
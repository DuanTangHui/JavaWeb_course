package cn.edu.hqu.javaee.springboot_vue_05.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class History {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user_id;
    private String user_name;
    private String name;
    private String academy;
    private boolean health_status ;
    private String work_status ;
    private double temperature ;
    private String remark ;
    private boolean go_out;
    private String fill_time;

}

package cn.edu.hqu.javaee.springboot_vue_05.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Stu_info {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String user_id; //用户ID
    private String user_name; //用户名
    private String name; //姓名
    private String academy;  //学院
    private boolean status; //是否启用
    private boolean health_status; //健康状况
    private String work_status; //工作状态
    private boolean go_out;  //是否外出
    private double temperature;  //体温
    private String fill_time; //填报时间
    private String last_register_time; //最后登记时间
    private int no_register_num; //未登记次数
    private String remark;      //备注
    private boolean is_register;  //是否登记

}

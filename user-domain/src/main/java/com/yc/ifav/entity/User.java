package com.yc.ifav.entity;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data      //  lombok 注解，节省getter, setter
//总结：使用@Table注解时，若同一实例下存在多个数据库，且可能存在相同表名时，
// 需添加”schema“属性来声明数据库，是否Hibernate可能找到其它库的表去。
//例：@Table(name="TS_USER",schema="CPM")
@Table(name = "users")
public class User {
    @Id  // JPA注解,指定此属性为表中的主键
    //@GeneratedValue 用于标注主键的生成策略，通过 strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：
    // SqlServer 对应 identity，MySQL 对应 auto increment。
    //  在 javax.persistence.GenerationType 中定义了以下几种可供选择的策略：
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //mybatis获取新插入的id，必须要写的
    private Integer uId;   //注意: 对应的数据表中的字段名叫 book_id
    private String uName;
    private String uEmail;
    private String uPwd;
    private String uAvator;

}
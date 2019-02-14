package com.imamachi.simplepolling.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class Department {

    // 部署ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer departmentId;

    // 部署名
    @Column(nullable = false, unique = true)
    private String departmentName;

    public Department(String departmentName){
        this.departmentName = departmentName;
    }
}

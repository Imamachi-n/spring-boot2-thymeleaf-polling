package com.imamachi.simplepolling.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {

    // 従業員ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer employeeId;

    // 従業員ステータス
    @Column(nullable = false, unique = true)
    private Status employeeStatus;

    public static enum Status {
        一般社員, 管理職, パートナー社員
    }

    public Employee(Status employeeStatus){
        this.employeeStatus = employeeStatus;
    }
}

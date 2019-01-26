package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "result_detail")
@Data
public class ResultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer resultDetailId;

    @Column(nullable = false)
    private Integer questionDetailId;

    @Column(nullable = false)
    private String description;
}

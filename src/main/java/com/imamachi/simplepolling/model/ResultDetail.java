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

    // 質問事項
    @Column(nullable = false)
    private String description;

    // 回答
    @Column(nullable = false)
    private String answer;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "resultId", nullable = false)
    private Result result;
}

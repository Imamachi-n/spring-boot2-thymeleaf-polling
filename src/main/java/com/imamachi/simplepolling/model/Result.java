package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "result")
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer resultId;

    // アンケート情報
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;

    // 質問事項の詳細
    // mappedBy属性に相手のEntityクラスで@ManyToOneアノテーションを使用したフィールド名を指定
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "result")
    @OrderBy("resultDetailId ASC")
    private List<ResultDetail> resultDetail;

}

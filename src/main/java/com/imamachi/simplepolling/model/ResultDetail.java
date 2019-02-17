package com.imamachi.simplepolling.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "result_detail")
@Data
@NoArgsConstructor
public class ResultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer resultDetailId;

    // アンケート番号
    @Column(nullable = false)
    private int questionnaireNo;

    // ドキュメントタイプ（単一選択回答、複数選択回答、コメント回答）
    private Question.DocType docType;

    // 従業員ステータス
    @Column(nullable = false)
    private String employeeStatus;

    // 部署名
    @Column(nullable = false)
    private String departmentName;

    // 質問事項
    @Column(nullable = false)
    private String description;

    // 回答
    @Column(nullable = false)
    private int answerId;

    // 回答
    @Column(nullable = false)
    private String answer;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "resultId", nullable = false)
    private Result result;

    public ResultDetail(int questionnaireNo, Question.DocType docType, String description,
                        int answerId, String answer, String employeeStatus, String departmentName){
        this.questionnaireNo = questionnaireNo;
        this.docType = docType;
        this.description = description;
        this.answerId = answerId;
        this.answer = answer;
        this.employeeStatus = employeeStatus;
        this.departmentName = departmentName;
    }
}

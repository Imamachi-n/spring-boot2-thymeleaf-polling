package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "question")
@Data
public class Question {
    public static enum DocType {
        singleQ, multiQ, commentQ
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer questionId;

    // ドキュメントタイプ（単一選択回答、複数選択回答、コメント回答）
    @Column(nullable = false)
    private DocType docType;

    // 必須質問かどうかのチェック
    @Column(nullable = false)
    private boolean requirement;

    // データ作成日
    @Column(nullable = false)
    java.sql.Date createDate;

    // アンケートの質問内容
    @Column(nullable = false)
    private String questionDesc;

    // 質問事項の詳細
    // mappedBy属性に相手のEntityクラスで@ManyToOneアノテーションを使用したフィールド名を指定
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @OrderBy("questionDetailId ASC")
    private List<QuestionDetail> questionDetail;

    // アンケート情報
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;

    @Version
    private Integer version;

    public Question(DocType docType, boolean requirement, String questionDesc,
                    List<QuestionDetail> questionDetail, Questionnaire questionnaire){
        this.docType = docType;
        this.requirement = requirement;
        this.questionDesc = questionDesc;
        this.createDate = java.sql.Date.valueOf(LocalDate.now());
        this.questionDetail = questionDetail;
        this.questionnaire = questionnaire;
    }
}

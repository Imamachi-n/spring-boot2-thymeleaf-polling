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
        singleQ, multiQ, comment
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer questionId;

    @Column(nullable = false)
    private DocType docType;

    @Column(nullable = false)
    private boolean requirement;

    @Column(nullable = false)
    java.sql.Date createDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionDetail")
    @OrderBy("questionDetailId ASC")
    private List<QuestionDetail> questionDetail;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;

    @Version
    private Integer version;

    public Question(DocType docType, boolean requirement){
        this.docType = docType;
        this.requirement = requirement;
        this.createDate = java.sql.Date.valueOf(LocalDate.now());
    }
}

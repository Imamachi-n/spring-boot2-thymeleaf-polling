package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "current_questionnaire")
@Data
public class CurrentQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    // アンケート情報
    @OneToOne
    @JoinColumn(name = "questionnaireId")
    private Questionnaire questionnaire;

    public CurrentQuestionnaire() {}

    public CurrentQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

}

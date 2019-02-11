package com.imamachi.simplepolling.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "respondent")
@Data
@NoArgsConstructor
public class Respondent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Integer respondentId;

    // アンケートに回答した人
    @Column(nullable = false, unique = true)
    private String name;

    // アンケート情報
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;

    public Respondent(String name, Questionnaire questionnaire){
        this.name = name;
        this.questionnaire = questionnaire;
    }
}

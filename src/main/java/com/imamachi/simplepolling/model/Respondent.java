package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "respondent")
@Data
public class Respondent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Integer respondentId;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;
}

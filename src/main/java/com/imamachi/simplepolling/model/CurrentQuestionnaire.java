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
    private Integer questionnaireId;

    private String title;

    public CurrentQuestionnaire() {}

    public CurrentQuestionnaire(String title){
        this.title = title;
    }
}

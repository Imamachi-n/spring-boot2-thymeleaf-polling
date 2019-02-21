package com.imamachi.simplepolling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
アンケートタイトルEntityクラス
 */
@Entity
@Table(name = "questionnaire")
@Data
@NoArgsConstructor
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer questionnaireId;

    // アンケートのタイトル
    @Column(nullable = false, unique = true)
    private String title;

    public Questionnaire(String title){
        this.title = title;
    }

    public Questionnaire(Integer questionnaireId, String title){
        this.questionnaireId = questionnaireId;
        this.title = title;
    }
}

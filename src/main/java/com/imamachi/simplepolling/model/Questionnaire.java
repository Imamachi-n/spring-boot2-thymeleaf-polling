package com.imamachi.simplepolling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/*
アンケートタイトルEntityクラス
 */
@Entity
@Table(name = "questionnaire")
@Data
@AllArgsConstructor
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer questionnaireId;

    // アンケートのタイトル
    @Column(nullable = false)
    private String title;

    public Questionnaire(String title){
        this.title = title;
    }
}

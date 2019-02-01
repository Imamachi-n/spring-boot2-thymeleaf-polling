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
@AllArgsConstructor
@NoArgsConstructor
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

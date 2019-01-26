package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "current_questionnaire")
@Data
public class CurrentQuestionnaire {

    @Id
    @Column(nullable = false)
    private Integer questionnaireId;

    private String comment;
}

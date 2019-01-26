package com.imamachi.simplepolling.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "result")
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Integer resultId;

    @Column(nullable = false,updatable = false)
    private Integer questionnaireId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaireId", nullable = false)
    private Questionnaire questionnaire;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultDetail")
    @OrderBy("resultDetailId ASC")
    private List<ResultDetail> resultDetail;

}

package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultChartData {

    // 質問項目No
    private int answerId;

    // 質問項目内容
    private String answer;

    // 質問No
    private int questionnaireNo;

    // 質問内容
    private String description;

    // 質問形式
    private Question.DocType docType;

    // 回答数
    private Long count;

    public ResultChartData(int answerId, String answer, int questionnaireNo,
                           String description, Question.DocType docType, Long count){
        this.answerId = answerId;
        this.answer = answer;
        this.questionnaireNo = questionnaireNo;
        this.description = description;
        this.docType = docType;
        this.count = count;
    }

    public ResultChartData(int answerId, String answer, int questionnaireNo,
                           String description, Question.DocType docType){
        this.answerId = answerId;
        this.answer = answer;
        this.questionnaireNo = questionnaireNo;
        this.description = description;
        this.docType = docType;
    }
}

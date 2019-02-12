package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChartRootData {

    // 質問No
    private int questionnaireNo;

    // 質問内容
    private String questionnaire;

    // 質問形式
    private Question.DocType docType;

    // 質問結果
    private List<ChartForm> chartData;

    public ChartRootData(int questionnaireNo, String questionnaire, Question.DocType docType){
        this.questionnaireNo = questionnaireNo;
        this.questionnaire = questionnaire;
        this.docType = docType;
        this.chartData = new ArrayList<>();
    }
}

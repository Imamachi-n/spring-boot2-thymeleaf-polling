package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChartRootData {

    // 質問No
    private int questionnaireNo;

    // 質問内容
    private String description;

    // 質問回答

    // 質問形式
    private Question.DocType docType;
}

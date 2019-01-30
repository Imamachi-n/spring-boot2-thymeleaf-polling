package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class QuestionForm {

    // ドキュメントタイプ（単一選択回答、複数選択回答、コメント回答）
    private Question.DocType docType;

    // 必須質問かどうかのチェック
    private boolean requirement;

    // アンケートの質問内容
    private String questionDesc;

    // 説明
    @Valid
    private List<QuestionDetailForm> questionDetails;

    public QuestionForm(Question.DocType docType, boolean requirement){
        this.docType = docType;
        this.requirement = requirement;
    }

    public QuestionForm(){}

}

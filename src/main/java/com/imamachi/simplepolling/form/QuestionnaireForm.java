package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionnaireForm {

    // アンケートのタイトル
    private String title;

    // ドキュメントタイプ（単一選択回答、複数選択回答、コメント回答）
    private Question.DocType docType;

    // 必須質問かどうかのチェック
    private boolean requirement;

    // アンケートの質問内容
    private String questionDesc;
}

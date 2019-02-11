package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class ResultForm {

    // アンケート番号
    @Column(nullable = false)
    private String questionnaireNo;

    // ドキュメントタイプ（単一選択回答、複数選択回答、コメント回答）
    private Question.DocType docType;

    // 質問事項
    @Column(nullable = false)
    private String description;

    // エラーチェック
    private Boolean isError;

    // 質問
    @Valid
    private List<ResultDetailForm> resultDetailForms;
}

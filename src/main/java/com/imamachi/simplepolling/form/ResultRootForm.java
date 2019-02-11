package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class ResultRootForm {

    // アンケートID
//    private String questionnaireId;

    // アンケート情報
    private String questionnaireTitle;

    // 登録ユーザ
    private String username;

    // 質問内容
    @Valid
    List<ResultForm> resultForms;
}

package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Employee;
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

    // 従業員ステータス
    private Employee.Status employeeStatus;

    // 部署名
    private String departmentName;

    // 質問内容
    @Valid
    List<ResultForm> resultForms;
}

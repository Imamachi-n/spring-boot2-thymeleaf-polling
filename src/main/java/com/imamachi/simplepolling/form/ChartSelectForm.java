package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChartSelectForm {

    // アンケート
    private String questionnaireId;

    // 所属
    private String employeeStatus;

    // 部署
    private String departmentName;
}

package com.imamachi.simplepolling.form;

import com.imamachi.simplepolling.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeForm {

    // 社員番号（OA番号）
    @NotBlank(message = "社員番号（OA番号）を入力してください。")
    private String employeeId;

    // 所属
    @NotNull(message = "所属を選択してください。")
    private String employeeStatus;

    // 部署
    @NotNull(message = "部署を選択してください。")
    private String departmentName;

}

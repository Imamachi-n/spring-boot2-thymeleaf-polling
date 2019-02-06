package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDetailForm {

    @NotBlank(message = "質問項目を入力してください。")
    private String description;

    public QuestionDetailForm(String description){
        this.description = description;
    }

}

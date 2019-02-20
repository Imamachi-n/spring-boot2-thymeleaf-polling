package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionRootForm {

    // ID
    private Integer id;

    @NotBlank(message = "タイトルを入力してください。")
    private String title;

    @Valid
    private List<QuestionForm> questions;

    public QuestionRootForm(String title){
        this.title = title;
    }
}

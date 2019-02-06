package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class QuestionRootForm {

    @NotBlank(message = "タイトルを入力してください。")
    private String title;

    @Valid
    private List<QuestionForm> questions;
}

package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class QuestionRootForm {

    @NotNull
    @Min(1)
    private String title;

    @Valid
    private List<QuestionForm> questions;
}

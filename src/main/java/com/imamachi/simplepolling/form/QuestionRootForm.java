package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class QuestionRootForm {

    private String title;

    @Valid
    private List<QuestionForm> questions;
}

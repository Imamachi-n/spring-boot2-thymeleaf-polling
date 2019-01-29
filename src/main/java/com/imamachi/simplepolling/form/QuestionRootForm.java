package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Collection;

@Getter
@Setter
public class QuestionRootForm {

    @Valid
    private Collection<QuestionForm> questions;
}

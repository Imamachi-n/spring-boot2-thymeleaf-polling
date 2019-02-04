package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDetailForm {

    private String description;

    public QuestionDetailForm(String description){
        this.description = description;
    }

}

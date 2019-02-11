package com.imamachi.simplepolling.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChartForm {

    private String key;
    private String value;

    public ChartForm(String key, String value){
        this.key = key;
        this.value = value;
    }
}

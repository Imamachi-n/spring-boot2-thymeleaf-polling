package com.imamachi.simplepolling.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private Integer userId;

    private String username;

    private Boolean isAdmin;

    private Integer version;
}

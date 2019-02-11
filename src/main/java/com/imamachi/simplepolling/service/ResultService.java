package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ResultRootForm;

public interface ResultService {

    boolean existFormError(ResultRootForm resultRootForm);

    boolean registerResult(ResultRootForm resultRootForm);
}

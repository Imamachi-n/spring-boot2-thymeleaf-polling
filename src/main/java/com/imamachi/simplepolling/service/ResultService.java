package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ResultRootForm;
import com.imamachi.simplepolling.model.Questionnaire;

public interface ResultService {

    boolean existFormError(ResultRootForm resultRootForm);

    boolean existRespondent(String employeeId, Questionnaire currentQuestionnaire);

    boolean registerResult(ResultRootForm resultRootForm);
}

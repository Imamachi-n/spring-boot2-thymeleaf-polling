package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.CurrentQuestionnaire;

public interface QuestionnaireService {

    CurrentQuestionnaire getCurrentQuestionnaire();

    QuestionRootForm getQuestionnaireTemplate();

    Boolean registerQuestionnaire(QuestionRootForm questionRootForm);
}

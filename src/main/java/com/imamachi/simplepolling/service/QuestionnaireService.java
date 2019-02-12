package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Questionnaire;

public interface QuestionnaireService {

    CurrentQuestionnaire getCurrentQuestionnaire();

    Questionnaire getQuestionnaireById(Integer questionnaireId);

    QuestionRootForm getQuestionnaireTemplate();

    Boolean registerQuestionnaire(QuestionRootForm questionRootForm);
}

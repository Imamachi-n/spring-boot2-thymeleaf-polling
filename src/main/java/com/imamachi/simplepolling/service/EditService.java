package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Questionnaire;

public interface EditService {

    Questionnaire getSelectedQuestionnaire(int questionnaireId);

    QuestionRootForm getQuestionForm(Questionnaire questionnaire);

    Boolean updateQuestionnaire(QuestionRootForm questionRootForm);
}

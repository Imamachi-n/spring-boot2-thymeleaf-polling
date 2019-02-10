package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Questionnaire;

import java.util.List;

public interface HomeService {

    List<Questionnaire> getQuestionnaires();

    CurrentQuestionnaire getCurrentQuestionnaire();

    Boolean saveQuestionnaire(int questionnaireId);
}

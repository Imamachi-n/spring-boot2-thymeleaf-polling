package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ChartForm;
import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.model.Questionnaire;

import java.util.List;

public interface ChartService {

    boolean getQuestionnaireResult(Questionnaire questionnaire, List<ChartRootData> chartRootData);
}

package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ChartForm;
import com.imamachi.simplepolling.model.Questionnaire;

import java.util.List;

public interface ChartService {

    boolean getQuestionnaireResult(Questionnaire questionnaire, List<List<ChartForm>> chartForms, List<String> qType);
}

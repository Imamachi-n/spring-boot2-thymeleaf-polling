package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ChartForm;
import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.model.Questionnaire;

import java.util.List;

public interface ChartService {

    boolean getQuestionnaireResult(Questionnaire questionnaire, List<List<ChartForm>> chartForms, List<String> qType,
    List<String> chartQName, List<List<ChartForm>> commentForms, List<String> commentQName, List<ChartRootData> chartRootData);
}

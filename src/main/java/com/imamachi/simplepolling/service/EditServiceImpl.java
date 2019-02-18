package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditServiceImpl implements EditService {

    private QuestionnaireRepository questionnaireRepository;
    private HomeService homeService;

    @Autowired
    public EditServiceImpl(QuestionnaireRepository questionnaireRepository,
                           HomeService homeService){
        this.questionnaireRepository = questionnaireRepository;
        this.homeService = homeService;
    }

    // 現在のアンケートを保存
    public Questionnaire getSelectedQuestionnaire(int questionnaireId){
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        if(questionnaire.isPresent()){
            return questionnaire.get();
        }else {
            return homeService.getCurrentQuestionnaire().getQuestionnaire();
        }
    }
}

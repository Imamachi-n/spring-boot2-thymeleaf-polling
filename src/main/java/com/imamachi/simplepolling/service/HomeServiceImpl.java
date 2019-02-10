package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    private QuestionnaireRepository questionnaireRepository;
    private CurrentQuestionnaireRepository currentQuestionnaireRepository;

    @Autowired
    public HomeServiceImpl(QuestionnaireRepository questionnaireRepository,
                           CurrentQuestionnaireRepository currentQuestionnaireRepository){
        this.questionnaireRepository = questionnaireRepository;
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
    }

    // アンケートの一覧の取得
    @Override
    public List<Questionnaire> getQuestionnaires(){
        return questionnaireRepository.findAll();
    }

    // 現在のアンケートを取得
    @Override
    public CurrentQuestionnaire getCurrentQuestionnaire(){
        return currentQuestionnaireRepository.findAll().get(0);
    }

    // 現在のアンケートを保存
    public Boolean saveQuestionnaire(int questionnaireId){
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);

        if(questionnaire.isPresent()) {
            currentQuestionnaireRepository.deleteAll();
            currentQuestionnaireRepository.save(new CurrentQuestionnaire(questionnaire.get()));
            return true;
        }else {
            return false;
        }
    }
}

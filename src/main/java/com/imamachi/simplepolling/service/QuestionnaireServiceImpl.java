package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private CurrentQuestionnaireRepository currentQuestionnaireRepository;

    @Autowired
    public QuestionnaireServiceImpl(CurrentQuestionnaireRepository currentQuestionnaireRepository){
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
    }

    @Override
    public String getCurrentQuestionnaire(){
        List<CurrentQuestionnaire> result = currentQuestionnaireRepository.findAll();
        return result.get(result.size() - 1).getTitle();
    }
}

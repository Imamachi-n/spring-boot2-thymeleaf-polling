package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private CurrentQuestionnaireRepository currentQuestionnaireRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository,
                               CurrentQuestionnaireRepository currentQuestionnaireRepository){
        this.questionRepository = questionRepository;
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
    }

    @Override
    public List<Question> getQuestionnaireInfo(){

        // アンケート情報を検索
        Questionnaire questionnaire = currentQuestionnaireRepository.findAll().get(0).getQuestionnaire();

        // アンケート内容を検索
        return questionRepository.findByQuestionnaireOrderByQuestionId(questionnaire);
    }

    // 選択項目の追加
//    @Override
//    public addItem
}

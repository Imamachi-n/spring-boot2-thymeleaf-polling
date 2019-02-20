package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.QuestionDetail;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditServiceImpl implements EditService {

    private QuestionnaireRepository questionnaireRepository;
    private HomeService homeService;
    private QuestionRepository questionRepository;

    @Autowired
    public EditServiceImpl(QuestionnaireRepository questionnaireRepository,
                           HomeService homeService,
                           QuestionRepository questionRepository){
        this.questionnaireRepository = questionnaireRepository;
        this.homeService = homeService;
        this.questionRepository = questionRepository;
    }

    // 現在のアンケートを保存
    @Override
    public Questionnaire getSelectedQuestionnaire(int questionnaireId){
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        if(questionnaire.isPresent()){
            return questionnaire.get();
        }else {
            return homeService.getCurrentQuestionnaire().getQuestionnaire();
        }
    }

    // 選択したアンケートを取得
    @Override
    public QuestionRootForm getQuestionForm(Questionnaire questionnaire){
        List<Question> questionList = questionRepository.findByQuestionnaireOrderByQuestionId(questionnaire);
        return transformQuestion2QuestionRootForm(questionList, questionnaire);
    }

    // QuestionオブジェクトをQuestionRootFormオブジェクトへ変換
    private QuestionRootForm transformQuestion2QuestionRootForm(List<Question> questionList, Questionnaire questionnaire){

        QuestionRootForm questionRootForm = new QuestionRootForm(questionnaire.getTitle());
        questionRootForm.setId(questionnaire.getQuestionnaireId());

        List<QuestionForm> questionForms = new ArrayList<>();

        for(Question question : questionList){

            List<QuestionDetailForm> questionDetailForms = new ArrayList<>();
            for(QuestionDetail questionDetail : question.getQuestionDetail()){
                QuestionDetailForm questionDetailForm = new QuestionDetailForm(questionDetail.getDescription());
                questionDetailForm.setId(questionDetail.getQuestionDetailId());
                questionDetailForms.add(questionDetailForm);
            }

            if(questionDetailForms.size() == 0) questionDetailForms = null;

            QuestionForm questionForm = new QuestionForm(question.getDocType(), question.isRequirement(),
                    question.getQuestionDesc(), questionDetailForms);
            questionForm.setId(question.getQuestionId());
            questionForms.add(questionForm);
        }

        questionRootForm.setQuestions(questionForms);

        return questionRootForm;
    }
}

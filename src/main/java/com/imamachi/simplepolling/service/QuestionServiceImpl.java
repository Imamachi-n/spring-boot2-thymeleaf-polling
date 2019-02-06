package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
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
    @Override
    public void addQuestionItem(QuestionRootForm questionRootForm, int index){
        List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
        questionDetails.add(new QuestionDetailForm());
        questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
    }

    // 選択項目の削除
    @Override
    public boolean deleteQuestionItem(QuestionRootForm questionRootForm, int index){
        List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
        if(questionDetails.size() > 1){
            questionDetails.remove(questionDetails.size() - 1);
            questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
            return true;
        }
        return false;
    }

    // 質問の追加（単一選択回答）
    @Override
    public void addSingleQuestion(QuestionRootForm questionRootForm){
        List<QuestionForm> questions = questionRootForm.getQuestions();
        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();

        QuestionForm questionForm = new QuestionForm(Question.DocType.singleQ, true);
        questionDetailForms.add(new QuestionDetailForm("とても当てはまる"));
        questionDetailForms.add(new QuestionDetailForm("まあまあ当てはまる"));
        questionDetailForms.add(new QuestionDetailForm("どちらでもない"));
        questionDetailForms.add(new QuestionDetailForm("ほとんど当てはまらない"));
        questionDetailForms.add(new QuestionDetailForm("全く当てはまらない"));
        questionForm.setQuestionDetails(questionDetailForms);
        questions.add(questionForm);

        questionRootForm.setQuestions(questions);
    }

    // 質問の追加（複数選択回答）
    @Override
    public void addMultiQuestion(QuestionRootForm questionRootForm){

        List<QuestionForm> questions = questionRootForm.getQuestions();
        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();

        QuestionForm questionForm = new QuestionForm(Question.DocType.multiQ, true);
        questionDetailForms.add(new QuestionDetailForm());
        questionForm.setQuestionDetails(questionDetailForms);
        questions.add(questionForm);

        questionRootForm.setQuestions(questions);
    }

    // 質問の追加（コメント回答）
    @Override
    public void addCommentQuestion(QuestionRootForm questionRootForm){

        List<QuestionForm> questions = questionRootForm.getQuestions();
        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();

        QuestionForm questionForm = new QuestionForm(Question.DocType.commentQ, false);
        questionDetailForms.add(new QuestionDetailForm("dummy"));
        questionForm.setQuestionDetails(questionDetailForms);
        questions.add(questionForm);

        questionRootForm.setQuestions(questions);
    }

    // 質問の削除
    @Override
    public boolean deleteQuestion(QuestionRootForm questionRootForm, int index){
        List<QuestionForm> questionFroms = questionRootForm.getQuestions();
        if(questionFroms.size() > 1) {
            questionFroms.remove(index);
            questionRootForm.setQuestions(questionFroms);
            return true;
        }
        return false;
    }
}

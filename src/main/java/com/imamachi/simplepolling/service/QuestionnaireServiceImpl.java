package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private CurrentQuestionnaireRepository currentQuestionnaireRepository;

    @Autowired
    public QuestionnaireServiceImpl(CurrentQuestionnaireRepository currentQuestionnaireRepository){
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
    }

    // アンケートのタイトルを取得
    @Override
    public CurrentQuestionnaire getCurrentQuestionnaire(){
        return currentQuestionnaireRepository.findAll().get(0);
    }

    // 新規作成アンケートのテンプレート作成
    @Override
    public QuestionRootForm getQuestionnaireTemplate(){
        // 初期アンケートの用意
        QuestionRootForm questionRootForm = new QuestionRootForm();
        ArrayList<QuestionForm> questionForms = new ArrayList<>();

        // 単一選択型の質問（テンプレート）
        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();
        questionDetailForms.add(new QuestionDetailForm("とても当てはまる"));
        questionDetailForms.add(new QuestionDetailForm("まあまあ当てはまる"));
        questionDetailForms.add(new QuestionDetailForm("どちらでもない"));
        questionDetailForms.add(new QuestionDetailForm("ほとんど当てはまらない"));
        questionDetailForms.add(new QuestionDetailForm("全く当てはまらない"));

        // 複数選択型の質問（テンプレート）
        ArrayList<QuestionDetailForm> questionDetailForms2 = new ArrayList<>();
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());

        // コメント記載型の質問（テンプレート）
        QuestionForm questionForm1 = new QuestionForm(Question.DocType.singleQ, true);
        questionForm1.setQuestionDetails(questionDetailForms);
        QuestionForm questionForm2 = new QuestionForm(Question.DocType.multiQ, true);
        questionForm2.setQuestionDetails(questionDetailForms2);
        QuestionForm questionForm3 = new QuestionForm(Question.DocType.commentQ, false);
        questionForm3.setQuestionDetails(questionDetailForms);

        questionForms.add(questionForm1);
        questionForms.add(questionForm2);
        questionForms.add(questionForm3);
        questionRootForm.setQuestions(questionForms);
        return questionRootForm;
    }
}

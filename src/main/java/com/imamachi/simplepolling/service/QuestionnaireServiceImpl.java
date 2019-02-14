package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.*;
import com.imamachi.simplepolling.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private CurrentQuestionnaireRepository currentQuestionnaireRepository;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionRepository questionRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public QuestionnaireServiceImpl(CurrentQuestionnaireRepository currentQuestionnaireRepository,
                                    QuestionnaireRepository questionnaireRepository,
                                    QuestionRepository questionRepository,
                                    DepartmentRepository departmentRepository){
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.departmentRepository = departmentRepository;
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

        ArrayList<QuestionDetailForm> questionDetailForms3 = new ArrayList<>();
        questionDetailForms3.add(new QuestionDetailForm("dummy"));

        // コメント記載型の質問（テンプレート）
        QuestionForm questionForm1 = new QuestionForm(Question.DocType.singleQ, true);
        questionForm1.setQuestionDetails(questionDetailForms);
        QuestionForm questionForm2 = new QuestionForm(Question.DocType.multiQ, true);
        questionForm2.setQuestionDetails(questionDetailForms2);
        QuestionForm questionForm3 = new QuestionForm(Question.DocType.commentQ, false);
        questionForm3.setQuestionDetails(questionDetailForms3);

        questionForms.add(questionForm1);
        questionForms.add(questionForm2);
        questionForms.add(questionForm3);
        questionRootForm.setQuestions(questionForms);
        return questionRootForm;
    }

    // アンケートの取得
    @Override
    public Questionnaire getQuestionnaireById(Integer questionnaireId){
        return questionnaireRepository.findById(questionnaireId).get();
    }

    // アンケートの登録
    @Override
    public Boolean registerQuestionnaire(QuestionRootForm questionRootForm){

        // アンケートタイトルをDBに格納
        Questionnaire questionnaire = new Questionnaire(questionRootForm.getTitle());
        questionnaireRepository.save(questionnaire);

        // アンケート内容をDBに格納
        for(QuestionForm questionForm : questionRootForm.getQuestions()){
            List<QuestionDetail> questionDetails = new ArrayList<>();
            if(questionForm.getQuestionDetails() != null) {
                for (QuestionDetailForm questionDetailForm : questionForm.getQuestionDetails()) {
                    questionDetails.add(new QuestionDetail(questionDetailForm.getDescription()));
                }
            }

            Question question = new Question(questionForm.getDocType(), questionForm.isRequirement(),
                    questionForm.getQuestionDesc(), questionDetails, questionnaire);

            questionDetails.forEach(questionDetail -> {
                questionDetail.setQuestion(question);
            });

            // DBに保存
            questionRepository.save(question);
        }

        return true;
    }

    // 部署リストの取得
    @Override
    public List<String> getDepartmentAll(){
        return departmentRepository.findAll()
                .stream()
                .map(item -> item.getDepartmentName())
                .collect(Collectors.toList());
    }
}

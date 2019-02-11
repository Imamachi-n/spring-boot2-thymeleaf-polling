package com.imamachi.simplepolling;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.QuestionDetail;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.CurrentQuestionnaireRepository;
import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    // 登録データ
    private final static String QUESTIONNAIRE_TITLE = "2018年度　有給休暇取得と時間外勤務に関するアンケート";
    private final static String QUESTION_DETAIL_1 = "アンケート内容１";
    private final static String QUESTION_DETAIL_2 = "アンケート内容２";
    private final static String QUESTION_DETAIL_3 = "アンケート内容３";
    private final static String QUESTION_DETAIL_4 = "いまいち";
    private final static String QUESTION_DETAIL_5 = "ふつう";
    private final static String QUESTION_DETAIL_6 = "よかった";
    private final static String QUESTION_DESC = "平均的に考えて、当社のカスタマーサービス担当者はどれほどお客様のお役に立っていますか？";
    private final static String QUESTION_DESC2 = "平均的にみて、当社のカスタマーサービス担当者との会話において、不満に感じられる程度を教えてください。";
    private final static String QUESTION_DESC3 = "自由なコメントをご記入ください。";

    private boolean alreadySetup = false;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionRepository questionRepository;
    private CurrentQuestionnaireRepository currentQuestionnaireRepository;

    @Autowired
    public InitialDataLoader(QuestionnaireRepository questionnaireRepository,
                             QuestionRepository questionRepository,
                             CurrentQuestionnaireRepository currentQuestionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        // アンケートタイトルをDBに格納
        Questionnaire questionnaire = new Questionnaire(QUESTIONNAIRE_TITLE);
        questionnaireRepository.save(questionnaire);

        // 現在のアンケートを保存
        List<Questionnaire> questionnaire1 = questionnaireRepository.findAll();
        CurrentQuestionnaire currentQuestionnaire = new CurrentQuestionnaire(questionnaire1.get(0));
        currentQuestionnaireRepository.save(currentQuestionnaire);

        // アンケート内容をDBに格納
        QuestionDetail questionDetail1 = new QuestionDetail(QUESTION_DETAIL_1);
        QuestionDetail questionDetail2 = new QuestionDetail(QUESTION_DETAIL_2);
        QuestionDetail questionDetail3 = new QuestionDetail(QUESTION_DETAIL_3);
        QuestionDetail questionDetail4 = new QuestionDetail(QUESTION_DETAIL_4);
        QuestionDetail questionDetail5 = new QuestionDetail(QUESTION_DETAIL_5);
        QuestionDetail questionDetail6 = new QuestionDetail(QUESTION_DETAIL_6);
        QuestionDetail questionDetail7 = new QuestionDetail(QUESTION_DETAIL_1);

        // アンケート情報をDBに格納
        Question question = new Question(Question.DocType.multiQ, true, QUESTION_DESC,
                Arrays.asList(questionDetail1, questionDetail2, questionDetail3), questionnaire);
        Question question2 = new Question(Question.DocType.singleQ, true, QUESTION_DESC2,
                Arrays.asList(questionDetail4, questionDetail5, questionDetail6), questionnaire);
        Question question3 = new Question(Question.DocType.commentQ, true, QUESTION_DESC3,
                Arrays.asList(questionDetail7), questionnaire);

        questionDetail1.setQuestion((question));
        questionDetail2.setQuestion((question));
        questionDetail3.setQuestion((question));
        questionDetail4.setQuestion((question2));
        questionDetail5.setQuestion((question2));
        questionDetail6.setQuestion((question2));
        questionDetail7.setQuestion((question3));

        // DBへ登録
        questionRepository.save(question);
        questionRepository.save(question2);
        questionRepository.save(question3);
    }
}

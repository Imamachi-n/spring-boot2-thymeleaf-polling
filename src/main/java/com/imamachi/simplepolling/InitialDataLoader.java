package com.imamachi.simplepolling;

import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.QuestionDetail;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    // 登録データ
    private final static String QUESTIONNAIRE_TITLE = "アンケート１";
    private final static String QUESTION_DETAIL_1 = "アンケート内容１";
    private final static String QUESTION_DETAIL_2 = "アンケート内容２";
    private final static String QUESTION_DETAIL_3 = "アンケート内容３";
    private final static String QUESTION_DETAIL_4 = "いまいち";
    private final static String QUESTION_DETAIL_5 = "ふつう";
    private final static String QUESTION_DETAIL_6 = "よかった";
    private final static String QUESTION_DESC = "質問内容１";
    private final static String QUESTION_DESC2 = "質問内容2";

    private boolean alreadySetup = false;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public InitialDataLoader(QuestionnaireRepository questionnaireRepository,
                             QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        // アンケートタイトルをDBに格納
        Questionnaire questionnaire = new Questionnaire(QUESTIONNAIRE_TITLE);
        questionnaireRepository.save(questionnaire);

        // アンケート内容をDBに格納
        QuestionDetail questionDetail1 = new QuestionDetail(QUESTION_DETAIL_1);
        QuestionDetail questionDetail2 = new QuestionDetail(QUESTION_DETAIL_2);
        QuestionDetail questionDetail3 = new QuestionDetail(QUESTION_DETAIL_3);
        QuestionDetail questionDetail4 = new QuestionDetail(QUESTION_DETAIL_4);
        QuestionDetail questionDetail5 = new QuestionDetail(QUESTION_DETAIL_5);
        QuestionDetail questionDetail6 = new QuestionDetail(QUESTION_DETAIL_6);

        // アンケート情報をDBに格納
        Question question = new Question(Question.DocType.multiQ, true, QUESTION_DESC,
                Arrays.asList(questionDetail1, questionDetail2, questionDetail3), questionnaire);
        Question question2 = new Question(Question.DocType.singleQ, true, QUESTION_DESC,
                Arrays.asList(questionDetail4, questionDetail5, questionDetail6), questionnaire);

        questionDetail1.setQuestion((question));
        questionDetail2.setQuestion((question));
        questionDetail3.setQuestion((question));
        questionDetail4.setQuestion((question));
        questionDetail5.setQuestion((question));
        questionDetail6.setQuestion((question));

        // DBへ登録
        questionRepository.save(question);
        questionRepository.save(question2);
    }
}

package com.imamachi.simplepolling.model;

import com.imamachi.simplepolling.repository.QuestionRepository;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    // 登録データ
    private final static String QUESTIONNAIRE_TITLE = "アンケート１";
    private final static String QUESTION_DETAIL_1 = "アンケート内容１";
    private final static String QUESTION_DETAIL_2 = "アンケート内容２";
    private final static String QUESTION_DETAIL_3 = "アンケート内容３";
    private final static String QUESTION_DESC = "質問内容１";

    @Test
    public void addMultiQ(){
        // アンケートタイトルをDBに格納
        Questionnaire questionnaire = new Questionnaire(QUESTIONNAIRE_TITLE);
        questionnaireRepository.save(questionnaire);

        // アンケート内容をDBに格納
        QuestionDetail questionDetail1 = new QuestionDetail(QUESTION_DETAIL_1);
        QuestionDetail questionDetail2 = new QuestionDetail(QUESTION_DETAIL_2);
        QuestionDetail questionDetail3 = new QuestionDetail(QUESTION_DETAIL_3);

        // アンケート情報をDBに格納
        Question question = new Question(Question.DocType.multiQ, true, QUESTION_DESC,
                Arrays.asList(questionDetail1, questionDetail2, questionDetail3), questionnaire);

        questionDetail1.setQuestion((question));
        questionDetail2.setQuestion((question));
        questionDetail3.setQuestion((question));

        // DBへ登録
        questionRepository.save(question);

        // 登録内容の確認
        List<Question> result = questionRepository.findAll();
        for (Question element : result) {
            assertThat(element.getQuestionnaire().getTitle(), is(QUESTIONNAIRE_TITLE));
            assertThat(element.getQuestionDesc(), is(QUESTION_DESC));

            List<String> questionDetails = Arrays.asList(QUESTION_DETAIL_1, QUESTION_DETAIL_2, QUESTION_DETAIL_3);
            int i = 0;
            for (QuestionDetail item : element.getQuestionDetail()) {
                assertThat(item.getDescription(), is(questionDetails.get(i)));
                i++;
            }
        }

    }
}

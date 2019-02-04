package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionnaireInfo();

    void addQuestionItem(QuestionRootForm questionRootForm, int index);

    boolean deleteQuestionItem(QuestionRootForm questionRootForm, int index);

    void addSingleQuestion(QuestionRootForm questionRootForm);

    void addMultiQuestion(QuestionRootForm questionRootForm);

    void addCommentQuestion(QuestionRootForm questionRootForm);

    boolean deleteQuestion(QuestionRootForm questionRootForm, int index);
}

package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById

    List<Question> findByQuestionnaireOrderByQuestionId(Questionnaire questionnaire);

    void deleteByQuestionnaire(Questionnaire questionnaire);

}

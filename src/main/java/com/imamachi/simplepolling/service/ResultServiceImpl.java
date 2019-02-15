package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ResultDetailForm;
import com.imamachi.simplepolling.form.ResultForm;
import com.imamachi.simplepolling.form.ResultRootForm;
import com.imamachi.simplepolling.model.*;
import com.imamachi.simplepolling.repository.QuestionnaireRepository;
import com.imamachi.simplepolling.repository.RespondentRepository;
import com.imamachi.simplepolling.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ResultServiceImpl implements ResultService {

    private QuestionnaireRepository questionnaireRepository;
    private RespondentRepository respondentRepository;
    private ResultRepository resultRepository;

    @Autowired
    public ResultServiceImpl(QuestionnaireRepository questionnaireRepository,
                             RespondentRepository respondentRepository,
                             ResultRepository resultRepository){
        this.questionnaireRepository = questionnaireRepository;
        this.respondentRepository = respondentRepository;
        this.resultRepository = resultRepository;
    }

    // 入力チェック（回答のない質問があった場合、エラー扱いとする）
    @Override
    public boolean existFormError(ResultRootForm resultRootForm){

        boolean result = false;

        for(ResultForm resultForm : resultRootForm.getResultForms()){
            // コメント回答の場合、エラーチェックを行わない
            if(resultForm.getDocType().equals(Question.DocType.commentQ)) continue;

            // 回答しているかチェック
            if(resultForm.getResultDetailForms() == null){
                // 未回答の場合
                resultForm.setIsError(true);
                result = true;
                continue;
            }
            List<ResultDetailForm> tmp = resultForm.getResultDetailForms().stream()
                                    .filter(resultDetailForm -> resultDetailForm.getAnswer() != null)
                                    .collect(Collectors.toList());
            if(tmp.size() == 0) {
                // 未回答の場合
                resultForm.setIsError(true);
                result = true;
            }else{
                // 回答済みの場合
                resultForm.setIsError(false);

                // 単一選択回答の場合、NumberとAnswerをセットする
                if(resultForm.getDocType().equals(Question.DocType.singleQ)){
                    Integer index = Integer.parseInt(resultForm.getResultDetailForms().get(0).getAnswer().split(":")[0]) + 1;
                    String answer = resultForm.getResultDetailForms().get(0).getAnswer().split(":")[1];
                    resultForm.getResultDetailForms().get(0).setNumber(index.toString());
                    resultForm.getResultDetailForms().get(0).setAnswer(answer);
                }
            }
        }
        return result;
    }

    // アンケート結果の登録
    @Override
    @Transactional
    public boolean registerResult(ResultRootForm resultRootForm){
        // アンケート情報の取得
        Optional<Questionnaire> questionnaire = questionnaireRepository.findByTitle(resultRootForm.getQuestionnaireTitle());
        if(!questionnaire.isPresent()) return false;

        // ユーザの登録
        respondentRepository.save(new Respondent(resultRootForm.getUsername(), questionnaire.get()));

        // 登録
        Result result = new Result(questionnaire.get());
        List<ResultDetail> resultDetails = new ArrayList<>();
        for (ResultForm resultForm: resultRootForm.getResultForms()){
            // 回答がない場合、次の質問に移る
            if(resultForm.getResultDetailForms() == null) continue;

            // 各回答を登録
            for(ResultDetailForm resultDetail : resultForm.getResultDetailForms()) {
                // 回答項目の番号の取得
                String strNumber = resultDetail.getNumber();
                int number = 1;
                if(strNumber != null) number = Integer.parseInt(strNumber);

                // 回答項目がない場合、次の回答項目に移る
                if(resultDetail.getAnswer() == null || resultDetail.getAnswer().equals("")) continue;

                ResultDetail resultDetailTmp = new ResultDetail(Integer.parseInt(resultForm.getQuestionnaireNo()),
                        resultForm.getDocType(), resultForm.getDescription(), number, resultDetail.getAnswer(),
                        resultRootForm.getEmployeeStatus(), resultRootForm.getDepartmentName());
                resultDetailTmp.setResult(result);
                resultDetails.add(resultDetailTmp);
            }
        }

        result.setResultDetail(resultDetails);
        resultRepository.save(result);

        return true;
    }
}

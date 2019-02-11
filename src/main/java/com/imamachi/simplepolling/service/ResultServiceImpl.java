package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ResultDetailForm;
import com.imamachi.simplepolling.form.ResultForm;
import com.imamachi.simplepolling.form.ResultRootForm;
import com.imamachi.simplepolling.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

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
}

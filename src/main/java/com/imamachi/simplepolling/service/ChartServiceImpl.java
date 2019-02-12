package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ChartForm;
import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.form.ResultChartData;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.model.Result;
import com.imamachi.simplepolling.model.ResultDetail;
import com.imamachi.simplepolling.repository.ResultDetailRepository;
import com.imamachi.simplepolling.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartServiceImpl implements ChartService {

    private ResultRepository resultRepository;
    private ResultDetailRepository resultDetailRepository;

    @Autowired
    public ChartServiceImpl(ResultRepository resultRepository,
                            ResultDetailRepository resultDetailRepository){
        this.resultRepository = resultRepository;
        this.resultDetailRepository = resultDetailRepository;
    }

    @Override
    public boolean getQuestionnaireResult(Questionnaire questionnaire, List<ChartRootData> chartRootData){

        // アンケート結果を取得する
        List<Result> result = resultRepository.getResultsByQuestionnaire(questionnaire);
        if(result.size() == 0) return false;
        List<ResultChartData> resultChartDataList =  resultDetailRepository.findQuestionnaireCount(result);
        List<ResultChartData> resultChartComments =  resultDetailRepository.findQuestionnaireComment(result);

        // 質問ごとに結果数をまとめる
        Map<Integer, String> chartData = new HashMap<>();
        int counter = -1;
        for(ResultChartData resultChartData : resultChartDataList) {
            int no = resultChartData.getQuestionnaireNo();
            if(chartData.containsKey(no)){
                chartRootData.get(counter).getChartData()
                        .add(new ChartForm(resultChartData.getAnswer(), resultChartData.getCount().toString()));
            }else{
                chartData.put(no, "");

                chartRootData.add(new ChartRootData(resultChartData.getQuestionnaireNo(), resultChartData.getDescription(), resultChartData.getDocType()));
                chartRootData.get(++counter).getChartData()
                        .add(new ChartForm(resultChartData.getAnswer(), resultChartData.getCount().toString()));
            }
        }

        // 質問ごとにコメントをまとめる
        for(ResultChartData resultChartData : resultChartComments) {
            int no = resultChartData.getQuestionnaireNo();
            if(chartData.containsKey(no)){
                chartRootData.get(counter).getChartData()
                        .add(new ChartForm(resultChartData.getAnswer(), ""));
            }else{
                chartData.put(no, "");

                chartRootData.add(new ChartRootData(resultChartData.getQuestionnaireNo(), resultChartData.getDescription(), resultChartData.getDocType()));
                chartRootData.get(++counter).getChartData()
                        .add(new ChartForm(resultChartData.getAnswer(), ""));
            }
        }

        return true;
    }
}

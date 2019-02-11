package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.ChartForm;
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
    public boolean getQuestionnaireResult(Questionnaire questionnaire, List<List<ChartForm>> chartForms, List<String> qType){
//        chartForms = new ArrayList<>();
//        qType = new ArrayList<>();

        List<Result> result = resultRepository.getResultsByQuestionnaire(questionnaire);
        if(result.size() == 0) return false;
        List<ResultChartData> resultChartDatas =  resultDetailRepository.findQuestionnaireCount(result);
        List<ResultChartData> resultChartComments =  resultDetailRepository.findQuestionnaireComment(result);

        // 質問ごとに結果数をまとめる
        Map<Integer, ArrayList<ChartForm>> chartData = new LinkedHashMap<>();
        Map<Integer, String> chartType = new LinkedHashMap<>();
        for(ResultChartData resultChartData : resultChartDatas) {
            int no = resultChartData.getQuestionnaireNo();
            if(chartData.containsKey(no)){
                chartData.get(no).add(new ChartForm(resultChartData.getAnswer(), resultChartData.getCount().toString()));
            }else{
                ArrayList<ChartForm> tmp = new ArrayList<>();
                tmp.add(new ChartForm(resultChartData.getAnswer(), resultChartData.getCount().toString()));
                chartData.put(no, tmp);

                chartType.put(no, resultChartData.getDocType().toString());
            }
        }

        for(int questionId : chartType.keySet()){
            qType.add(chartType.get(questionId
            ));
        }

        for(int questionId : chartData.keySet()){
            chartForms.add(chartData.get(questionId));
        }

        // 質問ごとにコメントをまとめる
        Map<Integer, ArrayList<String>> chartComment = new HashMap<>();
        for(ResultChartData resultChartData : resultChartComments) {
            int no = resultChartData.getQuestionnaireNo();
            if(chartComment.containsKey(no)){
                chartComment.get(no).add(resultChartData.getAnswer());
            }else{
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(resultChartData.getAnswer());
                chartComment.put(no, tmp);
            }
        }

        return true;
    }
}

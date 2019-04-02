package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.form.ResultChartData;
import com.imamachi.simplepolling.model.Result;
import com.imamachi.simplepolling.model.ResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDetailRepository extends JpaRepository<ResultDetail, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById

    // 質問事項の回答数を取得
    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType, count(rd)) " +
            "from ResultDetail rd where rd.result IN :result and rd.docType <> 2" +
            "group by rd.answerId, rd.questionnaireNo " +
            "order by rd.questionnaireNo, rd.answerId")
    List<ResultChartData> findQuestionnaireCount(List<Result> result);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType, count(rd)) " +
            "from ResultDetail rd where rd.result IN :result and rd.departmentName = :department " +
            "and rd.employeeStatus = :employee and rd.docType <> 2" +
            "group by rd.answerId, rd.questionnaireNo " +
            "order by rd.questionnaireNo, rd.answerId")
    List<ResultChartData> findQuestionnaireCountByDepertmentAndEmployee(List<Result> result, String department, String employee);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType, count(rd)) " +
            "from ResultDetail rd where rd.result IN :result and rd.departmentName = :department " +
            "and rd.docType <> 2" +
            "group by rd.answerId, rd.questionnaireNo " +
            "order by rd.questionnaireNo, rd.answerId")
    List<ResultChartData> findQuestionnaireCountByDepertment(List<Result> result, String department);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType, count(rd)) " +
            "from ResultDetail rd where rd.result IN :result " +
            "and rd.employeeStatus = :employee and rd.docType <> 2" +
            "group by rd.answerId, rd.questionnaireNo " +
            "order by rd.questionnaireNo, rd.answerId")
    List<ResultChartData> findQuestionnaireCountByEmployee(List<Result> result, String employee);

    // コメントのリストを取得
    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType) " +
            "from ResultDetail rd where rd.result IN :result and rd.docType = 2" +
            "order by rd.questionnaireNo, rd.resultDetailId")
    List<ResultChartData> findQuestionnaireComment(List<Result> result);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType) " +
            "from ResultDetail rd where rd.result IN :result and rd.departmentName = :department " +
            "and rd.employeeStatus = :employee and rd.docType = 2" +
            "order by rd.questionnaireNo, rd.resultDetailId")
    List<ResultChartData> findQuestionnaireCommentByDepartmentAndEmployee(List<Result> result, String department, String employee);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType) " +
            "from ResultDetail rd where rd.result IN :result and rd.departmentName = :department " +
            "and rd.docType = 2" +
            "order by rd.questionnaireNo, rd.resultDetailId")
    List<ResultChartData> findQuestionnaireCommentByDepartment(List<Result> result, String department);

    @Query(value = "select new com.imamachi.simplepolling.form.ResultChartData(" +
            "rd.answerId, rd.answer, rd.questionnaireNo, rd.description, rd.docType) " +
            "from ResultDetail rd where rd.result IN :result " +
            "and rd.employeeStatus = :employee and rd.docType = 2" +
            "order by rd.questionnaireNo, rd.resultDetailId")
    List<ResultChartData> findQuestionnaireCommentByEmployee(List<Result> result, String employee);

}

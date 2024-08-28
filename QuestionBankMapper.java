package com.dyzc.survey.mapper;

import com.dyzc.survey.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionBankMapper {

    // 插入新题目
    void insertQuestion(QuestionBankModel question);

    // 批量插入单选题选项
    void batchInsertRadioOptions(@Param("options") List<RadioQuestionModel> options);

    // 批量插入多选题选项
    void batchInsertCheckOptions(@Param("options") List<CheckQuestionModel> options);

    // 批量插入多项填空题选项
    void batchInsertMultiFillBlankOptions(@Param("options") List<MultiFillBlankQuestionModel> options);

    // 批量插入评分题选项
    void batchInsertScoreOptions(@Param("options") List<ScoreQuestionModel> options);

    // 删除题目
    void deleteQuestion(@Param("quId") String quId);

    // 删除单选题选项
    void deleteRadioOptionsByQuestionId(@Param("quId") String quId);

    // 删除多选题选项
    void deleteCheckOptionsByQuestionId(@Param("quId") String quId);

    // 删除多项填空题选项
    void deleteMultiFillBlankOptionsByQuestionId(@Param("quId") String quId);

    // 删除评分题选项
    void deleteScoreOptionsByQuestionId(@Param("quId") String quId);

    // 更新题目基本信息
    void updateQuestionByNameAndDescription(QuestionBankModel question);

    // 查询题目及选项
    List<QuestionBankModel> selectQuestionsByBankCode(@Param("quBankCode") String quBankCode);

    // 查询单选题选项
    List<RadioQuestionModel> selectRadioOptionsByQuestionId(@Param("quId") String quId);

    // 查询多选题选项
    List<CheckQuestionModel> selectCheckOptionsByQuestionId(@Param("quId") String quId);

    // 查询多项填空题选项
    List<MultiFillBlankQuestionModel> selectMultiFillBlankOptionsByQuestionId(@Param("quId") String quId);

    // 查询评分题选项
    List<ScoreQuestionModel> selectScoreOptionsByQuestionId(@Param("quId") String quId);

    // 模糊搜索题目
    List<QuestionBankModel> searchQuestions(@Param("quBankCode") String quBankCode, @Param("search") String search);

    // 获取题目类型
    String getQuestionTypeByQuId(@Param("quId") String quId);

    // 统计题目数量
    int countQuestionsByBankCode(@Param("quBankCode") String quBankCode);

    // 获取题库键值
    String getQuBankCodeByQuId(@Param("quId") String quId);

    // 获取题目的排序序号
    int getOrderByIdByQuId(@Param("quId") String quId);

    // 更新排序序号
    void updateOrderByIdAfterDeletion(@Param("quBankCode") String quBankCode, @Param("orderById") int orderById);

    // 获取题库下的最大排序序号
    int getMaxOrderByIdByQuBankCode(@Param("quBankCode") String quBankCode);

}
package com.dyzc.survey.mapper;

import com.dyzc.survey.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionBankMapper {

    // 插入新题目
    void insertQuestion(QuestionBankModel question);

    // 插入题目选项
    void insertRadioOption(RadioQuestionModel radioOption);
    void insertCheckOption(CheckQuestionModel checkOption);
    void insertMultiFillBlankOption(MultiFillBlankQuestionModel multiFillBlankOption);
    void insertScoreOption(ScoreQuestionModel scoreOption);

    // 删除题目
    void deleteQuestion(@Param("quId") String quId);

    // 删除题目选项
    void deleteRadioOptionsByQuestionId(@Param("quId") String quId);
    void deleteCheckOptionsByQuestionId(@Param("quId") String quId);
    void deleteMultiFillBlankOptionsByQuestionId(@Param("quId") String quId);
    void deleteScoreOptionsByQuestionId(@Param("quId") String quId);

    // 更新题目和选项的排序
    void updateOrderByIdAfterDeletion(@Param("belongId") String belongId, @Param("orderById") int orderById);

    // 删除选项
    void deleteRadioOption(@Param("radioId") String radioId);
    void deleteCheckOption(@Param("id") String id);
    void deleteMultiFillBlankOption(@Param("multiFillBlankQuId") String multiFillBlankQuId);
    void deleteScoreOption(@Param("scoreQuId") String scoreQuId);

    // 更新题目
    void updateQuestionByNameAndDescription(QuestionBankModel question);

    // 更新选项
    void updateRadioOption(RadioQuestionModel radioOption);
    void updateCheckOption(CheckQuestionModel checkOption);
    void updateMultiFillBlankOption(MultiFillBlankQuestionModel multiFillBlankOption);
    void updateScoreOption(ScoreQuestionModel scoreOption);

    // 查询题目和选项
    List<QuestionBankModel> selectQuestionsByBankCode(@Param("quBankCode") String quBankCode);
    List<RadioQuestionModel> selectRadioOptionsByQuestionId(@Param("quId") String quId);
    List<CheckQuestionModel> selectCheckOptionsByQuestionId(@Param("quId") String quId);
    List<MultiFillBlankQuestionModel> selectMultiFillBlankOptionsByQuestionId(@Param("quId") String quId);
    List<ScoreQuestionModel> selectScoreOptionsByQuestionId(@Param("quId") String quId);

    // 模糊搜索题目
    List<QuestionBankModel> searchQuestions(@Param("quBankCode") String quBankCode, @Param("quName") String quName, @Param("quDescription") String quDescription);

    // 统计题目数量
    int countQuestionsByBankCode(@Param("quBankCode") String quBankCode);

    // 获取排序信息
    int getMaxOrderByIdByQuBankCode(@Param("quBankCode") String quBankCode);
    int getOrderByIdByQuId(@Param("quId") String quId);
    int getMaxRadioOrderById(@Param("quId") String quId);
    int getMaxCheckOrderById(@Param("quId") String quId);
    int getMaxMultiFillBlankOrderById(@Param("quId") String quId);
    int getMaxScoreOrderById(@Param("quId") String quId);
    int getRadioOrderByIdByOptionId(@Param("optionId") String optionId);
    int getCheckOrderByIdByOptionId(@Param("optionId") String optionId);
    int getMultiFillBlankOrderByIdByOptionId(@Param("optionId") String optionId);
    int getScoreOrderByIdByOptionId(@Param("optionId") String optionId);

    // 更新排序
    void updateRadioOptionOrderByIdAfterDeletion(@Param("belongQuId") String belongQuId, @Param("orderById") int orderById);
    void updateCheckOptionOrderByIdAfterDeletion(@Param("quId") String quId, @Param("orderById") int orderById);
    void updateMultiFillBlankOptionOrderByIdAfterDeletion(@Param("quId") String quId, @Param("orderById") int orderById);
    void updateScoreOptionOrderByIdAfterDeletion(@Param("quId") String quId, @Param("orderById") int orderById);

    // 获取题库信息
    String getQuBankCodeByQuId(@Param("quId") String quId);
    String getQuestionTypeByQuId(@Param("quId") String quId);

    // 获取选项对应的题目ID
    String getQuestionIdByOptionId(@Param("optionId") String optionId);

}

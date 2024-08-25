package com.dyzc.survey.service;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.survey.domain.QuestionBankModel;

import java.util.List;

public interface QuestionBankService {


    /**
     * 添加新题目
     * @param question 题目模型对象
     */
    AjaxResult addQuestion(QuestionBankModel question);
    //void addQuestion(QuestionBankModel question);

    /**
     * 添加题目选项
     * @param quId 题目ID
     * @param optionName 选项内容
     */
    AjaxResult addOption(String quId, String optionName);
    //void addOption(String quId, String optionName);

    /**
     * 删除题目及其所有选项
     * @param quId 题目ID
     */
    AjaxResult deleteQuestionAndOptions(String quId);
    //void deleteQuestionAndOptions(String quId);

    /**
     * 删除题目选项
     * @param optionId 选项ID
     */
    AjaxResult deleteOption(String optionId);
    //void deleteOption(String optionId);

    /**
     * 更新题目信息
     * @param quId 题目ID
     * @param quName 题目名称
     * @param quDescription 题目描述
     */
    AjaxResult updateQuestion(String quId, String quName, String quDescription);
    //void updateQuestion(String quId, String quName, String quDescription);

    /**
     * 更新题目选项
     * @param optionId 选项ID
     * @param optionName 选项内容
     */
    AjaxResult updateOption(String optionId, String optionName);
    //void updateOption(String optionId, String optionName);

    /**
     * 根据题库键值查询所有题目
     * @param quBankCode 题库键值
     * @return 题目列表
     */
    AjaxResult getQuestionsByBankCode(String quBankCode);
    //List<QuestionBankModel> getQuestionsByBankCode(String quBankCode);

    /**
     * 根据题目ID查询所有选项
     * @param quId 题目ID
     * @return 选项列表
     */
    AjaxResult getOptionsByQuestionId(String quId);
    //List<?> getOptionsByQuestionId(String quId);

    /**
     * 模糊搜索题目
     * @param quBankCode 题库键值
     * @param quName 题目名称
     * @param quDescription 题目描述
     * @return 题目列表
     */
    AjaxResult searchQuestions(String quBankCode, String quName, String quDescription);
    //List<QuestionBankModel> searchQuestions(String quBankCode, String quName, String quDescription);

    /**
     * 根据题库键值统计题目数量
     * @param quBankCode 题库键值
     * @return 题目数量
     */
    AjaxResult countQuestionsByBankCode(String quBankCode);
    //int countQuestionsByBankCode(String quBankCode);

}

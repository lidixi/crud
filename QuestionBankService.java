package com.dyzc.survey.service;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.survey.domain.QuestionBankModel;
import java.util.List;

public interface QuestionBankService {

    /**
     * 添加新题目及其选项
     *
     * @param questions 题目及选项的列表
     * @return 操作结果
     */
    AjaxResult addQuestions(List<QuestionBankModel> questions);

    /**
     * 更新题目及其选项
     *
     * @param questions 题目及选项的列表
     * @return 操作结果
     */
    AjaxResult updateQuestions(List<QuestionBankModel> questions);

    /**
     * 根据题库键值查询所有题目及选项
     *
     * @param quBankCode 题库键值
     * @return 题目及选项的列表
     */
    AjaxResult getQuestionsByBankCode(String quBankCode);

    /**
     * 模糊搜索题目及选项
     *
     * @param quBankCode 题库键值
     * @param search     搜索关键词
     * @return 题目及选项的列表
     */
    AjaxResult searchQuestions(String quBankCode, String search);

    /**
     * 删除题目及其所有选项
     *
     * @param quId 题目ID
     * @return 操作结果
     */
    AjaxResult deleteQuestionAndOptions(String quId);

    /**
     * 根据题库键值统计题目数量
     *
     * @param quBankCode 题库键值
     * @return 题目数量
     */
    AjaxResult countQuestionsByBankCode(String quBankCode);

}

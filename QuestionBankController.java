package com.dyzc.web.controller.follow.survey;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.survey.domain.QuestionBankModel;
import com.dyzc.survey.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/questionBank")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 添加新题目
     *
     * @param question 题目模型对象
     */
    @PostMapping("/addQuestion")
    public AjaxResult addQuestion(@RequestBody QuestionBankModel question) {
        try {
            return questionBankService.addQuestion(question);
        } catch (Exception e) {
            return AjaxResult.error("添加新题目失败: " + e.getMessage());
        }
    }
    /**
    @PostMapping("/addQuestion")
    public void addQuestion(@RequestBody QuestionBankModel question) {
        questionBankService.addQuestion(question);
    }
    */

    /**
     * 添加题目选项
     *
     * @param quId       题目ID
     * @param optionName 选项内容
     */
    @PostMapping("/addOption")
    public AjaxResult addOption(@RequestParam String quId, @RequestParam String optionName) {
        try {
            return questionBankService.addOption(quId, optionName);
        } catch (Exception e) {
            return AjaxResult.error("添加题目选项失败: " + e.getMessage());
        }
    }
    /**
    @PostMapping("/addOption")
    public void addOption(@RequestParam String quId, @RequestParam String optionName) {
        questionBankService.addOption(quId, optionName);
    }
    */

    /**
     * 删除题目及其所有选项
     *
     * @param quId 题目ID
     */
    @DeleteMapping("/deleteQuestionAndOptions")
    public AjaxResult deleteQuestionAndOptions(@RequestParam String quId) {
        try {
            return questionBankService.deleteQuestionAndOptions(quId);
        } catch (Exception e) {
            return AjaxResult.error("删除题目及其所有选项失败: " + e.getMessage());
        }
    }

    /**
    @DeleteMapping("/deleteQuestionAndOptions")
    public void deleteQuestionAndOptions(@RequestParam String quId) {
        questionBankService.deleteQuestionAndOptions(quId);
    }
    */

    /**
     * 删除题目选项
     *
     * @param optionId 选项ID
     */
    @DeleteMapping("/deleteOption")
    public AjaxResult deleteOption(@RequestParam String optionId) {
        try {
            return questionBankService.deleteOption(optionId);
        } catch (Exception e) {
            return AjaxResult.error("删除题目选项失败: " + e.getMessage());
        }
    }
    /**
    @DeleteMapping("/deleteOption")
    public void deleteOption(@RequestParam String optionId) {
        questionBankService.deleteOption(optionId);
    }
    */

    /**
     * 更新题目信息
     *
     * @param quId          题目ID
     * @param quName        题目名称
     * @param quDescription 题目描述
     */
    @PutMapping("/updateQuestion")
    public AjaxResult updateQuestion(@RequestParam String quId, @RequestParam String quName, @RequestParam String quDescription) {
        try {
            return questionBankService.updateQuestion(quId, quName, quDescription);
        } catch (Exception e) {
            return AjaxResult.error("更新题目信息失败: " + e.getMessage());
        }
    }
    /**
    @PutMapping("/updateQuestion")
    public void updateQuestion(@RequestParam String quId, @RequestParam String quName, @RequestParam String quDescription) {
        questionBankService.updateQuestion(quId, quName, quDescription);
    }
    */

    /**
     * 更新题目选项
     *
     * @param optionId   选项ID
     * @param optionName 选项内容
     */
    @PutMapping("/updateOption")
    public AjaxResult updateOption(@RequestParam String optionId, @RequestParam String optionName) {
        try {
            return questionBankService.updateOption(optionId, optionName);
        } catch (Exception e) {
            return AjaxResult.error("更新题目选项失败: " + e.getMessage());
        }
    }
    /**
    @PutMapping("/updateOption")
    public void updateOption(@RequestParam String optionId, @RequestParam String optionName) {
        questionBankService.updateOption(optionId, optionName);
    }
    */

    /**
     * 根据题库键值查询所有题目
     *
     * @param quBankCode 题库键值
     * @return 题目列表
     */
    @GetMapping("/getQuestionsByBankCode")
    public AjaxResult getQuestionsByBankCode(@RequestParam String quBankCode) {
        try {
            return questionBankService.getQuestionsByBankCode(quBankCode);
        } catch (Exception e) {
            return AjaxResult.error("查询题库失败: " + e.getMessage());
        }
    }
    /**
    @GetMapping("/getQuestionsByBankCode")
    public List<QuestionBankModel> getQuestionsByBankCode(@RequestParam String quBankCode) {
        return questionBankService.getQuestionsByBankCode(quBankCode);
    }
    */

    /**
     * 根据题目ID查询所有选项
     *
     * @param quId 题目ID
     * @return 选项列表
     */
    @GetMapping("/getOptionsByQuestionId")
    public AjaxResult getOptionsByQuestionId(@RequestParam String quId) {
        try {
            return questionBankService.getOptionsByQuestionId(quId);
        } catch (Exception e) {
            return AjaxResult.error("查询选项失败: " + e.getMessage());
        }
    }
    /**
    @GetMapping("/getOptionsByQuestionId")
    public List<?> getOptionsByQuestionId(@RequestParam String quId) {
        return questionBankService.getOptionsByQuestionId(quId);
    }
    */

    /**
     * 模糊搜索题目
     *
     * @param quBankCode    题库键值
     * @param quName        题目名称（可选）
     * @param quDescription 题目描述（可选）
     * @return 题目列表
     */
    @GetMapping("/searchQuestions")
    public AjaxResult searchQuestions(@RequestParam String quBankCode,
                                      @RequestParam(required = false) String quName,
                                      @RequestParam(required = false) String quDescription) {
        try {
            return questionBankService.searchQuestions(quBankCode, quName, quDescription);
        } catch (Exception e) {
            return AjaxResult.error("模糊搜索题目失败: " + e.getMessage());
        }
    }
    /**
    @GetMapping("/searchQuestions")
    public List<QuestionBankModel> searchQuestions(@RequestParam String quBankCode,
                                                   @RequestParam(required = false) String quName,
                                                   @RequestParam(required = false) String quDescription){
        return questionBankService.searchQuestions(quBankCode, quName, quDescription);
    }
    */

    /**
     * 根据题库键值统计题目数量
     *
     * @param quBankCode 题库键值
     * @return 题目数量
     */
    @GetMapping("/countQuestionsByBankCode")
    public AjaxResult countQuestionsByBankCode(@RequestParam String quBankCode) {
        try {
            return questionBankService.countQuestionsByBankCode(quBankCode);
        } catch (Exception e) {
            return AjaxResult.error("统计题目数量失败: " + e.getMessage());
        }
    }
    /**
    @GetMapping("/countQuestionsByBankCode")
    public int countQuestionsByBankCode(@RequestParam String quBankCode) {
        return questionBankService.countQuestionsByBankCode(quBankCode);
    }
    */
}

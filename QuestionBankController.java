package com.dyzc.web.controller.follow.survey;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.common.exception.user.UserNotFundException;
import com.dyzc.framework.util.ShiroUtils;
import com.dyzc.survey.domain.QuestionBankModel;
import com.dyzc.survey.service.QuestionBankService;
import com.dyzc.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/questionBank")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    @PostMapping("/addQuestion")
    public AjaxResult addQuestion(@RequestBody List<QuestionBankModel> questions) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.addQuestions(questions);
    }

    @PostMapping("/updateQuestion")
    public AjaxResult updateQuestion(@RequestBody List<QuestionBankModel> questions) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.updateQuestions(questions);
    }

    @PostMapping("/getQuestionsByBankCode")
    public AjaxResult getQuestionsByBankCode(@RequestParam("quBankCode") String quBankCode) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.getQuestionsByBankCode(quBankCode);
    }

    @PostMapping("/searchQuestions")
    public AjaxResult searchQuestions(@RequestParam("quBankCode") String quBankCode,
                                      @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.searchQuestions(quBankCode, search);
    }

    @PostMapping("/deleteQuestionAndOptions")
    public AjaxResult deleteQuestionAndOptions(@RequestParam("quId") String quId) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.deleteQuestionAndOptions(quId);
    }

    @PostMapping("/countQuestionsByBankCode")
    public AjaxResult countQuestionsByBankCode(@RequestParam("quBankCode") String quBankCode) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return questionBankService.countQuestionsByBankCode(quBankCode);
    }

}

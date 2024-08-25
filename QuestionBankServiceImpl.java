package com.dyzc.survey.service.impl;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.common.core.lang.UUID;
import com.dyzc.common.enums.QuType;
import com.dyzc.survey.domain.*;
import com.dyzc.survey.mapper.QuestionBankMapper;
import com.dyzc.survey.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addQuestion(QuestionBankModel question) {
        try {
            // 生成新题目ID
            question.setQuId(UUID.randomUUID().toString());
            questionBankMapper.insertQuestion(question);
            return AjaxResult.success("题目添加成功");
        } catch (Exception e) {
            return AjaxResult.error("题目添加失败: " + e.getMessage());
        }
    }
    /**
    public void addQuestion(QuestionBankModel question) {
        // 生成新题目ID
        question.setQuId(UUID.randomUUID().toString());
        questionBankMapper.insertQuestion(question);
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addOption(String quId, String optionName) {
        try {
            // 根据题目ID获取题目类型
            String quType = getQuestionTypeByQuId(quId);

            // 根据题目类型选择不同的插入选项方法
            if (QuType.RADIO.getActionName().equals(quType)) {
                RadioQuestionModel radioOption = new RadioQuestionModel();
                radioOption.setRadioId(UUID.randomUUID().toString());
                radioOption.setBelongQuId(quId);
                radioOption.setOptionName(optionName);
                questionBankMapper.insertRadioOption(radioOption);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                CheckQuestionModel checkOption = new CheckQuestionModel();
                checkOption.setId(UUID.randomUUID().toString());
                checkOption.setQuId(quId);
                checkOption.setOptionName(optionName);
                questionBankMapper.insertCheckOption(checkOption);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                MultiFillBlankQuestionModel multiFillBlankOption = new MultiFillBlankQuestionModel();
                multiFillBlankOption.setMultiFillBlankQuId(UUID.randomUUID().toString());
                multiFillBlankOption.setQuId(quId);
                multiFillBlankOption.setOptionName(optionName);
                questionBankMapper.insertMultiFillBlankOption(multiFillBlankOption);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                ScoreQuestionModel scoreOption = new ScoreQuestionModel();
                scoreOption.setScoreQuId(UUID.randomUUID().toString());
                scoreOption.setQuId(quId);
                scoreOption.setOptionName(optionName);
                questionBankMapper.insertScoreOption(scoreOption);
            }
            return AjaxResult.success("选项添加成功");
        } catch (Exception e) {
            return AjaxResult.error("选项添加失败: " + e.getMessage());
        }
    }
    /**
    public void addOption(String quId, String optionName) {
        // 根据题目ID获取题目类型
        String quType = getQuestionTypeByQuId(quId);

        // 根据题目类型选择不同的插入选项方法
        if (QuType.RADIO.getActionName().equals(quType)) {
            RadioQuestionModel radioOption = new RadioQuestionModel();
            radioOption.setRadioId(UUID.randomUUID().toString());
            radioOption.setBelongQuId(quId);
            radioOption.setOptionName(optionName);
            questionBankMapper.insertRadioOption(radioOption);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            CheckQuestionModel checkOption = new CheckQuestionModel();
            checkOption.setId(UUID.randomUUID().toString());
            checkOption.setQuId(quId);
            checkOption.setOptionName(optionName);
            questionBankMapper.insertCheckOption(checkOption);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            MultiFillBlankQuestionModel multiFillBlankOption = new MultiFillBlankQuestionModel();
            multiFillBlankOption.setMultiFillBlankQuId(UUID.randomUUID().toString());
            multiFillBlankOption.setQuId(quId);
            multiFillBlankOption.setOptionName(optionName);
            questionBankMapper.insertMultiFillBlankOption(multiFillBlankOption);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            ScoreQuestionModel scoreOption = new ScoreQuestionModel();
            scoreOption.setScoreQuId(UUID.randomUUID().toString());
            scoreOption.setQuId(quId);
            scoreOption.setOptionName(optionName);
            questionBankMapper.insertScoreOption(scoreOption);
        }
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteQuestionAndOptions(String quId) {
        try {
            // 获取题目类型
            String quType = getQuestionTypeByQuId(quId);
            // 获取题库键值
            String quBankCode = questionBankMapper.getQuBankCodeByQuId(quId);
            // 获取最大orderById
            int maxOrderId = questionBankMapper.getMaxOrderByIdByQuBankCode(quBankCode);
            // 获取当前题目的orderById
            int orderById = questionBankMapper.getOrderByIdByQuId(quId);

            // 如果当前题目的orderById小于最大值，需要更新其后的orderById
            if (orderById < maxOrderId) {
                questionBankMapper.updateOrderByIdAfterDeletion(quBankCode, orderById);
            }

            // 删除题目
            questionBankMapper.deleteQuestion(quId);

            // 根据题目类型删除对应选项
            if (QuType.RADIO.getActionName().equals(quType)) {
                questionBankMapper.deleteRadioOptionsByQuestionId(quId);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                questionBankMapper.deleteCheckOptionsByQuestionId(quId);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                questionBankMapper.deleteMultiFillBlankOptionsByQuestionId(quId);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                questionBankMapper.deleteScoreOptionsByQuestionId(quId);
            }
            return AjaxResult.success("题目及选项删除成功");
        } catch (Exception e) {
            return AjaxResult.error("题目及选项删除失败: " + e.getMessage());
        }
    }
    /**
    public void deleteQuestionAndOptions(String quId) {
        // 获取题目类型
        String quType = getQuestionTypeByQuId(quId);
        // 获取题库键值
        String quBankCode = questionBankMapper.getQuBankCodeByQuId(quId);
        // 获取最大orderById
        int maxOrderId = questionBankMapper.getMaxOrderByIdByQuBankCode(quBankCode);
        // 获取当前题目的orderById
        int orderById = questionBankMapper.getOrderByIdByQuId(quId);

        // 如果当前题目的orderById小于最大值，需要更新其后的orderById
        if (orderById < maxOrderId) {
            questionBankMapper.updateOrderByIdAfterDeletion(quBankCode, orderById);
        }

        // 删除题目
        questionBankMapper.deleteQuestion(quId);

        // 根据题目类型删除对应选项
        if (QuType.RADIO.getActionName().equals(quType)) {
            questionBankMapper.deleteRadioOptionsByQuestionId(quId);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            questionBankMapper.deleteCheckOptionsByQuestionId(quId);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            questionBankMapper.deleteMultiFillBlankOptionsByQuestionId(quId);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            questionBankMapper.deleteScoreOptionsByQuestionId(quId);
        }
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteOption(String optionId) {
        try {
            // 根据选项ID获取题目ID
            String quId = getQuestionIdByOptionId(optionId);
            // 根据题目ID获取题目类型
            String quType = getQuestionTypeByQuId(quId);
            //初始化局部变量
            int maxOrderId = 0;
            int orderById = 0;

            // 获取选项的orderById和最大orderById
            if (QuType.RADIO.getActionName().equals(quType)) {
                maxOrderId = questionBankMapper.getMaxRadioOrderById(quId);
                orderById = questionBankMapper.getRadioOrderByIdByOptionId(optionId);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                maxOrderId = questionBankMapper.getMaxCheckOrderById(quId);
                orderById = questionBankMapper.getCheckOrderByIdByOptionId(optionId);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                maxOrderId = questionBankMapper.getMaxMultiFillBlankOrderById(quId);
                orderById = questionBankMapper.getMultiFillBlankOrderByIdByOptionId(optionId);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                maxOrderId = questionBankMapper.getMaxScoreOrderById(quId);
                orderById = questionBankMapper.getScoreOrderByIdByOptionId(optionId);
            }

            // 如果当前选项的orderById小于最大值，需要更新其后的orderById
            if (orderById < maxOrderId) {
                if (QuType.RADIO.getActionName().equals(quType)) {
                    questionBankMapper.updateRadioOptionOrderByIdAfterDeletion(quId, orderById);
                } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                    questionBankMapper.updateCheckOptionOrderByIdAfterDeletion(quId, orderById);
                } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                    questionBankMapper.updateMultiFillBlankOptionOrderByIdAfterDeletion(quId, orderById);
                } else if (QuType.SCORE.getActionName().equals(quType)) {
                    questionBankMapper.updateScoreOptionOrderByIdAfterDeletion(quId, orderById);
                }
            }

            // 删除指定的选项
            if (QuType.RADIO.getActionName().equals(quType)) {
                questionBankMapper.deleteRadioOption(optionId);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                questionBankMapper.deleteCheckOption(optionId);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                questionBankMapper.deleteMultiFillBlankOption(optionId);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                questionBankMapper.deleteScoreOption(optionId);
            }

            return AjaxResult.success("选项删除成功");
        } catch (Exception e) {
            return AjaxResult.error("选项删除失败: " + e.getMessage());
        }
    }
    /**
    public void deleteOption(String optionId) {
        // 根据选项ID获取题目ID
        String quId = getQuestionIdByOptionId(optionId);
        // 根据题目ID获取题目类型
        String quType = getQuestionTypeByQuId(quId);
        //初始化局部变量
        int maxOrderId = 0;
        int orderById = 0;

        // 获取选项的orderById和最大orderById
        if (QuType.RADIO.getActionName().equals(quType)) {
            maxOrderId = questionBankMapper.getMaxRadioOrderById(quId);
            orderById = questionBankMapper.getRadioOrderByIdByOptionId(optionId);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            maxOrderId = questionBankMapper.getMaxCheckOrderById(quId);
            orderById = questionBankMapper.getCheckOrderByIdByOptionId(optionId);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            maxOrderId = questionBankMapper.getMaxMultiFillBlankOrderById(quId);
            orderById = questionBankMapper.getMultiFillBlankOrderByIdByOptionId(optionId);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            maxOrderId = questionBankMapper.getMaxScoreOrderById(quId);
            orderById = questionBankMapper.getScoreOrderByIdByOptionId(optionId);
        }

        // 如果当前选项的orderById小于最大值，需要更新其后的orderById
        if (orderById < maxOrderId) {
            if (QuType.RADIO.getActionName().equals(quType)) {
                questionBankMapper.updateRadioOptionOrderByIdAfterDeletion(quId, orderById);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                questionBankMapper.updateCheckOptionOrderByIdAfterDeletion(quId, orderById);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                questionBankMapper.updateMultiFillBlankOptionOrderByIdAfterDeletion(quId, orderById);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                questionBankMapper.updateScoreOptionOrderByIdAfterDeletion(quId, orderById);
            }
        }

        // 删除指定的选项
        if (QuType.RADIO.getActionName().equals(quType)) {
            questionBankMapper.deleteRadioOption(optionId);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            questionBankMapper.deleteCheckOption(optionId);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            questionBankMapper.deleteMultiFillBlankOption(optionId);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            questionBankMapper.deleteScoreOption(optionId);
        }
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateQuestion(String quId, String quName, String quDescription) {
        try {
            QuestionBankModel question = new QuestionBankModel();
            question.setQuId(quId);
            question.setQuName(quName);
            question.setQuDescription(quDescription);
            questionBankMapper.updateQuestionByNameAndDescription(question);
            return AjaxResult.success("题目信息更新成功");
        } catch (Exception e) {
            return AjaxResult.error("题目信息更新失败: " + e.getMessage());
        }
    }
    /**
    public void updateQuestion(String quId, String quName, String quDescription) {
        QuestionBankModel question = new QuestionBankModel();
        question.setQuId(quId);
        question.setQuName(quName);
        question.setQuDescription(quDescription);
        questionBankMapper.updateQuestionByNameAndDescription(question);
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateOption(String optionId, String optionName) {
        try {
            // 根据选项ID获取题目ID
            String quId = getQuestionIdByOptionId(optionId);
            // 根据题目ID获取题目类型
            String quType = getQuestionTypeByQuId(quId);

            // 根据题目类型选择更新选项方法
            if (QuType.RADIO.getActionName().equals(quType)) {
                RadioQuestionModel radioOption = new RadioQuestionModel();
                radioOption.setRadioId(optionId);
                radioOption.setOptionName(optionName);
                questionBankMapper.updateRadioOption(radioOption);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                CheckQuestionModel checkOption = new CheckQuestionModel();
                checkOption.setId(optionId);
                checkOption.setOptionName(optionName);
                questionBankMapper.updateCheckOption(checkOption);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                MultiFillBlankQuestionModel multiFillBlankOption = new MultiFillBlankQuestionModel();
                multiFillBlankOption.setMultiFillBlankQuId(optionId);
                multiFillBlankOption.setOptionName(optionName);
                questionBankMapper.updateMultiFillBlankOption(multiFillBlankOption);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                ScoreQuestionModel scoreOption = new ScoreQuestionModel();
                scoreOption.setScoreQuId(optionId);
                scoreOption.setOptionName(optionName);
                questionBankMapper.updateScoreOption(scoreOption);
            }
            return AjaxResult.success("选项更新成功");
        } catch (Exception e) {
            return AjaxResult.error("选项更新失败: " + e.getMessage());
        }
    }
    /**
    public void updateOption(String optionId, String optionName) {
        // 根据选项ID获取题目ID
        String quId = getQuestionIdByOptionId(optionId);
        // 根据题目ID获取题目类型
        String quType = getQuestionTypeByQuId(quId);

        // 根据题目类型选择更新选项方法
        if (QuType.RADIO.getActionName().equals(quType)) {
            RadioQuestionModel radioOption = new RadioQuestionModel();
            radioOption.setRadioId(optionId);
            radioOption.setOptionName(optionName);
            questionBankMapper.updateRadioOption(radioOption);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            CheckQuestionModel checkOption = new CheckQuestionModel();
            checkOption.setId(optionId);
            checkOption.setOptionName(optionName);
            questionBankMapper.updateCheckOption(checkOption);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            MultiFillBlankQuestionModel multiFillBlankOption = new MultiFillBlankQuestionModel();
            multiFillBlankOption.setMultiFillBlankQuId(optionId);
            multiFillBlankOption.setOptionName(optionName);
            questionBankMapper.updateMultiFillBlankOption(multiFillBlankOption);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            ScoreQuestionModel scoreOption = new ScoreQuestionModel();
            scoreOption.setScoreQuId(optionId);
            scoreOption.setOptionName(optionName);
            questionBankMapper.updateScoreOption(scoreOption);
        }
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult getQuestionsByBankCode(String quBankCode) {
        try {
            List<QuestionBankModel> questions = questionBankMapper.selectQuestionsByBankCode(quBankCode);
            return AjaxResult.success(questions);
        } catch (Exception e) {
            return AjaxResult.error("获取题目失败: " + e.getMessage());
        }
    }
    /**
    public List<QuestionBankModel> getQuestionsByBankCode(String quBankCode) {
        return questionBankMapper.selectQuestionsByBankCode(quBankCode);
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult getOptionsByQuestionId(String quId) {
        try {
            // 根据题目ID获取题目类型
            String quType = getQuestionTypeByQuId(quId);

            // 根据题目类型选择查询选项方法
            List<?> options = null;
            if (QuType.RADIO.getActionName().equals(quType)) {
                options = questionBankMapper.selectRadioOptionsByQuestionId(quId);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                options = questionBankMapper.selectCheckOptionsByQuestionId(quId);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                options = questionBankMapper.selectMultiFillBlankOptionsByQuestionId(quId);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                options = questionBankMapper.selectScoreOptionsByQuestionId(quId);
            }
            return AjaxResult.success(options);
        } catch (Exception e) {
            return AjaxResult.error("获取选项失败: " + e.getMessage());
        }
    }
    /**
    public List<?> getOptionsByQuestionId(String quId) {
        // 根据题目ID获取题目类型
        String quType = getQuestionTypeByQuId(quId);

        // 根据题目类型选择查询选项方法
        if (QuType.RADIO.getActionName().equals(quType)) {
            return questionBankMapper.selectRadioOptionsByQuestionId(quId);
        } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
            return questionBankMapper.selectCheckOptionsByQuestionId(quId);
        } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
            return questionBankMapper.selectMultiFillBlankOptionsByQuestionId(quId);
        } else if (QuType.SCORE.getActionName().equals(quType)) {
            return questionBankMapper.selectScoreOptionsByQuestionId(quId);
        }
        return null;
    }
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult searchQuestions(String quBankCode, String quName, String quDescription) {
        try {
            List<QuestionBankModel> questions = questionBankMapper.searchQuestions(quBankCode, quName, quDescription);
            return AjaxResult.success(questions);
        } catch (Exception e) {
            return AjaxResult.error("搜索题目失败: " + e.getMessage());
        }
    }
    /**
    public List<QuestionBankModel> searchQuestions(String quBankCode, String quName, String quDescription) {
        return questionBankMapper.searchQuestions(quBankCode, quName, quDescription);
    }
     */

    @Override
    public AjaxResult countQuestionsByBankCode(String quBankCode) {
        try {
            int count = questionBankMapper.countQuestionsByBankCode(quBankCode);
            return AjaxResult.success(count);
        } catch (Exception e) {
            return AjaxResult.error("统计题目数量失败: " + e.getMessage());
        }
    }
    /**
    public int countQuestionsByBankCode(String quBankCode) {
        return questionBankMapper.countQuestionsByBankCode(quBankCode);
    }
     */

    //根据题目ID获取题目类型
    private String getQuestionTypeByQuId(String quId) {
        return questionBankMapper.getQuestionTypeByQuId(quId);
    }

    //根据选项ID获取题目ID
    private String getQuestionIdByOptionId(String optionId) {
        return questionBankMapper.getQuestionIdByOptionId(optionId);
    }

}

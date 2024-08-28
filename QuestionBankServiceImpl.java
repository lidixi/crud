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
    public AjaxResult addQuestions(List<QuestionBankModel> questions) {
        try {
            // 遍历每个题目
            for (QuestionBankModel question : questions) {
                // 设置新题目的ID
                question.setQuId(UUID.randomUUID().toString());
                questionBankMapper.insertQuestion(question); // 插入题目
                // 根据quType选择插入对应的选项
                String quType = question.getQuType();
                if (QuType.RADIO.getActionName().equals(quType)) {
                    // 为选项生成序号
                    generateRadioOptionIds(question.getQuRadios(), question.getQuId());
                    // 插入新选项
                    questionBankMapper.batchInsertRadioOptions(question.getQuRadios());
                } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                    generateCheckOptionIds(question.getQuCheck(), question.getQuId());
                    questionBankMapper.batchInsertCheckOptions(question.getQuCheck());
                } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                    generateMultiFillBlankOptionIds(question.getQuMultiFillBlank(), question.getQuId());
                    questionBankMapper.batchInsertMultiFillBlankOptions(question.getQuMultiFillBlank());
                } else if (QuType.SCORE.getActionName().equals(quType)) {
                    generateScoreOptionIds(question.getQuScore(), question.getQuId());
                    questionBankMapper.batchInsertScoreOptions(question.getQuScore());
                }
            }
            return AjaxResult.success("题目及选项添加成功");
        } catch (Exception e) {
            return AjaxResult.error("题目及选项添加失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateQuestions(List<QuestionBankModel> questions) {
        try {
            // 遍历每个题目
            for (QuestionBankModel question : questions) {
                // 更新题目信息
                questionBankMapper.updateQuestionByNameAndDescription(question);
                // 获取题目信息
                String quId = question.getQuId();
                String quType = question.getQuType();
                // 根据quType删除旧的选项并插入新的选项
                if (QuType.RADIO.getActionName().equals(quType)) {
                    // 删除旧选项
                    questionBankMapper.deleteRadioOptionsByQuestionId(quId);
                    // 为选项生成序号
                    generateRadioOptionIds(question.getQuRadios(), question.getQuId());
                    // 插入新选项
                    questionBankMapper.batchInsertRadioOptions(question.getQuRadios());
                } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                    questionBankMapper.deleteCheckOptionsByQuestionId(quId);
                    generateCheckOptionIds(question.getQuCheck(), question.getQuId());
                    questionBankMapper.batchInsertCheckOptions(question.getQuCheck());
                } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                    questionBankMapper.deleteMultiFillBlankOptionsByQuestionId(quId);
                    generateMultiFillBlankOptionIds(question.getQuMultiFillBlank(), question.getQuId());
                    questionBankMapper.batchInsertMultiFillBlankOptions(question.getQuMultiFillBlank());
                } else if (QuType.SCORE.getActionName().equals(quType)) {
                    questionBankMapper.deleteScoreOptionsByQuestionId(quId);
                    generateScoreOptionIds(question.getQuScore(), question.getQuId());
                    questionBankMapper.batchInsertScoreOptions(question.getQuScore());
                }
            }
            return AjaxResult.success("题目及选项更新成功");
        } catch (Exception e) {
            return AjaxResult.error("题目及选项更新失败: " + e.getMessage());
        }
    }

    // 为单选题选项生成主键和排序序号
    private void generateRadioOptionIds(List<RadioQuestionModel> options, String quId) {
        for (int i = 0; i < options.size(); i++) {
            RadioQuestionModel option = options.get(i);
            option.setRadioId(UUID.randomUUID().toString()); // 生成唯一ID
            option.setBelongQuId(quId); // 设置选项的所属题目ID
            option.setRadioOrderById(i + 1); // 从1开始排序
        }
    }

    // 为多选题选项生成主键和排序序号
    private void generateCheckOptionIds(List<CheckQuestionModel> options, String quId) {
        for (int i = 0; i < options.size(); i++) {
            CheckQuestionModel option = options.get(i);
            option.setId(UUID.randomUUID().toString()); // 生成唯一ID
            option.setQuId(quId); // 设置选项的所属题目ID
            option.setOrderById(i + 1); // 从1开始排序
        }
    }

    // 为多项填空题选项生成主键和排序序号
    private void generateMultiFillBlankOptionIds(List<MultiFillBlankQuestionModel> options, String quId) {
        for (int i = 0; i < options.size(); i++) {
            MultiFillBlankQuestionModel option = options.get(i);
            option.setMultiFillBlankQuId(UUID.randomUUID().toString()); // 生成唯一ID
            option.setQuId(quId); // 设置选项的所属题目ID
            option.setOrderById(i + 1); // 从1开始排序
        }
    }

    // 为评分题选项生成主键和排序序号
    private void generateScoreOptionIds(List<ScoreQuestionModel> options, String quId) {
        for (int i = 0; i < options.size(); i++) {
            ScoreQuestionModel option = options.get(i);
            option.setScoreQuId(UUID.randomUUID().toString()); // 生成唯一ID
            option.setQuId(quId); // 设置选项的所属题目ID
            option.setOrderById(i + 1); // 从1开始排序
        }
    }

    @Override
    public AjaxResult getQuestionsByBankCode(String quBankCode) {
        try {
            List<QuestionBankModel> questions = questionBankMapper.selectQuestionsByBankCode(quBankCode);
            for (QuestionBankModel question : questions) {
                String quId = question.getQuId();
                String quType = question.getQuType();

                if (QuType.RADIO.getActionName().equals(quType)) {
                    question.setQuRadios(questionBankMapper.selectRadioOptionsByQuestionId(quId));
                } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                    question.setQuCheck(questionBankMapper.selectCheckOptionsByQuestionId(quId));
                } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                    question.setQuMultiFillBlank(questionBankMapper.selectMultiFillBlankOptionsByQuestionId(quId));
                } else if (QuType.SCORE.getActionName().equals(quType)) {
                    question.setQuScore(questionBankMapper.selectScoreOptionsByQuestionId(quId));
                }
            }
            return AjaxResult.success(questions);
        } catch (Exception e) {
            return AjaxResult.error("获取题目及选项失败: " + e.getMessage());
        }
    }

    @Override
    public AjaxResult searchQuestions(String quBankCode, String search) {
        try {
            List<QuestionBankModel> questions = questionBankMapper.searchQuestions(quBankCode, search);
            for (QuestionBankModel question : questions) {
                String quId = question.getQuId();
                String quType = question.getQuType();

                if (QuType.RADIO.getActionName().equals(quType)) {
                    question.setQuRadios(questionBankMapper.selectRadioOptionsByQuestionId(quId));
                } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                    question.setQuCheck(questionBankMapper.selectCheckOptionsByQuestionId(quId));
                } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                    question.setQuMultiFillBlank(questionBankMapper.selectMultiFillBlankOptionsByQuestionId(quId));
                } else if (QuType.SCORE.getActionName().equals(quType)) {
                    question.setQuScore(questionBankMapper.selectScoreOptionsByQuestionId(quId));
                }
            }
            return AjaxResult.success(questions);
        } catch (Exception e) {
            return AjaxResult.error("搜索题目及选项失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteQuestionAndOptions(String quId) {
        try {
            String quType = questionBankMapper.getQuestionTypeByQuId(quId);
            // 获取题目所属题库的键值
            String quBankCode = questionBankMapper.getQuBankCodeByQuId(quId);
            // 获取被删除题目的排序序号
            int deletedOrderById = questionBankMapper.getOrderByIdByQuId(quId);
            // 根据quType删除选项
            if (QuType.RADIO.getActionName().equals(quType)) {
                questionBankMapper.deleteRadioOptionsByQuestionId(quId);
            } else if (QuType.CHECKBOX.getActionName().equals(quType)) {
                questionBankMapper.deleteCheckOptionsByQuestionId(quId);
            } else if (QuType.MULTIFILLBLANK.getActionName().equals(quType)) {
                questionBankMapper.deleteMultiFillBlankOptionsByQuestionId(quId);
            } else if (QuType.SCORE.getActionName().equals(quType)) {
                questionBankMapper.deleteScoreOptionsByQuestionId(quId);
            } 
            // 删除题目
            questionBankMapper.deleteQuestion(quId);
            // 更新排序序号：如果删除的题目的 orderById 不是最大值，需要更新其他题目的排序序号
            int maxOrderById = questionBankMapper.getMaxOrderByIdByQuBankCode(quBankCode);
            if (deletedOrderById < maxOrderById) {
                questionBankMapper.updateOrderByIdAfterDeletion(quBankCode, deletedOrderById);
            }
            return AjaxResult.success("题目及选项删除成功");
        } catch (Exception e) {
            return AjaxResult.error("题目及选项删除失败: " + e.getMessage());
        }
    }

    @Override
    public AjaxResult countQuestionsByBankCode(String quBankCode) {
        try {
            int count = questionBankMapper.countQuestionsByBankCode(quBankCode);
            return AjaxResult.success(count);
        } catch (Exception e) {
            return AjaxResult.error("统计题目数量失败: " + e.getMessage());
        }
    }
}

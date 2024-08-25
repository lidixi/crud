package com.dyzc.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = "handler")
public class QuestionBankModel implements Serializable {

//题库类型
    private String quBankName;
//题库键值
    private String quBankCode;
//题目描述
    private String quDescription;

    //    题目id
    private String quId;

    //    题目标题
    private String quName;

    //    题目类型（以quType枚举类中的分类为准）
    private String quType;

    //    创建时间
    private String quCreateDate;

    //    排序序号
    private int orderById;

    //    单选题选项
    private List<RadioQuestionModel> quRadios;

    //    多选题选项
    private List<CheckQuestionModel> quCheck;

    //    多项填空题选项
    private List<MultiFillBlankQuestionModel> quMultiFillBlank;

    //    评分题选项
    private List<ScoreQuestionModel> quScore;

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getQuBankName() {
        return quBankName;
    }

    public void setQuBankName(String quBankName) {
        this.quBankName = quBankName;
    }

    public String getQuBankCode() {
        return quBankCode;
    }

    public void setQuBankCode(String quBankCode) {
        this.quBankCode = quBankCode;
    }

    public String getQuDescription() {
        return quDescription;
    }

    public void setQuDescription(String quDescription) {
        this.quDescription = quDescription;
    }

    public String getQuName() {
        return quName;
    }

    public void setQuName(String quName) {
        this.quName = quName;
    }

    public String getQuType() {
        return quType;
    }

    public void setQuType(String quType) {
        this.quType = quType;
    }

    public String getQuCreateDate() {
        return quCreateDate;
    }

    public void setQuCreateDate(String quCreateDate) {
        this.quCreateDate = quCreateDate;
    }

    public int getOrderById() {
        return orderById;
    }

    public void setOrderById(int orderById) {
        this.orderById = orderById;
    }

    public List<RadioQuestionModel> getQuRadios() {
        return quRadios;
    }

    public void setQuRadios(List<RadioQuestionModel> quRadios) {
        this.quRadios = quRadios;
    }

    public List<CheckQuestionModel> getQuCheck() {
        return quCheck;
    }

    public void setQuCheck(List<CheckQuestionModel> quCheck) {
        this.quCheck = quCheck;
    }

    public List<MultiFillBlankQuestionModel> getQuMultiFillBlank() {
        return quMultiFillBlank;
    }

    public void setQuMultiFillBlank(List<MultiFillBlankQuestionModel> quMultiFillBlank) {
        this.quMultiFillBlank = quMultiFillBlank;
    }

    public List<ScoreQuestionModel> getQuScore() {
        return quScore;
    }

    public void setQuScore(List<ScoreQuestionModel> quScore) {
        this.quScore = quScore;
    }

    @Override
    public String toString() {
        return "{" +
                "\"quId\":\"" + quId + '\"' +
                ", \"quName\":\"" + quName.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"quType\":\"" + quType + '\"' +
                ", \"quCreateDate\":\"" + quCreateDate + '\"' +
                ", \"orderById\":\"" + orderById + '\"' +
                ", \"quRadios\":" + quRadios.toString()+
                ", \"quCheck\":" + quCheck.toString()+
                ", \"quMultiFillBlank\":" + quMultiFillBlank.toString()+
                ", \"quScore\":" + quScore.toString()+
                '}';
    }
}




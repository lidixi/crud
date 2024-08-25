package com.dyzc.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "handler")
public class MultiFillBlankQuestionModel implements Serializable {

    private String multiFillBlankQuId;

    private String quId;

    private String isNote;

    private int isRequiredFill;

    private String optionName;

    private String optionTitle;

    private int orderById;

    private int visibility;

    private String checkType;

    public String getMultiFillBlankQuId() {
        return multiFillBlankQuId;
    }

    public void setMultiFillBlankQuId(String multiFillBlankQuId) {
        this.multiFillBlankQuId = multiFillBlankQuId;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getIsNote() {
        return isNote;
    }

    public void setIsNote(String isNote) {
        this.isNote = isNote;
    }

    public int getIsRequiredFill() {
        return isRequiredFill;
    }

    public void setIsRequiredFill(int isRequiredFill) {
        this.isRequiredFill = isRequiredFill;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public int getOrderById() {
        return orderById;
    }

    public void setOrderById(int orderById) {
        this.orderById = orderById;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    @Override
    public String toString() {
        return "{" +
                "\"multiFillBlankQuId\":\"" + multiFillBlankQuId + '\"' +
                ", \"quId\":\"" + quId + '\"' +
                ", \"checkType\":\"" + checkType + '\"' +
                ", \"isNote\":\"" + isNote + '\"' +
                ", \"isRequiredFill\":\"" + isRequiredFill + '\"' +
                ", \"optionName\":\"" + optionName.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"optionTitle\":\"" + optionTitle + '\"' +
                ", \"orderById\":\"" + orderById + '\"' +
                ", \"visibility\":\"" + visibility + '\"' +
                '}';
    }
}

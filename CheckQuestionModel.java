package com.dyzc.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "handler")
public class CheckQuestionModel implements Serializable {

    private String id;

    private String quId;

    private String checkType;

    private String isNote;

    private String isRequiredFill;

    private String optionName;

    private String optionTitle;

    private int orderById;

    private String visibility;

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getIsNote() {
        return isNote;
    }

    public void setIsNote(String isNote) {
        this.isNote = isNote;
    }

    public String getIsRequiredFill() {
        return isRequiredFill;
    }

    public void setIsRequiredFill(String isRequiredFill) {
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
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

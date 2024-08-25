package com.dyzc.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "handler")
public class ScoreQuestionModel implements Serializable {

    private String scoreQuId;

    private String quId;

    private String optionName;

    private String optionTitle;

    private int orderById;

    private int visibility;

    public String getScoreQuId() {
        return scoreQuId;
    }

    public void setScoreQuId(String scoreQuId) {
        this.scoreQuId = scoreQuId;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
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

    @Override
    public String toString() {
        return "{" +
                "\"scoreQuId\":\"" + scoreQuId + '\"' +
                ", \"quId\":\"" + quId + '\"' +
                ", \"optionName\":\"" + optionName.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"optionTitle\":\"" + optionTitle + '\"' +
                ", \"visibility\":\"" + visibility + '\"' +
                ", \"orderById\":\"" + orderById + '\"' +
                '}';
    }
}

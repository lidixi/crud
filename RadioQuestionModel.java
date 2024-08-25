package com.dyzc.survey.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "handler")
public class RadioQuestionModel implements Serializable {

//    单选题选项主键id
    private String radioId;

//    当前选项所属题型id
    private String belongQuId;

//    选项内容
    private String optionName;

//    校验类型  默认为NO
    private String checkType;

//    选项标题
    private String optionTitle;

//    排序id
    private int radioOrderById;

//    是否显示  0不显示  1显示
    private int radioVisibility;

//    选项后是否添加填空
    private int isNote;

    public int getIsNote() {
        return isNote;
    }

    public void setIsNote(int isNote) {
        this.isNote = isNote;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId;
    }

    public String getBelongQuId() {
        return belongQuId;
    }

    public void setBelongQuId(String belongQuId) {
        this.belongQuId = belongQuId;
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

    public int getRadioOrderById() {
        return radioOrderById;
    }

    public void setRadioOrderById(int radioOrderById) {
        this.radioOrderById = radioOrderById;
    }

    public int getRadioVisibility() {
        return radioVisibility;
    }

    public void setRadioVisibility(int radioVisibility) {
        this.radioVisibility = radioVisibility;
    }

    @Override
    public String toString() {
            return "{" +
                    "\"radioId\":\"" + radioId + '\"' +
            ", \"belongQuId\":\"" + belongQuId + '\"' +
            ", \"optionName\":\"" + optionName.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
            ", \"optionTitle\":\"" + optionTitle + '\"' +
            ", \"radioOrderById\":\"" + radioOrderById + '\"' +
            ", \"radioVisibility\":\"" + radioVisibility + '\"' +
            ", \"checkType\":\"" + checkType + '\"' +
            ", \"isNote\":\"" + isNote + '\"' +
//                    TODO 一些未知的前端需要的参数，在此处加入，先回显
                    ", \"optionValue\":\"" + '\"' +
                    ", \"optionId\":\"" + '\"' +
                    ", \"isNote\":\"" + '\"' +
                    ", \"checkType\":\"" + '\"' +
                    ", \"isRequiredFill\":\"" + '\"' +

            '}';
}
}

package com.dyzc.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "handler")
public class KnowledgeBaseModel implements Serializable {

    //  文章主键
    private String id;

    //  标题
    private String title;

    //  摘要
    private String summary;

    //  正文
    private String content;

    //  创建人
    private String createdPerson;

    //  创建日期
    private String createdDate;

    //  状态，已发布=1， 草稿=0
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedPerson() {
        return createdPerson;
    }

    public void setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"title\":\"" + title.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"summary\":\"" + summary.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"content\":\"" + content.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"createdPerson\":" + createdPerson.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"createdDate\":" + createdDate.replaceAll("\"", "\"").replaceAll("\\\\","\\\\")  + '\"' +
                ", \"status\":" + status + '\"' +
                '}';
    }
}

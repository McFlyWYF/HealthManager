package com.eric.cookbook.bean;

import java.io.Serializable;


public class SearchHistoryBean implements Serializable{

    private String time;
    private String content;

    public SearchHistoryBean() {
    }

    public SearchHistoryBean(String time, String content) {
        this.content = content;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.springtest.model;

public class InfoMessage {
    private String fromname;
    private String toname;
    private String content;
    private int state;  //状态，为 1表示已读消息，为 0表示未读

    public InfoMessage(String fromname, String toname, String content, int state) {
        this.fromname = fromname;
        this.toname = toname;
        this.content = content;
        this.state = state;
    }

    public InfoMessage() { }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "InfoMessage{" +
                "fromname='" + fromname + '\'' +
                ", toname='" + toname + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }
}

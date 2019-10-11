package com.mrl.inote.note.entity;

import com.mrl.inote.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    private String content;
    private String knowledge;
    private String user;
    private String userName;

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "knowledge_id")
    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    @Column(name = "user_id")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_name")


    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", knowledge='" + knowledge + '\'' +
                ", user='" + user + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", id='" + id + '\'' +
                '}';
    }
}

package com.mrl.inote.note.entity;

import com.mrl.inote.common.entity.BaseEntity_NID;

import javax.persistence.*;

@Entity
@Table(name = "knowledge")
@IdClass(KnowledgeKey.class)
public class Knowledge extends BaseEntity_NID {

    private String id;
    private String title;
    private String content;
    private int view_time;
    private int important;
    private int difficult;
    private String parentId;
    private String user;
    private String imgUrl;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "view_time")
    public int getView_time() {
        return view_time;
    }

    public void setView_time(int view_time) {
        this.view_time = view_time;
    }

    @Column(name = "important")
    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    @Column(name = "difficult")
    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "user_id")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", view_time=" + view_time +
                ", important=" + important +
                ", difficult=" + difficult +
                ", parentId='" + parentId + '\'' +
                ", user='" + user + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

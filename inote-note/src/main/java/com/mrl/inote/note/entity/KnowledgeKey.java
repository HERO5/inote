package com.mrl.inote.note.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class KnowledgeKey implements Serializable {

    private String id;
    private String user;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "user_id")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "KnowledgeKey{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

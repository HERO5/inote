package com.mrl.inote.note.dao;

import com.mrl.inote.note.entity.Comment;

import java.util.List;

public interface CommentDaoCustom {

    public List<Comment> getComment(Comment comment);
}

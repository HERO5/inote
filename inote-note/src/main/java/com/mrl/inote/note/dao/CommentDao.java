package com.mrl.inote.note.dao;

import com.mrl.inote.common.dao.CommonDao;
import com.mrl.inote.note.entity.Comment;

public interface CommentDao extends CommentDaoCustom, CommonDao<Comment, String> {
}

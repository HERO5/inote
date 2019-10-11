package com.mrl.inote.note.service;

import com.mrl.inote.common.service.CommonService;
import com.mrl.inote.note.dao.CommentDao;
import com.mrl.inote.note.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends CommonService<Comment, String> {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        super.setCommonDao(commentDao);
    }

    public List<Comment> getCommentList(Comment comment){
        return commentDao.getComment(comment);
    }

    public void saveAll(List<Comment> comments){
        if(comments!=null&&comments.size()>0){
            for(Comment knowledge : comments){
                try{
                    this.save(knowledge);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.mrl.inote.note.dao;

import com.mrl.inote.common.dao.CustomBaseSqlDaoImpl;
import com.mrl.inote.note.entity.Comment;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl extends CustomBaseSqlDaoImpl implements CommentDaoCustom {

    @Override
    public List<Comment> getComment(Comment comment) {
        Map<String,Object> map = new HashMap<String,Object>();
        StringBuilder hql = new StringBuilder();
        hql.append("select c from Comment c where 1=1 ");
        if(comment != null){
            if(StringUtils.isNotBlank(comment.getContent())){
                hql.append(" and c.content like  :content ");
                map.put("content", "%"+comment.getContent()+"%");
            }
            if(StringUtils.isNotBlank(comment.getUser())){
                hql.append(" and c.user = :user ");
                map.put("user", comment.getUser());
            }
            if(StringUtils.isNotBlank(comment.getKnowledge())){
                hql.append(" and c.knowledge = :knowledge ");
                map.put("knowledge", comment.getKnowledge());
            }
        }
        hql.append(" order by c.createDate desc ");
        return this.queryByMapParams(hql.toString(), map);
    }
}

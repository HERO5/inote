package com.mrl.inote.note.dao;

import com.mrl.inote.common.dao.CustomBaseSqlDaoImpl;
import com.mrl.inote.note.entity.Knowledge;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeDaoImpl extends CustomBaseSqlDaoImpl implements KnowledgeDaoCustom {

    @Override
    public List<Knowledge> getKnowledge(Knowledge knowledge) {
        Map<String,Object> map = new HashMap<String,Object>();
        StringBuilder hql = new StringBuilder();
        hql.append("select k from Knowledge k where 1=1 ");
        if(knowledge != null){
            if(StringUtils.isNotBlank(knowledge.getTitle())){
                hql.append(" and k.title like  :title ");
                map.put("title", "%"+knowledge.getTitle()+"%");
            }
            if(StringUtils.isNotBlank(knowledge.getContent())){
                hql.append(" and k.content like  :content ");
                map.put("content", "%"+knowledge.getContent()+"%");
            }
            if(StringUtils.isNotBlank(knowledge.getUser())){
                hql.append(" and k.user = :user ");
                map.put("user", knowledge.getUser());
            }
        }
        hql.append(" order by k.createDate desc ");
        return this.queryByMapParams(hql.toString(), map);
    }

}

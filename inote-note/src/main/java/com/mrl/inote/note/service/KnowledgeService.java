package com.mrl.inote.note.service;

import com.mrl.inote.common.service.CommonService;
import com.mrl.inote.note.dao.KnowledgeDao;
import com.mrl.inote.note.entity.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService extends CommonService<Knowledge, String> {

    @Autowired
    private KnowledgeDao knowledgeDao;

    @Autowired
    public void setKnowledgeDao(KnowledgeDao knowledgeDao) {
        super.setCommonDao(knowledgeDao);
    }


    public List<Knowledge> getKnowledgeList(Knowledge knowledge){
        return knowledgeDao.getKnowledge(knowledge);
    }

    public void saveAll(List<Knowledge> knowledges){
        if(knowledges!=null&&knowledges.size()>0){
            for(Knowledge knowledge : knowledges){
                try{
                    this.save(knowledge);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteAll(List<Knowledge> knowledges){
        if(knowledges!=null&&knowledges.size()>0){
            this.delete(knowledges);
        }
    }
}

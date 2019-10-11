package com.mrl.inote.note.dao;

import com.mrl.inote.common.dao.CustomBaseSqlDaoImpl;
import com.mrl.inote.note.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl  extends CustomBaseSqlDaoImpl implements UserDaoCustom {
    @Override
    public List<User> getUserAll() {
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User u where 1=1 ");
        return this.queryForList(hql.toString());
    }

    @Override
    public List<User> getUserByProperty(User user) {
        StringBuilder hql = new StringBuilder();
        Map<String,Object> map = new HashMap<String,Object>();
        hql.append("select u from User u where 1=1 ");
        if(user != null){
            if(StringUtils.isNotBlank(user.getName())){
                hql.append(" and u.name = :name ");
                map.put("name", user.getName());
            }
        }
        return this.queryByMapParams(hql.toString(), map);
    }
}

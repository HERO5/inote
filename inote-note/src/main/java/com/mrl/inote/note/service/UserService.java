package com.mrl.inote.note.service;

import com.mrl.inote.common.service.CommonService;
import com.mrl.inote.note.dao.UserDao;
import com.mrl.inote.note.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CommonService<User, String> {

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        super.setCommonDao(userDao);
    }

    public List<User> getUserAll(){
        return userDao.getUserAll();
    }

    public User getUserByName(String name){
        User user = null;
        if(StringUtils.isNotBlank(name)){
            user = new User();
            user.setName(name);
            List<User> users = userDao.getUserByProperty(user);
            if(users!=null&&users.size()>0){
                user = users.get(0);
            }else{
                user = null;
            }
        }
        return user;
    }

}

package com.mrl.inote.note.dao;

import com.mrl.inote.note.entity.User;

import java.util.List;

public interface UserDaoCustom {

    public List<User> getUserAll();

    public List<User> getUserByProperty(User user);

}

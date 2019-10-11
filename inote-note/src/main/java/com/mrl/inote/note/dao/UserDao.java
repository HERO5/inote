package com.mrl.inote.note.dao;

import com.mrl.inote.common.dao.CommonDao;
import com.mrl.inote.note.entity.User;

public interface UserDao extends UserDaoCustom, CommonDao<User, String> {

}

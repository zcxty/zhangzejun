package com.zcxty.dao;

import java.util.List;

import com.zcxty.model.User;

public interface UserDao {
	int addUser(User user);
	 
    int deleteUserById(long id);
 
    int updateUser(User user);
 
    User queryUserById(long id);
    int getUserCount();
    List<User> queryAllUser();

}
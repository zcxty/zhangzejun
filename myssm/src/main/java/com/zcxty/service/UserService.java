package com.zcxty.service;

import java.util.List;

import com.zcxty.model.User;

public interface UserService {
	int addUser(User user);
	 
    int deleteUserById(long id);
 
    int updateUser(User user);
 
    User queryById(long id);
    int getUserCount();
    List<User> queryAllUser();

}

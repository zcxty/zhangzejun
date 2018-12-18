package com.zcxty.service.impl;

import java.util.List;
import com.zcxty.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcxty.model.User;
import com.zcxty.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserDao userDao;
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public int deleteUserById(long id) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(id);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public User queryById(long id) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(id);
	}

	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllUser();
	}
    public int getUserCount() {
    	return userDao.getUserCount();
    }
}

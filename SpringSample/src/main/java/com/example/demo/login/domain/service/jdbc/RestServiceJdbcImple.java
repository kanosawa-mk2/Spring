package com.example.demo.login.domain.service.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.RestService;

@Service
public class RestServiceJdbcImple implements RestService{

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	@Override
	public boolean insert(User user) {
		int result = dao.insertOne(user);
		return result > 0;
	}

	@Override
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	@Override
	public List<User> selectMany() {
		return dao.selectMany();
	}

	@Override
	public boolean update(User user) {
		int result = dao.updateOne(user);
		return result > 0;
	}

	@Override
	public boolean delete(String userId) {
		int result = dao.deleteOne(userId);
		return result > 0;
	}

}

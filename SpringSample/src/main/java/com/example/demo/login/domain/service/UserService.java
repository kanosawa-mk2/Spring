package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {

	/**
	 * 　Autowiredと一緒にQualifierアノテーションを使用すると、
	 * どのBeanを使用するか指定することができる。
	 * インターフェースを継承したクラスが２つ（UserDaoJdbcImplとUserDaoJdbcImpl2）ある場合は、
	 * Qualifierを付けないといけない。
	 */
	@Autowired
	@Qualifier("UserDaoJdbcImpl4")
	UserDao dao;


	public boolean insert(User user) {
		int rowNumber = dao.insertOne(user);

		return rowNumber > 0;
	}

	public int count() {
		return dao.count();
	}

	public List<User> selectMany(){
		return dao.selectMany();
	}

	public User selectOne(String userId){
		return dao.selectOne(userId);
	}

	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		return rowNumber > 0;
	}

	public boolean deleteOne(String userId) {
		int rowNumber = dao.deleteOne(userId);
		return rowNumber > 0;
	}
}

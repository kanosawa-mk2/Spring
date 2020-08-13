package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;

/**
 * RESTサービス
 *
 */
public interface RestService {

	/**
	 * 登録
	 */
	public boolean insert(User user);

	/**
	 * 検索（単体）
	 */
	public User selectOne(String userId);

	/**
	 * 検索（複数）
	 */
	public List<User> selectMany();

	/**
	 * 更新
	 */
	public boolean update(User user);

	/**
	 * 削除
	 */
	public boolean delete(String userId);
}

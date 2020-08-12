package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

// Repositoryアノテーションの引数はBean名をセットしている。
// Bean名をセットすることで、@Autowiredする際に、どのクラスを使用するか指定できる。
@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	/**
	 * Userテーブルの件数を取得
	 */
	@Override
	public int count() throws DataAccessException {

		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		return count;
	}

	/**
	 * Userテーブルにデータを1件insert
	 */
	@Override
	public int insertOne(User user) throws DataAccessException {

		int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)",
				user.getUserId(),
				user.getPassword(),
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getRole());

		return rowNumber;
	}

	/**
	 * Userテーブルのデータを1件取得
	 */
	@Override
	public User selectOne(String userId) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = ?", userId);

		User user = new User();

		user.setUserId((String) map.get("user_id")); //ユーザーID
		user.setPassword((String) map.get("password")); //パスワード
		user.setUserName((String) map.get("user_name")); //ユーザー名
		user.setBirthday((Date) map.get("birthday")); //誕生日
		user.setAge((Integer) map.get("age")); //年齢
		user.setMarriage((Boolean) map.get("marriage")); //結婚ステータス
		user.setRole((String) map.get("role")); //ロール

		return user;
	}

	/**
	 * Userテーブルの全データを取得
	 */
	@Override
	public List<User> selectMany() throws DataAccessException {

		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
		List<User> userList = new ArrayList<>();

		for (Map<String, Object> map : getList) {

			User user = new User();

			user.setUserId((String) map.get("user_id")); //ユーザーID
			user.setPassword((String) map.get("password")); //パスワード
			user.setUserName((String) map.get("user_name")); //ユーザー名
			user.setBirthday((Date) map.get("birthday")); //誕生日
			user.setAge((Integer) map.get("age")); //年齢
			user.setMarriage((Boolean) map.get("marriage")); //結婚ステータス
			user.setRole((String) map.get("role")); //ロール

			userList.add(user);
		}

		return userList;
	}

	/**
	 * Userテーブルを1件更新
	 */
	@Override
	public int updateOne(User user) throws DataAccessException {
		//１件更新
		int rowNumber = jdbc.update("UPDATE M_USER"
				+ " SET"
				+ " password = ?,"
				+ " user_name = ?,"
				+ " birthday = ?,"
				+ " age = ?,"
				+ " marriage = ?"
				+ " WHERE user_id = ?",
				user.getPassword(),
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getUserId());

		//トランザクション確認のため、わざと例外をthrowする
		//        if (rowNumber > 0) {
		//            throw new DataAccessException("トランザクションテスト") {
		//            };
		//        }

		return rowNumber;
	}

	/**
	 * Userテーブルを1件削除
	 */
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE M_USER WHERE user_id = ?",userId);

		return rowNumber;
	}

	/**
	 * Userテーブルの全データをCSVに出力
	 */
	@Override
	public void userCsvOut() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ

	}

}

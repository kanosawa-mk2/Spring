package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public User selectOne(String userId) throws DataAccessException {

		String sql = "SELECT * FROM m_user WHERE user_id = ?";

		// BeanPropertyRowMapperでは、データベースから取得してきたカラム名と
		// 同一のフィールド名がクラスにあれば、自動でマッピングをする。
		// つまり、RowMapperのようにどのカラムとどのフィールドを一致させるか、
		// いちいち用意する必要がない。
		// ただし、自動でマッピングするためには、
		// 以下のようなカラム名とフィールド名にする必要がある。
		// ■カラム名は単語をアンダースコアで区切る（スネークケース）例：user_id
		// ■フィールド名は２つ目の単語から大文字にする（キャメルケース）例：StringuserId;
		// もしも、カラム名がアンダースコアで区切られていなければ、
		// AS句を使用して別名でもマッピングが可能。
		// このように、カラム名とフィールド名が一致するのであれば、
		// BeanPropertyRowMapperを使うと便利。

		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

		return jdbc.queryForObject(sql, rowMapper, userId);
	}

	/**
	 * Userテーブルの全データを取得
	 */
	@Override
	public List<User> selectMany() throws DataAccessException {

		String sql = "SELECT * FROM m_user";

		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

		return jdbc.query(sql, rowMapper);
	}
}

package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// リポジトリークラスには@Repositoryを付ける
@Repository
public class HelloRepository {

	// JdbcTemplateはSpringのJDBC接続用のクラス
	// @Autowiredはインスタンスを生成している(DIの一環)
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> findOne(int id) {

		String query = "SELECT"
				+ " employee_id,"
				+ " employee_name,"
				+ " age"
				+ " FROM employee"
				+ " WHERE employee_id = ?";

		// 検索実行
		Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);

		return employee;
	}
}

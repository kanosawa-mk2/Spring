package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

/**
 * テスト用のクラスには、@RunWith(SpringRunner.class)と@SprintBootTestの２つのアノテーションを付ける。
 * ・@RunWith(SpringRunner.class)　
 *   RunWithアノテーションは、テストをどのクラスで実行するか指定できます。
 *   SpringRunnerクラスは、Spring用のJUnitを使えるクラスです。
 * ・@SprintBootTest
 *   このアノテーションを付けると、SpringBootを起動してからテストを始めてくれます。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	/**
	 * 通常のテスト
	 */
	@Test
	public void countTest1() {
		assertEquals(dao.count(), 2);
	}

	/**
	 * 任意のSQL実行後にテスト
	 * @Sqlアノテーションを使用すると、そのSQLを実行した後の状態でテストされる。
	 * ただし、@Sqlに記載されているSQLはそのメソッド内だけで有効。
	 */
	@Test
	@Sql("/testdata.sql")
	public void countTest2() {
		assertEquals(dao.count(), 3);
	}
}

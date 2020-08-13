package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.service.UserService;

/**
 * Springが用意するMockitoを使って、メソッドの戻り値を任意の値に変更することができる。
 * まずは、モックとして使用するBeanに@MockBeanアノテーションを付ける。
 * 　その後、以下のコード部分でメソッドの戻り値を任意の値にする。
 * ・when(service.count()).thenReturn(10);
 * 　これは、service.count()メソッドの戻り値を10にしている。
 * その後、ユーザー一覧画面の表示内容が変わっていることをテストする。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * モックの戻り値設定
	 */
	@MockBean
	private UserService service;

	/**
	 * WithMockUserアノテーションを使うと、ログインした後にしか表示できない画面のテストをすることができる。
	 * なお、以下のようにusername,password,roles属性を指定することもできる。
	 * ・ユーザー指定サンプル
	 * @WithMockUser(username="satou",roles={"ROLE_ADMIN"})
	 */
	@Test
	@WithMockUser
	public void ユーザリスト画面のユーザ件数のテスト1() throws Exception {
		// UserServiceのcountメソッドの戻り値10に設定
		when(service.count()).thenReturn(10);

		// ユーザ一覧画面のチェック
		mockMvc
				.perform(get("/userList"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("合計：10件")));
	}
}

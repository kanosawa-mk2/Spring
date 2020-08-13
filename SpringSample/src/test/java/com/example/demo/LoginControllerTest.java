package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 画面表示内容の確認
 *
 * 画面表示内容の確認をするためには、モックを使う。
 * ここで、Springが用意しているモックを使う。
 * それを使うと、コントローラークラスのテストを簡単にできる。
 * 　Springのモックを使うために、@AutoConfigureMockMvcアノテーションをクラスに付ける。
 * その後、MockMvcクラスを@Autowiredすれば、Springのモックを使うことができる。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void ログイン画面表示() throws Exception {
		mockMvc
			.perform(get("/login")) // /loginにGETリクエストを送っている（ログイン画面表示）
			.andExpect(status().isOk()) // HTTPリクエストが正常に終了したかチェック
			.andExpect(content().string(containsString("ユーザID"))); // ログイン画面のhtmlにユーザIDという文字列が含まれているかチェック
	}
}

package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;

/**
 * REST用コントローラ
 *
 * RestControllerアノテーションと似たものとして、ResponseBodyアノテーションがある。
 * ResponseBodyはメソッドに付ける。
 * そうすると、そのメソッドの戻り値がhtmlファイルのパスではなく、JSONなどを返すことができるようになる。
 * ResponseBodyは、RESTサービス以外で使うことがある。
 * 例えば、JavaScriptからSpringにリクエストを投げて、非同期処理を行うなどの場合に使う。
 */
@RestController
public class UserRestController {

	@Autowired
	RestService service;

	/**
	 * ユーザ全件取得
	 * @return
	 */
	@GetMapping("/rest/get")
	public List<User> getUserMany(){
		return service.selectMany();
	}

	/**
	 * ユーザ1件取得
	 * @param userId
	 * @return
	 */
	@GetMapping("rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id") String userId){
		return service.selectOne(userId);
	}

	/**
	 * RequestBodyアノテーションを使うと、HTTPリクエストのボディ部分を引数にマッピングできる。
	 * こうするとで、POSTメソッドでもユーザ情報を取得できる。
	 * テストリクエスト
	 * 　curl -X POST -H "Content-Type:application/json" -d "{\"userId\":\"tamura@co.jp\",\"password\":\"pass\",\"userName\":\"tamura\",\"birthday\":\"1986-11-05\",\"age\":\"31\",\"marriage\":\"false\",\"role\":\"ROLE_ADMIN\"}" http://localhost:8080/rest/insert
	 */
	@PostMapping("/rest/insert")
	public String postUserOne(@RequestBody User user) {
		boolean result = service.insert(user);

		String str = "";

		if(result) {
			str = "{\"result\":\"ok\"}";
		}else {
			str = "{\"result\":\"error\"}";
		}
		return str;
	}

	/**
	 * テストリクエスト
	 * curl http://localhost:8080/rest/update -X PUT -H "Content-Type:application/json" -d "{\"userId\":\"yamada@xxx.co.jp\",\"password\":\"password\",\"userName\":\"yamada\",\"birthday\":\"1990-01-01\",\"age\":\"28\",\"marriage\":\"false\",\"role\":\"ROLE_ADMIN\"}"
	 */
	@PutMapping("/rest/update")
	public String putUserOne(@RequestBody User user) {
		boolean result = service.update(user);

		String str = "";

		if(result) {
			str = "{\"result\":\"ok\"}";
		}else {
			str = "{\"result\":\"error\"}";
		}
		return str;
	}

	/**
	 * テストリクエスト
	 * curl http://localhost:8080/rest/delete/yamada@xxx.co.jp -X DELETE
	 */
	@DeleteMapping("rest/delete/{id:.+}")
	public String deleteUserOne(@PathVariable("id") String userId) {
		boolean result = service.delete(userId);

		String str = "";

		if(result) {
			str = "{\"result\":\"ok\"}";
		}else {
			str = "{\"result\":\"error\"}";
		}
		return str;
	}
}

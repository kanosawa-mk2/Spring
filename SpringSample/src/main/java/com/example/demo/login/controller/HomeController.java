package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;

	private Map<String, String> radioMarriage;

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<String, String>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");

		return radio;
	}

	//ホーム画面のGET用メソッド.
	@GetMapping("/home")
	public String getHome(Model model) {

		//コンテンツ部分にホーム画面を表示するための文字列を登録
		model.addAttribute("contents", "login/home::home_contents");
		return "login/homeLayout";
	}

	@GetMapping("/userList")
	public String getUserList(Model model) {

		//コンテンツ部分にホーム画面を表示するための文字列を登録
		model.addAttribute("contents", "login/userList::userList_contents");

		List<User> userList = userService.selectMany();

		model.addAttribute("userList", userList);

		int count = userService.count();

		model.addAttribute("userListCount", count);

		return "login/homeLayout";

	}

	/**
	 * 動的なURLに対応したメソッドを作るためには、@GetMappingや@PostMappingの値に/{<変数名>}を付ける。
	 * 例えば、ユーザーIDを受け取る場合は、@GetMapping(/userDetail/{id})とする。
	 * 通常は/userDetail/{id}とすればいいのですが、本書のサンプルではユーザーIDがメールアドレス形式となっている。
	 * そのため、/userDetail/{id}だけだと処理がうまくいかない。
	 * 例えば、yamada@xxx.co.jpというユーザーIDが渡されてくると、yamada@xxx.coしか受け取れない。
	 * それに対応するために、正規表現を使用している。
	 * そのため、@GetMapping("/userDetail/{id:.+}")としています。これにより、メールアドレスをちゃんと受け取ることができる。
	 *
	 * PathVariableアノテーションを付けると、渡されてきたパス（URL）の値を引数の変数に入れることができる。
	 * コードでいうと、http://localhost:8080/userDetail/yamada@xxx.co.jpというURLでリクエストが来た場合、
	 * yamada@xxx.co.jpという値が引数のuserIdという変数に入れられる。
	 */
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		// ユーザーID確認（デバッグ）
		System.out.println("userId = " + userId);

		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");

		// 結婚ステータス用ラジオボタンの初期化
		radioMarriage = initRadioMarriage();

		// ラジオボタン用のMapをModelに登録
		model.addAttribute("radioMarriage", radioMarriage);

		// ユーザーIDのチェック
		if (userId != null && userId.length() > 0) {

			// ユーザー情報を取得
			User user = userService.selectOne(userId);

			// Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId()); //ユーザーID
			form.setUserName(user.getUserName()); //ユーザー名
			form.setBirthday(user.getBirthday()); //誕生日
			form.setAge(user.getAge()); //年齢
			form.setMarriage(user.isMarriage()); //結婚ステータス

			// Modelに登録
			model.addAttribute("signupForm", form);
		}

		return "login/homeLayout";
	}

	// params="update"はボタンのname属性を参照し、一致していたら実行する
	@PostMapping(value = "/userDetail", params = "update")
	public String getUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		// ユーザーID確認（デバッグ）
		System.out.println("更新ボタンの処理");

		User user = new User();
		user.setUserId(form.getUserId()); //ユーザーID
		user.setUserName(form.getUserName()); //ユーザー名
		user.setBirthday(form.getBirthday()); //誕生日
		user.setAge(form.getAge()); //年齢
		user.setMarriage(form.isMarriage()); //結婚ステータス

		boolean result = userService.updateOne(user);

		if (result) {
			model.addAttribute("result", "更新成功");
		} else {

			model.addAttribute("result", "更新失敗");
		}

		return getUserList(model);
	}

	@PostMapping(value = "/userDetail", params = "delete")
	public String getUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		// ユーザーID確認（デバッグ）
		System.out.println("削除ボタンの処理");

		boolean result = userService.deleteOne(form.getUserId());

		if (result) {
			model.addAttribute("result", "削除成功");
		} else {

			model.addAttribute("result", "削除失敗");
		}

		return getUserList(model);
	}

	//ログアウト用メソッド.
	@PostMapping("/logout")
	public String postLogout() {
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}

	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getUserList(model);
	}

}

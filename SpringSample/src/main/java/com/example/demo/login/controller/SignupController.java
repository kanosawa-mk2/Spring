package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;

@Controller
public class SignupController {

	private Map<String, String> radioMarriage;

	private Map<String, String> initRadioMarrige() {

		Map<String, String> radio = new LinkedHashMap<String, String>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}

	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {

		//@ModelAttributeをつけると自動でMpdelクラスに登録してくれる
		// 以下のコードと同じ
		//model.addAttribute("SignupForm", form);

		// @ModelAttributeでは、デフォルトでクラス名の最初の文字を小文字に変えた文字列がキー名に登録される。

		radioMarriage = initRadioMarrige();

		model.addAttribute("radioMarriage", radioMarriage);

		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,BindingResult bindingResult, Model model) {
		// データバインド結果の受け取りにはBindingResultクラスを追加する
		// バリデーションを実施するには、フォームクラスに@Validationアノテーションをつける
		// バリデーションの実施結果はBindeingResultクラスに入っているので、バリデーションを行う場合でも、BindingResultクラスを引数に設定する

		// @Validatedのパラメータに、実行順序のインターフェースを指定。
		if(bindingResult.hasErrors()) {
			// GETリクエストのメソッドでユーザ登録画面に戻る
			return getSignUp(form, model);
		}
		System.out.println(form);

		// login.htmlにリダイレクト
		return "redirect:/login";
	}

}

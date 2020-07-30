package com.example.demo.trySpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	// @GetMapping get用
	// http://localhost:8080/hello
	@GetMapping("/hello")
	public String getHello() {
		// hello.html画面に遷移
		return "hello";
	}

	// @PostMapping post用
	// @RequestParam htmlのname属性を指定
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str,Model model) {

		// 画面から受け取った文字列をModelに登録
		model.addAttribute("sample", str);
		// helloResponse.htmlに画面遷移
		return "helloResponse";
	}
}

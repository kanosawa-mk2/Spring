package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdviceクラスを用意しておくと、アプリケーション全体で発生した例外処理を実装できる。
 * クラスの中身は、@ExceptionHandlerを付けたメソッドを用意する
 *
 */
@ControllerAdvice
@Component
public class GlobalControllAdvice {

	/**
	 * DataAccessException発生時の処理メソッド.
	 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		model.addAttribute("error", "内部サーバーエラー（DB）：GlobalControllAdvice");

		model.addAttribute("message", "DataAccessExceptionが発生しました");

		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		//例外処理の内容（ログ出力）
		System.out.println("===========================================");
		System.out.println("DataAccessExceptionが発生しました。 : " + e);
		System.out.println("===========================================");

		return "error";
	}

	/**
	 * Exception発生時の処理メソッド.
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		//例外処理の内容（ログ出力）
		System.out.println("===========================================");
		System.out.println("Exceptionが発生しました。 : " + e);
		e.printStackTrace();
		System.out.println("===========================================");

		return "error";
	}
}

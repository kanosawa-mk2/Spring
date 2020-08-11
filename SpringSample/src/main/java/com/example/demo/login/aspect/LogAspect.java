package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// AOPのクラスには@Aspectをつける、同時にDIコンテナへBean定義をするため、@Componentもつける
@Aspect
@Component
public class LogAspect {

	// executionの指定方法
	//  "execution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>)"
	//  パッケージ名、クラス名などには正規表現が使用できる
	// ・正規表現の使い方
	//    *（アスタリスク） ：アスタリスクを使用すると、任意の文字列を表します。
	//                        パッケージでは１階層のパッケージ名、メソッドの引数では、１つの引数になります。
	//    ..（ドット２文字）：ドットを２個続けると、パッケージの記述箇所では、任意（０以上）のパッケージになります。
	//                        メソッドの引数では、任意（０以上）の引数になります。
	//    ＋（プラス）      ：クラス名の後に指定すると、指定クラスのサブクラス／実装クラスが含まれます。
	//
	@Before("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("メソッド開始："+ jp.getSignature());
	}

	@After("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了："+ jp.getSignature());
	}
}

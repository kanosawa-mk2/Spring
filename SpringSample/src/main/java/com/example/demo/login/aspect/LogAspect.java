package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// AOPのクラスには@Aspectをつける、同時にDIコンテナへBean定義をするため、@Componentもつける
@Aspect
@Component
public class LogAspect {

	// executionの指定方法
	// その１
	// 　execution(<戻り値> <パッケージ名>.<クラス名>.<メソッド名>(<引数>)
	// 　パッケージ名、クラス名などには正規表現が使用できる
	// 　・正規表現の使い方
	// 　　　*（アスタリスク） ：アスタリスクを使用すると、任意の文字列を表します。
	// 　　                       パッケージでは１階層のパッケージ名、メソッドの引数では、１つの引数になります。
	// 　　　 ..（ドット２文字）：ドットを２個続けると、パッケージの記述箇所では、任意（０以上）のパッケージになります。
	// 　　                       メソッドの引数では、任意（０以上）の引数になります。
	// 　　   ＋（プラス）      ：クラス名の後に指定すると、指定クラスのサブクラス／実装クラスが含まれます。
	//
	// その２ Bean名で指定
	//   bean(<Bean名>)
	//
	// その３ アノテーション指定
	//   @annotation(<アノテーションクラス名>)
	//   全アノテーション指定
	//     @Around("@within(org.springframework.stereotype.Controller)")
	//     @withinを使うと、指定したアノテーションが付いているクラスの全てのメソッドが対象となります。ここでも、パッケージ名を含めて指定します。
	//@Before("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	@Before("execution(* *..*.*Controller.*(..))") // コントローラークラスのすべてのメソッド対象
	public void startLog(JoinPoint jp) {
		System.out.println("@Beforeメソッド開始："+ jp.getSignature());
	}

	//@After("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	@After("execution(* *..*.*Controller.*(..))") // コントローラークラスのすべてのメソッド対象
	public void endLog(JoinPoint jp) {
		System.out.println("@Afterメソッド終了："+ jp.getSignature());
	}

	/**
	 * 　Aroundを使う場合、アノテーションをを付けたメソッドの中で、AOP対象クラスのメソッドを直接実行します。
	 * proceedメソッドで、実行しています。
	 * だからAroundを使うと、メソッド実行の前後で任意の処理をすることができるのです。
	 * メソッドを直接実行しているため、returnには実行結果の戻り値を指定します。
	 * Around内でメソッドの実行を忘れると大変なので、ご注意ください。
	 */
	@Around("execution(* *..*.*Controller.*(..))")
	//@Around("bean(*Controller)")
	//@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	//@Around("@within(org.springframework.stereotype.Controller)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("@Aroundメソッド開始:" + jp.getSignature());
		try {

			Object result = jp.proceed();
			System.out.println("@Aroundメソッド終了:" + jp.getSignature());

			return result;
		} catch (Exception e) {
			System.out.println("@Aroundメソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * UserDaoクラスのログ出力
	 */
	@Around("execution(* *..*.*UserDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("UserDaoメソッド開始:" + jp.getSignature());
		try {

			Object result = jp.proceed();
			System.out.println("UserDaoメソッド終了:" + jp.getSignature());

			return result;
		} catch (Exception e) {
			System.out.println("UserDaoメソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}

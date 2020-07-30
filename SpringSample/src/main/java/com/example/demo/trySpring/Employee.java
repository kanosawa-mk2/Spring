package com.example.demo.trySpring;

import lombok.Data;

// ドメイン、モデル、DTOクラス
// Lombokの機能によりgetter,setterを自動生成
@Data
public class Employee {

	/** 従業員ID */
	private int employeeId;

	/** 従業員名 */
	private String employeeName;

	/** 年齢 */
	private int age;
}

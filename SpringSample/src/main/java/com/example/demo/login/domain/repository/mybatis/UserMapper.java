package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.User;

/**
 * MybatisによるCRUD操作
 *
 * ポイント１：@Mapper　MyBatisでSQLを実行するクラスには@Mapperアノテーションを付ける。
 * ポイント２：変数の指定　SQLを実行するためには、@Insertや@Selectなどのアノテーションを付ける。
 * 　　　　　　そして、アノテーションの引数にSQL文をセットする。
 * 　　　　　　これが、アノテーションでのMyBatisの使い方になる。
 * 　　　　　　そして、#{<変数名>}と指定することで、SQL文にメソッドの引数をセットできる。
 * ポイント３：カラム名　Select文を実行して、その戻り値をUserなどの参照型にしているメソッドには、注意が必要。
 * 　　　　　　それは、テーブルのカラム名と、クラスのフィールド名を一致させなければいけない。
 * 　　　　　　ユーザーIDの例で説明すると、以下のようになる。
 * 　　　　　　　・ユーザーID
 * 　　　　　　　　　m_userテーブルのカラム名：user_id
 * 　　　　　　　　　Userクラスのフィールド名：userId
 * 　　　　　　　このように、テーブルのカラム名と、クラスのフィールド名が一致していない場合は、
 * 　　　　　　　SQL文にAS句を使ってカラム名を変更する。
 * 　　　　　　　そうすることで、カラム名とフィールド名が一致して、Select結果をUserクラスにセットされる。
 *
 */
@Mapper
public interface UserMapper {

	// 登録用メソッド
	@Insert("INSERT INTO m_user ("
			+ " user_id,"
			+ " password,"
			+ " user_name,"
			+ " birthday,"
			+ " age,"
			+ " marriage,"
			+ " role)"
			+ " VALUES ("
			+ " #{userId},"
			+ " #{password},"
			+ " #{userName},"
			+ " #{birthday},"
			+ " #{age},"
			+ " #{marriage},"
			+ " #{role})")
	public boolean insert(User user);

	// １件検索用メソッド
	@Select("SELECT user_id AS userId,"
			+ "password,"
			+ "user_name AS userName,"
			+ "birthday,"
			+ "age,"
			+ "marriage,"
			+ "role"
			+ " FROM m_user"
			+ " WHERE user_id = #{userId}")
	public User selectOne(String userId);

	// 全件検索用メソッド
	@Select("SELECT user_id AS userId,"
			+ "password,"
			+ "user_name AS userName,"
			+ "birthday,"
			+ "age,"
			+ "marriage,"
			+ "role"
			+ " FROM m_user")
	public List<User> selectMany();

	// １件更新用メソッド
	@Update("UPDATE m_user SET"
			+ " password = #{password},"
			+ " user_name = #{userName},"
			+ " birthday = #{birthday},"
			+ " age = #{age},"
			+ " marriage = #{marriage}"
			+ " WHERE user_id = #{userId}")
	public boolean updateOne(User user);

	// １件削除用メソッド
	@Delete("DELETE FROM m_user WHERE user_id = #{userId}")
	public boolean deleteOne(String userId);
}

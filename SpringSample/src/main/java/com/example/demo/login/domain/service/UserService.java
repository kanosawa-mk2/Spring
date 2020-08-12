package com.example.demo.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

	/**
	 * 　Autowiredと一緒にQualifierアノテーションを使用すると、
	 * どのBeanを使用するか指定することができる。
	 * インターフェースを継承したクラスが２つ（UserDaoJdbcImplとUserDaoJdbcImpl2）ある場合は、
	 * Qualifierを付けないといけない。
	 */
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	//@Qualifier("UserDaoJdbcImpl2") RowMapper使用版
	//@Qualifier("UserDaoJdbcImpl3") BeanPropertyRowMapper使用版
	//@Qualifier("UserDaoJdbcImpl4") ResultSetExtractor使用版
	//@Qualifier("UserDaoNamedJdbcImple") NamedParameterJdbcTemplate使用版
	UserDao dao;


	public boolean insert(User user) {
		int rowNumber = dao.insertOne(user);

		return rowNumber > 0;
	}

	public int count() {
		return dao.count();
	}

	public List<User> selectMany(){
		return dao.selectMany();
	}

	public User selectOne(String userId){
		return dao.selectOne(userId);
	}

	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		return rowNumber > 0;
	}

	public boolean deleteOne(String userId) {
		int rowNumber = dao.deleteOne(userId);
		return rowNumber > 0;
	}

	public void userCsvOut() throws DataAccessException {
		dao.userCsvOut();
	}

	public byte[] getFile(String fileName)throws IOException{
        // ファイルシステム（デフォルト）の取得
        FileSystem fs = FileSystems.getDefault();

        // ファイル取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
	}
}

package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LogDaoH2Impl implements LogDao {
	
	
	@Autowired
	private DataSource dataSource;
	
//	public LogDaoH2Impl() {
//        try {
//            // JDBC 드라이버 로드
//            Class.forName("org.h2.Driver");
//            
//            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
//        }
//        catch (Exception e) {            
//            e.printStackTrace();
//        }
//	}

	@Override
	public Object addLog(Map<String, Object>map) {
		
		PreparedStatement psmt;
		try {
			psmt = dataSource.getConnection().prepareStatement("insert into DBLOG (method,sqlstring,success) values (?,?,?)");
			psmt.setObject(1, map.get("method") );
			psmt.setObject(2, map.get("sqlstring"));
			psmt.setObject(3, map.get("success"));
			psmt.executeUpdate();
			psmt.close();
			return map.values();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}

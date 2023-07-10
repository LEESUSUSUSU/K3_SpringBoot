package edu.pnu.logDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import edu.pnu.domain.MemberLog;

public class LogDao {

	private Connection con = null;

	public LogDao() {
		try {
			Class.forName("org.h2.Driver");

			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberLog addMember(MemberLog read) {

		int increment = getNextIncrement();

		PreparedStatement st = null;

		try {

			st = con.prepareStatement("insert into member (increment,method,sqlstring,regidate) values (?,?,?,?)");
			st.setInt(1, increment);
			st.setString(2, read.getMethod());
			st.setString(3, read.getSqlstring());
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	private int getNextIncrement() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
			return rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;

	}
}

package edu.pnu.dao.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {

	@Autowired
	private DataSource dataSource;

//	public MemberDaoH2Impl() {
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
	public Map<String, Object> getMembers() {
		Statement st = null;
		ResultSet rs = null;
		String sqlString = "select * from member order by id asc";
		Map<String, Object> map = new HashMap<>();
		try {
			List<MemberVO> list = new ArrayList<>();
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery(sqlString);
			while (rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}

			map.put("method", "get");
			map.put("sqlstring", sqlString);
			// map.put("regidate", new Date());
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		Statement st = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<>();
		try {
			String sqlString = String.format("select * from member where id=%d", id);
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setPass(rs.getString("pass"));
			m.setName(rs.getString("name"));
			m.setRegidate(rs.getDate("regidate"));

			map.put("method", "get");
			map.put("sqlstring", sqlString);
			// map.put("regidate", new Date());
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private int getNextId() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
			return rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {

		int id = getNextId();
		Map<String, Object> ret = new HashMap<>();
		PreparedStatement st = null;
		try {
			String sqlString = "insert into member (name,pass) values (?,?)";

			st = dataSource.getConnection().prepareStatement(sqlString);

			st.setString(1, member.getName());
			st.setString(2, member.getPass());
			st.executeUpdate();

			ret.put("method", "post");
			ret.put("sqlstring", sqlString);
			ret.put("regidate", new Date());
			ret.put("success", true);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("success", false);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		PreparedStatement st = null;
		Map<String, Object> ret = new HashMap<>();
		try {
			String sqlString = "update member set name=?,pass=? where id=?";
			st = dataSource.getConnection().prepareStatement(sqlString);

			st.setString(1, member.getName());
			st.setString(1, member.getPass());
			st.setInt(1, member.getId());

			ret.put("method", "put");
			ret.put("sqlstring", sqlString);
	//		ret.put("regidate", new Date());
			ret.put("success", true);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("success", false);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		PreparedStatement st = null;
		Map<String, Object> ret = new HashMap<>();
		try {
			String sqlString = "deletef from member id =?";
			st = dataSource.getConnection().prepareStatement(sqlString);

			st.setInt(1, id);

			ret.put("method", "put");
			ret.put("sqlstring", sqlString);
	//		ret.put("regidate", new Date());
			ret.put("success", true);
			
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("success", false);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

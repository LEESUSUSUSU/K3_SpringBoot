package edu.pnu.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;



public class MemberController {
	
	@Autowired
	private MemberService MemberService;
	
//	@SuppressWarnings("static-access")
//	public MemberController() {
//		try {
//			getClass().forName(driver);
//			con = DriverManager.getConnection(url, userID, password);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public List<Member> getMembers() throws SQLException{
		List<Member> list = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from member");
		while(rs.next()) {
			Member m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			list.add(m);
		}
		rs.close();
		stmt.close();
		return list;
	}
	
	public Member getMember(Long id) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(String.format("select * from member where id = %d", id));
		rs.next();
		Member m = Member.builder()
						.id(rs.getLong("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
		rs.close();
		stmt.close();
		return m;
	}
	
	public int insertMember(Member member) throws SQLException {
		String sql = "insert into member (pass, name) values (?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member.getPass());
		ps.setString(2, member.getName());
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 입력되었습니다.");
		ps.close();
		return result;
	}

	public int updateMember(Member member) throws SQLException {
		String sql = "update member set pass = ?, name = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member.getPass());
		ps.setString(2, member.getName());
		ps.setLong(3, member.getId());
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 수정되었습니다.");
		ps.close();
		return result;
	}

	public int deleteMember(Long id) throws SQLException {
		String sql = "delete from member where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 삭제되었습니다.");
		ps.close();
		return result;
	}
}

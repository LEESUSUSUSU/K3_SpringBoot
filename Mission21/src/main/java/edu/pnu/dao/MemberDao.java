package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.Member;


@Repository
public class MemberDao {
	
	@Autowired
	private DataSource dataSource;
	


	
	public List<Member> getMembers() throws SQLException{
		List<Member> list = new ArrayList<>();
		Statement stmt = dataSource.getConnection().createStatement();
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
	
	public Member getMember(@PathVariable long id) throws SQLException {
		Statement stmt = dataSource.getConnection().createStatement();
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
		PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
		ps.setString(1, member.getPass());
		ps.setString(2, member.getName());
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 입력되었습니다.");
		ps.close();
		return result;
	}

	public int updateMember(Member member) throws SQLException {
		String sql = "update member set pass = ?, name = ? where id = ?";
		PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
		ps.setString(1, member.getPass());
		ps.setString(2, member.getName());
		ps.setLong(3, member.getId());
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 수정되었습니다.");
		ps.close();
		return result;
	}

	public int deleteMember(@PathVariable long id) throws SQLException {
		String sql = "delete from member where id = ?";
		PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
		ps.setLong(1, id);
		int result = ps.executeUpdate(); 
		System.out.println("데이터가 삭제되었습니다.");
		ps.close();
		return result;
	}
}

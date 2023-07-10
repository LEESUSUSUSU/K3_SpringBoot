package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
   private String driver = "org.h2.Driver";
   private String url = "jdbc:h2:tcp://localhost/~/test";
   private String username = "sa";
   private String password = "tiger";
   
   private Connection con;
   
   // Datatbase Connection 설정
   public MemberDao() {
      try {
         Class.forName(driver);
         con = DriverManager.getConnection(url, username, password);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public int insertMember(Member m) throws SQLException {
      String sql = "insert into member (name, age, nickname) values (? ,? ,?)";
      PreparedStatement psmt = con.prepareStatement(sql);
      
      psmt.setString(1, m.getName());
      psmt.setInt(2, m.getAge());
      psmt.setString(3, m.getNickname());
      
      int result = psmt.executeUpdate();
      
      System.out.println("데이터가 입력되었습니다.");
      
      psmt.close();
      return result;
   }
   
   public List <Member> getMembers() throws SQLException {
      List<Member> memberList = new ArrayList<>();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from member");

      while (rs.next()) {
         Member member = new Member();
         member.setId(rs.getLong("id"));
         member.setName(rs.getString("name"));
         member.setAge(rs.getInt("age"));
         member.setNickname(rs.getString("nickname"));

         memberList.add(member);
      }

      stmt.close();
      rs.close();
      return memberList;
   }
   
   public Member getMember(Long id) {
      
      try {
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(String.format("select * from member where id = %d", id));
         
         rs.next();
         
         Member m = Member.builder().id(rs.getLong("id")).name(rs.getString("name")).age(rs.getInt("age")).nickname(rs.getString("nickname")).build();
         return m;
      } catch(Exception e) {
         e.printStackTrace();
      }
      return null;
   }
}

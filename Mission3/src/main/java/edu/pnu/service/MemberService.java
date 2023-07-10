package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

   private MemberInterface memberDao;

   public MemberService() {
 
      memberDao = new MemberDao();

   }

   public MemberVO getMember(Integer id) throws SQLException {
      return memberDao.getMember(id);
   }

   public List<MemberVO> getMembers() throws SQLException {
      return memberDao.getMembers();
   }

   public MemberVO addMember(MemberVO m) throws SQLException {
      return memberDao.addMember(m);
   }

   public MemberVO updateMember(MemberVO m) throws SQLException {
      return memberDao.updateMember(m);
   }

   public boolean deleteMember(Integer id) {
      return memberDao.deleteMember(id);
   }
}
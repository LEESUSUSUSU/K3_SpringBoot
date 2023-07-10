package edu.pnu.Service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoList;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberLog;
import edu.pnu.domain.MemberVO;
import edu.pnu.logDao.LogDao;

@Service
public class MemberService {
	
	 private MemberInterface memberDao;
	 private LogDao logDao;

	public MemberService() {
		
		 memberDao = new MemberDaoList();
		 this.logDao = new LogDao();
		 
		
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
	

	   @SuppressWarnings("unchecked")
	public MemberLog getMembers1() throws SQLException {
		     
		   HashMap<String, String> map = new HashMap<>();
		   
		   map = (HashMap<String, String>) memberDao.getMembers();
		
		   if(!map.isEmpty()) {
			   
			   return 
			   
			  
		   }
		return null;
		   
		   
	
		   
		   }

		   public MemberLog addMember1(MemberLog m) throws SQLException {
		      return MemberLog.addMember1(m);
		   }
	
}

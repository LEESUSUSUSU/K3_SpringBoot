package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDao;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;


@Service
public class MemberService {
	
	 public final MemberInterface memberDao;
	 public final LogDao logDao;
	 	
@Autowired
public MemberService(MemberInterface memberDao,LogDao logDao) {
	this.logDao =logDao;
	this.memberDao = memberDao;
}

//	public MemberService() {
//		memberDao = new MemberDaoH2Impl();
//		//memberDao = new MemberDaoListImpl();
//		
//		//logDao = new LogDaoH2Impl();
//		logDao = new LogDaoFileImpl();
//	}
//	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String,Object> map = new HashMap<>();
		logDao.addLog(map);
		return (List<MemberVO>) map.get("result");
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		logDao.addLog(map);
		return (MemberVO)map.get("result");
	}

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		logDao.addLog(map);
		return (MemberVO)map.get("result");
	}

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		logDao.addLog(map);
		return (MemberVO)map.get("result");
	}

	public MemberVO deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		logDao.addLog(map);
		return (MemberVO)map.get("result");
	}
}

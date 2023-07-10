package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;


@Service
public class MemberService {
	
	@Autowired
	private MemberDao MemberDao;

	

	public List<Member> getMembers() throws SQLException {
		return MemberDao.getMembers();
	}

	public Member getMember(Long id) throws SQLException {
		return MemberDao.getMember(id);
	}

	public int insertMember(Member member) throws SQLException {
		return MemberDao.insertMember(member);
	}

	public int updateMember(Member member) throws SQLException {
		return MemberDao.updateMember(member);
	}

	public int deleteMember(Long id) throws SQLException {
		return MemberDao.deleteMember(id);
	}
}

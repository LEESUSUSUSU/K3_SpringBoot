package edu.pnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
public static final Connection con = null;
	
	
	public List<MemberVO> getMembers() throws SQLException;
	
	MemberVO getMember(Integer id) throws SQLException;
	MemberVO addMember(MemberVO member)throws SQLException;
	MemberVO updateMember(MemberVO member)throws SQLException;
	
	boolean deleteMember(Integer id);

}

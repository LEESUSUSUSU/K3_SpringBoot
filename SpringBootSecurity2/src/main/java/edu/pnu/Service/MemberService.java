package edu.pnu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import edu.pnu.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;

	public void save(Member member) {
		memberRepo.save(member);

	}

}

package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Component
public class Memerinitialize implements ApplicationRunner {
	
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd"))
				.role("ROLE_MEMBER")  // ROLE_ 부트에서 사용 하는 기본 규칙.
				.enabled(true)
				.build());
	
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	}

}

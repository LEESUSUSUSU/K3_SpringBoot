package edu.pnu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
@WebMvcTest
class Chapter3ApplicationTests {
	
	
	@Autowired    // 객체에 저장을 해준다.
	MockMvc monkMvc;  //참조 변수
	
	

	@Test
	void contextLoads()throws Exception {
		monkMvc.perform(get("/hello").param("name", "둘리"))
				.andExpect(status().isOk())
//				.andExperct(content().string("Hello: 둘리"))  리턴된 값이 이거냐.
				.andDo(print());
	}

}

package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;

	//@Test
	//@Order(1)
	void contextLoads() {

		for (int i = 0; i < 10; i++) {
			boardRepo.save(Board.builder().title(i + "번째 게시글").writer(i + "테스터").content("content" + i)
					.createDate(new Date()).cnt(0L).build());

		}

	}

	//@Test
	public void testGetBoard() {

//		Optional<Board> opt = boardRepo.findById(1L);
//		Board board = opt.board()

		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}

	//@Test
	public void testUpdateBoard() {
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 전");
			System.out.println(board);

			board.setTitle("제목수정");
			boardRepo.save(board);
		}
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board);
		}

	}
	//@Test
	public void testDeletBoard() {
		boardRepo.deleteById(2L);
	}
	//@Test
	public void testfindAll() {
		List<Board> list =boardRepo.findAll();
		
		System.out.println("모든 데이터를 출력합니다.");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	//@Test
	public void testQueryAnnotationTest1() {
		System.out.println("testQueryAnnotationTest1");
		// select b form Board b where b.title like %title1% order by b.seq desc
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for(Board b : list) {
			System.out.println(b);
		}
		
	}
	
	//@Test
	public void testQueryAnnotationTest2() {
		System.out.println("testQueryAnnotationTest2");
		// select b form Board b where b.title like %title1% order by b.seq desc
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for(Board b : list) {
			System.out.println(b);
		}
		
	}
	
	//@Test
	public void testQueryAnnotationTest3() {
		System.out.println("testQueryAnnotationTest3");
		// select b form Board b where b.title like %title1% order by b.seq desc
		List<Object[]> list = boardRepo.queryAnnotationTest3("title1");
		for(Object[] b : list) {
			System.out.println(Arrays.toString(b) );
		}
		
	}
	
	//@Test
	public void testQueryAnnotationTest4() {
		System.out.println("testQueryAnnotationTest4");
		
		List<Object[]> list = boardRepo.queryAnnotationTest4("title1");
		for(Object[] b : list) {
			System.out.println(Arrays.toString(b) );
		}
		
	}
	
	@Test
	public void testQueryAnnotationTest5() {
		System.out.println("testQueryAnnotationTest5");
		
		Pageable paging = PageRequest.of(5, 5);
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		for(Board b : list) {
			System.out.println("--->"+b);
			
			
		}
	}
	
	
}

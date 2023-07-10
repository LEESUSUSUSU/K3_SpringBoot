package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;

//	private void test1(String serachCondition, String searchKeyword) {
//		BooleanBuilder builder = new BooleanBuilder();
//		QBoard qboard = QBoard.board;
//
//		if (serachCondition.equals("TITLE")) {
//			// select b from Board b where title like '%' ||: serarchKeyword||'%'
//			builder.and(qboard.title.like("%" + searchKeyword + "%"));
//
//		} else if (serachCondition.equals("CONTENT")) {
//			builder.and(qboard.content.like("%" + searchKeyword + "%"));
//		}
//
//		Iterable<Board> list = boardRepo.findAll(builder);
//		for (Board b : list) {
//			System.out.println("---->" + b);
//		}
//	}

	@Test
	public void testDynamicQuery() {

		// test1("TITLE", "title1");
		// test1("CONTENT","content2");

		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content2");
		test(map);

	}

	private void test(Map<String, String> map) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (key.equals("TITLE") ) {
				builder.and(qboard.title.like("%" + map.get(key) + "%"));
//			System.out.println(builder);
			} else if (key.equals("CONTENT")) {
				builder.or(qboard.content.like("%" + map.get(key) + "%"));
//				System.out.println(builder);
			}else {
				break;
			}

			Iterable<Board> list = boardRepo.findAll(builder);
			for (Board b : list) {
				System.out.println("---->" + b);
			}

		}

	}
}

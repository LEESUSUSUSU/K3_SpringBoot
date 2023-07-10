package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;



//t 는 테이블 이름

public interface BoardRepository extends JpaRepository<Board, Long> {
	

//	List<Board> findByTitleLike(String keyword);
//	List<Board> findByTitleLike(String keyword, Pageable paging);
//
	@Query("Select b from Board b where b.title like %?1% order by b.seq DESC")
	List <Board> queryAnnotationTest1(String searchKeyword);

	@Query("Select b from Board b where b.title like %:searchKeyword% order by b.seq DESC")
	List <Board> queryAnnotationTest2(String searchKeyword);
	
	@Query("Select b.seq,b.writer,b.createDate from Board b"
	+" where b.title like %:searchKeyword% order by b.seq desc")
	List <Object[]> queryAnnotationTest3(@Param("searchKeyword")String searchKeyword);
	
	@Query(value = "Select seq, title, writer, createDate "
			+ "from board where title like '%'||:sk||'%' "
			+ "order by seq desc", nativeQuery = true)
	List <Object[]> queryAnnotationTest4(@Param("sk")String searchKeyword);
	
	@Query("Select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest5(Pageable paging);
}

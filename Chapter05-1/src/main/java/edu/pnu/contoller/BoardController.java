package edu.pnu.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	BoardRepository boardRepo;

	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		List<Board> list = boardRepo.findAll();
		return boardRepo.findAll();

	}
	
	@GetMapping("/getBoard")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}
	
	@PostMapping("/insetBoard")
	public Board insertBoard(Board board) {
		return boardRepo.save(board);
		
	}
	
	
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
		
	}
	
	
	
	@DeleteMapping("/board")
	public String DeletBoard(Long id) {
		boardRepo.deleteById(id);
		return  String.format("sql가 %d인 데이터가 삭제되었습니다.", id);
	}
	
//	@GetMapping("/boardTilteLike")
//	public List<Board> findByTitleLike(String keyword){
//		return boardRepo.findByTitleLike(keyword);
//	}
	
//	@GetMapping("/findTitleAndFisrCnt")
//	public List<Board> findByTitleAndFisrCnt(String keyword , int i){
//		return boardRepo.findByTitleAndFisrCnt(keyword , i);
//	}
	
	
	
	
	
//	@GetMapping("/boardTilteLike")
//	public List<Board> findByTitleLike(Integer pagenum, String keyword){
//		PageRequest page = PageRequest.of(pagenum, 5);
//		return boardRepo.findByTitleLike("%"+keyword+"%", page);
//	}
	

	
	
		
	
}

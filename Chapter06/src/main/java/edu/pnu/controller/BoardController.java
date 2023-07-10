package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.Delegate;

@Controller
public class BoardController {

//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//
//		for (int i = 1; i <= 10; i++) {
//			boardList.add(Board.builder()
//					.seq((long) +i)
//					.title("title" + i)
//					.writer("writer" + i)
//					.content("content" + i)
//					.createDate(new Date())
//					.cnt(0L)
//					.build());
//
//		}
//		model.addAttribute("list",boardList);
//		return "getBoardList";
//
//	}
	@Autowired
	private BoardService boardService;
		
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		
	model.addAttribute("list",boardService.getBoardList());
		
		return "getBoardList";
	}
	
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board",boardService.getBoard(board));
		return "getBoard";
	}

	
	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:updateBoard";
		
	}
	
	
	
	@GetMapping("/deleteBoard")
	public String DerletBoard(Board board) {
		boardService.deleteBoard(board);
		return"forward:getBoardList";
	}
}

package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {

	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController가 생성 되었습니다.");
		System.out.println("=".repeat(50));
	}
	
	@GetMapping("/hello")
	public String hello1(String name) {
		return "Get Hello: " + name;
	}
	@PostMapping("/hello")
	public String hello2(String name) {
		return "Post Hello: " + name;
	}

	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello: " + name;
	}

	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello: " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다...");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
		
		
	}
	
	@GetMapping("/getBoard1")
	public BoardVO getBoard1() {
		BoardVO board = new BoardVO(
				1,
				"테스트 제목",
				"테스터",
				"테스트 내용입니다...",
				new Date(),
				
				0);
		
	
		return board;
		
		
	}
	@GetMapping("/getBoard2")
	public BoardVO getBoard2() {
		
		return BoardVO.builder()
				.seq(1)
				.title("테스트 제목")
				.writer("테스터")
				.createDate(new Date())
				.cnt(0)
				.build();
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList(){
		
		List<BoardVO> list = new ArrayList<>();
		
		for(int i =0; i<10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("테스트 제목");
			board.setWriter("테스터");
			board.setContent(i+"번 테스트 내용입니다...");
			board.setCreateDate(new Date());
			board.setCnt(i);
			list.add(board);
			
			
		}
		
		
		return list;
	}
	
	@GetMapping("/board")
	public @ResponseBody BoardVO board(@RequestBody BoardVO b) {
		b.setCreateDate(new Date());
		b.setCnt(100);
		System.out.println("Board:" + b);
		return b;

	}

}
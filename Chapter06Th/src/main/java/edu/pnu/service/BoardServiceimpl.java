package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceimpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRepo.findAll();

	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.setCnt(b.getCnt() + 1);
		return boardRepo.save(b);

	}

	@Override
	public void updateBoard(Board board) {
		
		Board findBaord = boardRepo.findById(board.getSeq()).get();
		
		findBaord.setTitle(board.getTitle());
		findBaord.setContent(board.getContent());
		boardRepo.save(findBaord);

	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());

	}

}

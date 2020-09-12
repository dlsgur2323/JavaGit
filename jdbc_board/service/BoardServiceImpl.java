package service;

import java.util.List;
import java.util.Map;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVo;

public class BoardServiceImpl implements IBoardService{
	private IBoardDao dao;
	
	public BoardServiceImpl() {
		dao = new BoardDaoImpl();
	}
	
	@Override
	public int insertBoard(BoardVo boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(Map<String, String> boardMap, int boardNo) {
		return dao.updateBoard(boardMap, boardNo);
	}

	@Override
	public List<BoardVo> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public BoardVo getOneBoard(int boardNo) {
		return dao.getOneBoard(boardNo);
	}

	@Override
	public int getBoardCount(int boardNo) {
		return dao.getBoardCount(boardNo);
	}

	@Override
	public void updateCnt(int boardNo) {
		dao.updateCnt(boardNo);
	}

	@Override
	public List<BoardVo> getAllTitle(String title) {
		return dao.getAllTitle(title);
	}

}

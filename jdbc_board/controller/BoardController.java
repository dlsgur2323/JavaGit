package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import service.BoardServiceImpl;
import service.IBoardService;
import vo.BoardVo;

public class BoardController {
	private Scanner sc;
	private IBoardService service;
	
	public BoardController() {
		sc = new Scanner(System.in);
		service = new BoardServiceImpl();
	}
	
	public static void main(String[] args) {
		new BoardController().start();

	}

	private void start() {
		String searchTitle = "";
		List<BoardVo> boardList;
		while(true) {
			if(searchTitle.equals("")) {
				boardList = service.getAllBoard();
			} else {
				boardList = service.getAllTitle(searchTitle);
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("No             제목                     작성자         조회수");
			System.out.println("-------------------------------------------------------------");
			if(boardList == null || boardList.size()==0) {
				System.out.println("게시물이 없습니다.");
			} else {
				for (BoardVo board : boardList) {
					System.out.println(board.getBoard_no() + "\t" + board.getBoard_title() + "\t\t" + board.getBoard_writer() + "\t" + board.getBoard_cnt());
				}
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
			System.out.print("작업선택 >>");
			int input = Integer.parseInt(sc.nextLine());
			switch(input) {
				case 1:
					insertBoard();
					break;
				case 2:
					boardViewer();
					break;
				case 3:
					System.out.println("검색 작업");
					System.out.println("-----------------------");
					System.out.print("- 검색할 제목 입력 :");
					String title = sc.nextLine();
					searchTitle = title;
					break;
				case 0:
					System.out.println("프로그램 종료...");
					System.exit(0);
			}
		}
	}

	private void boardViewer() {
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int boardNo = Integer.parseInt(sc.nextLine());
		BoardVo boardVo = service.getOneBoard(boardNo);
		if(boardVo != null) {
			service.updateCnt(boardNo);
			System.out.println(boardNo + "번글 내용");
			System.out.println("-------------------------------------------------------------");
			System.out.println("- 제  목 : " + boardVo.getBoard_title());
			System.out.println("- 작성자 : " + boardVo.getBoard_writer());
			System.out.println("- 내  용 : " + boardVo.getBoard_content());
			System.out.println("- 작성일 : " + boardVo.getBoard_date());
			System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
			System.out.print("작업선택 >>");
			int input = Integer.parseInt(sc.nextLine());
			switch(input) {
				case 1 :
					updateBoard(boardNo);
					break;
				case 2 :
					int count = service.deleteBoard(boardNo);
					if(count > 0) {
						System.out.println(boardNo + "번글이 삭제되었습니다.");
					}
					break;
				case 3 :
					break;
			}
		} else {
			System.out.println("존재하지 않는 게시물 번호 입니다.");
		}
	
		
	}

	private void updateBoard(int boardNo) {
		System.out.println("수정 작업하기");
		System.out.println("-----------------------");
		System.out.print("- 제  목 :");
		String title = sc.nextLine();
		System.out.println();
		System.out.print("- 내  용 :");
		String content = sc.nextLine();
		Map<String, String> boardMap = new HashMap<>();
		boardMap.put("title", title);
		boardMap.put("content", content);
		int count = service.updateBoard(boardMap, boardNo);
		if(count >0) {
			System.out.println(boardNo + "번글이 수정되었습니다.");
		}
	}

	private void insertBoard() {
		System.out.println("새글 작성하기");
		System.out.println("-----------------------");
		System.out.print("- 제  목 :");
		String title = sc.nextLine();
		System.out.println();
		System.out.print("- 작성자 :");
		String writer = sc.nextLine();
		System.out.println();
		System.out.print("- 내  용 :");
		String content = sc.nextLine();
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_content(content);
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		int count = service.insertBoard(boardVo);
		if(count >0) {
			System.out.println("새글 등록 완료");
		} else {
			System.out.println("새글 등록 실패");
		}
	}

}



























package dao;

import java.io.BufferedReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.sql.CLOB;
import util.DBUtil;
import vo.BoardVo;

public class BoardDaoImpl implements IBoardDao{
	
	@Override
	public int insertBoard(BoardVo boardVo) {
		Connection conn = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO JDBC_BOARD (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT)"
					+ " VALUES (BOARD_SEQ.NEXTVAL, ? ,?, SYSDATE, 0, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, boardVo.getBoard_title());
			ps.setString(2, boardVo.getBoard_writer());
			ps.setString(3, boardVo.getBoard_content());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(Map<String, String> boardMap, int boardNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, boardMap.get("title"));
			ps.setString(2, boardMap.get("content"));
			ps.setInt(3, boardNo);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return cnt;
	}

	@Override
	public List<BoardVo> getAllBoard() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVo> boardList = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			conn = DBUtil.getConnection();
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT FROM JDBC_BOARD";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			boardList = new ArrayList<>();
			while(rs.next()) {
				BoardVo boardVo = new BoardVo();
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(sdf.format(rs.getDate(4)));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return boardList;
	}
	
	@Override
	public List<BoardVo> getAllTitle(String title) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVo> boardList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT FROM JDBC_BOARD WHERE BOARD_TITLE LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
			rs = ps.executeQuery();
			boardList = new ArrayList<>();
			while(rs.next()) {
				BoardVo boardVo = new BoardVo();
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(sdf.format(rs.getDate(4)));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return boardList;
	}

	@Override
	public BoardVo getOneBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BoardVo boardVo = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT FROM JDBC_BOARD WHERE BOARD_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			rs = ps.executeQuery();
			while(rs.next()) {
				boardVo = new BoardVo();
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(sdf.format(rs.getDate(4)));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardVo.setBoard_content(clobToString(rs.getClob(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e2) {}
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		return boardVo;
	}

	@Override
	public int getBoardCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCnt(int boardNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE JDBC_BOARD SET BOACR_CNT += 1 WHERE BOARD_NO ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			int cnt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(ps != null) try {ps.close();} catch (Exception e2) {}
			if(conn != null) try {conn.close();} catch (Exception e2) {}
		}
		
	}
	
	
	public static String clobToString(Clob clob) throws Exception {
		StringBuffer s = new StringBuffer();
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		String ts = "";
		while((ts = br.readLine()) != null) {
			s.append(ts + "\n");
		}
		br.close();
		return s.toString();
	}


}

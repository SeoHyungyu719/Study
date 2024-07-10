package board.model.service;

import static common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import board.model.vo.PageInfo;

public class BoardService {
	private BoardDAO bDAO = new BoardDAO();
	
	public int getListCount() {
		SqlSession session = getSqlSession();
		int listCount = bDAO.getListCount(session);
		return listCount;
	}

	public ArrayList<Board> selectBoardList(PageInfo pi) {
		SqlSession session = getSqlSession();
		ArrayList<Board> list = bDAO.selectBoardList(session, pi);
		return list;
	}

	public int getSearchListCount(HashMap<String, String> map) {
		SqlSession session = getSqlSession();
		int listCount = bDAO.getSearchListCount(session, map);
		return listCount;
	}

	public ArrayList<Board> selectBoardList(HashMap<String, String> map, PageInfo pi) {
		SqlSession session = getSqlSession();
		ArrayList<Board> list = bDAO.selectSearchList(session, map, pi);
		
		return list;
	}

	public Board selectBoard(int bId, Integer empNo) {
		SqlSession session = getSqlSession();
		Board b = bDAO.selectBoardList(session, bId);
		if(b != null) {
			if(empNo != null && empNo != b.getEmpNo()) {
				int result = bDAO.updateCount(session,bId);
				b.setCount(b.getCount()+1);
				if(result > 0) {
					session.commit();
				} else {
					session.rollback();
				}	
			}
		}
		
		return b;
	}

	public int updateBoard(Board b) {
		SqlSession session = getSqlSession();
		int result = bDAO.updateBoard(session, b);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}
		return result;
		
	}

	public int insertBoard(Board b) {
		SqlSession session = getSqlSession();
		int result = bDAO.insertBoard(session, b);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
	}

	public int deleteBoard(int bId) {
		SqlSession session = getSqlSession();
		int result = bDAO.deleteBoard(session, bId);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		return result;
	}


}

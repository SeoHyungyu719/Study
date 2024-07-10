package board.controller;

import java.io.IOException;
import java.util.HashMap;

import board.model.service.BoardService;
import board.model.vo.Board;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBoardServlet
 */
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 수정 페이지로 이동해야하는데, 게시글 수정 페이지에는 현 게시글에 대한 데이터가 노출돼야함
		//따라서 데이터를 들고 가야하기 때문에 -> forward
		//DB에서 수정할 게시글의 데이터를 select해올것
		//또 만들어도 되지만 우리는 게시글  상세보기할 때 만들어놓은 것이 있으니 그걸 활용할 예정
		/*
		  new BoardService().selectBoard(select해올 게시글 번호, 내가 본 글을 조회할 때 조회수가 올라가지 않도록
		  하기위한 장치);
		 */
		int bId=Integer.parseInt(request.getParameter("bId"));
		Board b = new BoardService().selectBoard(bId, null);
		//어차피 지금은 데이터만 조회해오는 것이 목적, 조회수를 올리는 것이 목적이 아니기 떄문에 null 적용
		request.setAttribute("b", b);
		request.getRequestDispatcher("WEB-INF/views/board/updateBoardPage.jsp").forward(request, response);;
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("content", content);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

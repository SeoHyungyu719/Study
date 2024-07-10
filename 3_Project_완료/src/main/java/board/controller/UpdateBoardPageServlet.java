package board.controller;

import java.io.IOException;

import board.model.service.BoardService;
import board.model.vo.Board;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBoardPageServlet
 */
public class UpdateBoardPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String isNotice = request.getParameter("isNotice") == null ? "N" : request.getParameter("isNotice");
		String content = request.getParameter("content");
		int bId = Integer.parseInt(request.getParameter("bId"));
		// 게시글 수정이기 떄문에 writer필요없고 글 번호만 필요함
		
		Board b = new Board();
		b.setBoardNo(bId);
		b.setTitle(title);
		b.setContent(content);
		b.setIsNotice(isNotice);
		
		int result = new BoardService().updateBoard(b);
		if(result > 0) {
			response.sendRedirect("selectBoard.bo?bId=" + bId);
		}else {
			request.setAttribute("msg", "게시글 수정을 실패하였습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

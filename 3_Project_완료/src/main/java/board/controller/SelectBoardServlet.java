package board.controller;

import java.io.IOException;

import board.model.service.BoardService;
import board.model.vo.Board;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectBoardServlet
 */
public class SelectBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		Employee loginUser = (Employee)request.getSession().getAttribute("loginUser");
		Integer empNo = null;
		if(loginUser != null) {
			empNo = loginUser.getEmpNo();
		}
		
		Board b = new BoardService().selectBoard(bId, empNo);
		if(b != null) {
			request.setAttribute("b", b);
			request.getRequestDispatcher("WEB-INF/views/board/boardDetail.jsp").forward(request, response);
			
		}else {
			request.setAttribute("msg", "해당 게시글은 존재하지 않습니다.");
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

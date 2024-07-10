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
 * Servlet implementation class insertBoardServlet
 */
public class insertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int writer = ((Employee)request.getSession().getAttribute("loginUser")).getEmpNo(); //로그인한 사람의 사번(작성자)
		String isNotice = request.getParameter("isNotice") == null? "N":"Y";
		// 작성자가 관리자가 아니거나, 작성자가 관리자이지만 공지버튼을 클릭하지 않았을 때는 isNotice가 넘어오지 않을것 = null
		// "Y"라고 하지 않고 request.getParameter("isNotice")라고 해도됨(어차핌 value에 Y가 있으므로ㅓ)
		
		Board b= new Board();
		b.setTitle(title);
		b.setContent(content);
		b.setEmpNo(writer);
		b.setIsNotice(isNotice);
		
		int result =  new BoardService().insertBoard(b);
		if(result > 0) {
			response.sendRedirect("list.bo");
			// 게시글 삽입에 성공하면, 게시글이 제대로 잘 작성됐는지 확인하기 위해 게시글 목록 조회 화면으로 넘어갈 것
			//boardList.jsp로 넘어가야한다고 생각할 수도 있는데 그래도 됨
			// 대신 boardList.jsp로 넘어가게 만ㄷ를려면 해당 페이지에서 데이터를 뿌려주기 위해 페이징 처리를 다시 진행해야함
			// 페이징 처리한 데이터를 boardList.jsp로 전달하는 작업는 SelectBoardListServlet.java에서 했었음
			// SelectBoardListServlet.java는 list.bo를 처리하는 Servlet이므로, snedRedirect()를 통해 list.bo로 이동해도 같은 효과
			// (코드를 줄이면서 같은 효과를 내니 오히려  더 좋은 케이스라고 볼 수 있따.)
		} else {
			request.setAttribute("msg", "게시글 등록에 실패하셨습니다");
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

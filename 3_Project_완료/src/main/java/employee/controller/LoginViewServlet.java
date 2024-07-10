 package employee.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginViewServlet
 */
@WebServlet("/loginView.me")
public class LoginViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * RequestDispatcher.forward()                         HttpServletResponse.sendRedirect()
			request, response 유지 								request, response 유지X (새로 객체 생성)
			== 데이터가 전달 가능(날아가지 않음)							== 데이터 전달 불가능(데이터 유지 안됨)
			        url 유지										  url 변경됨 -> 인자로 넣은 경로로
		*/
		
		//response.sendRedirect("WEB-INF/views/employee/login.jsp");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/employee/login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

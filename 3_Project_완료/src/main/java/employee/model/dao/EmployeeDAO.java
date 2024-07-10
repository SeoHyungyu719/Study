package employee.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import employee.model.vo.Employee;

public class EmployeeDAO {



	public Employee login(SqlSession session, Employee e) {//
		//첫 번쨰 인자 : 내가 접근할 쿼리명
		//두 번째 인자 : 쿼리에 전달할 데이터
		// object라 뜨지만 실제로는 T이므로 자유로운 반환값을 받을 수 있음
		// 만약 object라면 다운캐스팅을 햇어여함
		Employee loginUser = session.selectOne("employeeMapper.login", e);
		// 생성자 값과, developer에 컬럼명이 같아야 데이터를 불러온다.
		System.out.println(loginUser);
		return loginUser;
}
	  public ArrayList<Employee> selectAll(SqlSession session) {
		  ArrayList<Employee> list = (ArrayList)session.selectList("employeeMapper.selectAll");
		  //형변환을 ArrayList로 하던가, List로 값읋 받으면됨(부모가 자식을 받지 못하므로 자식형태로 설정)
		  return list;
	  }
		
	  public int checkEmpNo(SqlSession session, int inputId) { 
		  String query = "select count(*) from emp wher empno = ?";
		  int result = session.selectOne("employeeMapper.checkEmpNo", inputId);
		  return result;
	  }
	  
	   public int insertEmployee(SqlSession session, Employee emp) {
			  		  
			
			  
			 int result = session.insert("employeeMapper.insertEmployee", emp);
			  
			 return result; 
			 }
	   public int updateEmployee(SqlSession session, Employee e) { 
			 
			  int result = session.update("employeeMapper.updateEmployee", e);
			  return result;
			  }  
	

	
 
	 
	  public int updateState(SqlSession session, HashMap<String, Object> map) {
		  
	      int result = session.update("employeeMapper.updateState", map);
		  return result;
	  }
	 
}

package employee.model.service;
import static common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import employee.model.dao.EmployeeDAO;
import employee.model.vo.Employee;

public class EmployeeService {
   
   private EmployeeDAO empDAO = new EmployeeDAO();
   
   public Employee login(Employee e) {
	  SqlSession session = getSqlSession();
      Employee loginUser = empDAO.login(session,e);
      
      return loginUser;
   }
   public ArrayList<Employee> selectAll() { 
	   SqlSession session = getSqlSession();
	  ArrayList<Employee> list = empDAO.selectAll(session); 
	  return list; }
   
   public int checkEmpNo(int inputId) { 
	 SqlSession session = getSqlSession();
	 int result = empDAO.checkEmpNo(session, inputId); 
	 return result; }
   
   public int insertEmployee(Employee emp) { 
	   SqlSession session = getSqlSession();
	  int result = empDAO.insertEmployee(session, emp);
	  if(result>0) {
		  session.commit();
	  }else {
		  session.rollback();
	  }
	  return result; 
	  }
   
   public int updateEmployee(Employee e) { 
	   SqlSession session = getSqlSession();
	  int result = empDAO.updateEmployee(session, e);
	  
	  if(result > 0) { session.commit(); }else { session.rollback(); } return result; }
   
	 public int updateState(HashMap<String, Object> map) { 
	  SqlSession session = getSqlSession();
	  int result = empDAO.updateState(session, map);
	  if(result > 0) {
		  session.commit();
	  }else {
		  session.rollback();
	  }
	  return result; }
	 
	 

}

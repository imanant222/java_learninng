package mhl;

public class EmpService {
	EmployeeDAO eDao = new EmployeeDAO();
	public Emp checkEmp(String empId, String pwd) {
		String sql = "select * from Emp where empId=? and pwd=md5(?)";
		return eDao.querySingle(sql, Emp.class, empId, pwd);
	}
}

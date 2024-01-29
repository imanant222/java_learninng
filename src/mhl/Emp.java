package mhl;

public class Emp {
	private Integer id;
	//用Integer而非int，是因为id使用了自增长，自增长在insert时写null，这可以避免int id=null出现空指针异常情况。
	private String empId;
	private String pwd;
	private String name;
	private String job;
	
	public Emp() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}

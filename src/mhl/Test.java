package mhl;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		//测试工具类是否运行正常
		Connection c= JdbcUtilsByDruid.getConnection();
		System.out.println(c);

	}

}

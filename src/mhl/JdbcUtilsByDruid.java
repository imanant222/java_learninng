package mhl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtilsByDruid {
	static DataSource ds; 
	static{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/druid.properties"));
			ds = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() {
		try {
			return ds.getConnection();
			} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection c) {
		try {
			if(rs!=null) {
				
					rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(c!=null) {
				c.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

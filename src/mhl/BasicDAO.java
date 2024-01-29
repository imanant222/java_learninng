package mhl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BasicDAO<T> {
	private Connection c;
	private QueryRunner qr = new QueryRunner();
//	DML语句
	public int update(String sql,Object... parameters) {
		c = JdbcUtilsByDruid.getConnection();
		try {
			return qr.update(c, sql,parameters);
			//返回受影响行数
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtilsByDruid.close(null,null,c);
		}
	}
//	Select语句（1）多行多列
	public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters){
		c = JdbcUtilsByDruid.getConnection();
		try {
			return qr.query(c, sql, new BeanListHandler<T>(clazz), parameters);
			//返回一个T类的容器，里面是对象数组，数组里是单行每列的数据(也就是放在xxx（某表名）类里的属性值)
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtilsByDruid.close(null,null,c);
		}
	}
//	Select语句（2）单行多列
	public T querySingle(String sql, Class<T> clazz, Object... parameters){
		c = JdbcUtilsByDruid.getConnection();
		try {
			return qr.query(c, sql, new BeanHandler<T>(clazz), parameters);
			//返回一个T类对象，根据toString方法返回其中包含的属性
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtilsByDruid.close(null,null,c);
		}
	}
//	Select语句（2）单行多列
	public Object queryScalar(String sql, Object... parameters){
		c = JdbcUtilsByDruid.getConnection();
		try {
			return qr.query(c, sql, new ScalarHandler(), parameters);
			//返回一个Object对象
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtilsByDruid.close(null,null,c);
		}
	}
	
}

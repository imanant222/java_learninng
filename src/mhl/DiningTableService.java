package mhl;

import java.util.List;

public class DiningTableService {
	DiningTableDAO dtDAO = new DiningTableDAO();
	
	public List<DiningTable> list(){
		String sql = "select * from diningTable";
		return dtDAO.queryMulti(sql, DiningTable.class);
	}
	
	public DiningTable getDiningTable(int id) {
		String sql = "select * from diningTable where id=?";
		return dtDAO.querySingle(sql, DiningTable.class, id);
	}
	
	public boolean orderDiningTable(int id, String orderName, String orderTel) {
		String sql = "update diningTable set state='已被预订', orderName = ?, orderTel = ? where id=?" ;
		int update = dtDAO.update(sql, orderName,orderTel,id);
		return update > 0;
	}
	
	public boolean updateState(int diningTableId,String state) {
		int update = dtDAO.update("update diningTable set state = ? where id=?",state,diningTableId);
		return update>0;
	} 
	public boolean updateStateNameTel(int diningTableId,String state) {
		int update = dtDAO.update("update diningTable set state = ?,orderName='',orderTel=''  where id=?",state,diningTableId);
		return update>0;
	} 
}

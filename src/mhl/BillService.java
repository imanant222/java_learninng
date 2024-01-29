package mhl;

import java.util.List;
import java.util.UUID;

public class BillService {
	private BillDAO bDao = new BillDAO();
	private MenuService menuService = new MenuService();
	private DiningTableService dtService = new DiningTableService();
	/*
	 * +---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| id            | int         | NO   | PRI | NULL    | auto_increment |
| billId        | varchar(50) | NO   |     |         |                |
| menuId        | int         | NO   |     | 0       |                |
| nums          | int         | NO   |     | 0       |                |
| money         | double      | NO   |     | 0       |                |
| diningTableId | int         | NO   |     | 0       |                |
| billDate      | datetime    | NO   |     | NULL    |                |
| state         | varchar(50) | NO   |     |         |                |
+---------------+-------------+------+-----+---------+----------------+
	 */
	public boolean orderMenu(int menuId, int nums, int diningTableId) {
		String sql = "insert into bill values(null,?,?,?,?,?,now(),'未结账')";
		int update = bDao.update(sql, UUID.randomUUID().toString(), menuId, nums, menuService.getMenu(menuId).getPrice()*nums, diningTableId);
		if(update<=0) {
			return false;
		}
		return dtService.updateState(diningTableId, "就餐中");
	}
	public List<Bill> listBill(){
		return bDao.queryMulti("select * from bill", Bill.class);
	}
	public boolean hasUnpaidBill(int diningTableId) {
		Bill b = bDao.querySingle("select * from bill where diningTableId=? and state='未结账' limit 0,1", Bill.class, diningTableId);
		return b!=null;
	}
	public boolean payTheBill(int diningTableId,String PaymentMethod) {
		int update = bDao.update("update bill set state=? where diningTableId=? and state='未结账'",PaymentMethod, diningTableId);
		if(update<=0) {
			return false;
		}
		if(!dtService.updateStateNameTel(diningTableId, "空")) {
			return false;
		}
		return true;
	}
}

package mhl;

import java.util.List;

public class MenuService {
	MenuDAO mDao = new MenuDAO();
	
	public List<Menu> listMenu() {
		String sql = "select * from menu";
		return mDao.queryMulti(sql, Menu.class);
	}
	
	public Menu getMenu(int id) {
		return mDao.querySingle("select * from menu where id=?", Menu.class, id);
	}
}

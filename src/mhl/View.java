package mhl;

import java.util.List;

public class View {
	public static void main(String[] args) {
		new View().mainMenu();	
		}
	boolean loop = true;
	String key = " ";
	EmpService es = new EmpService();
	DiningTableService dt = new DiningTableService();
	MenuService m = new MenuService();
	BillService b = new BillService();
	public void listDiningTable() {
		System.out.println("桌号\t\t\t状态");
		List<DiningTable> list = dt.list();
		for(DiningTable d : list) {
			System.out.println(d);
		}
	}
	public void orderDiningTable() {
		System.out.println("===============预定餐桌==============");
		System.out.println("请选择要预定的餐桌号(-1退出)：");
		int id = Hsp_Utils.readInt();
		if(id==-1) {
			System.out.println("===============退出餐桌预定==============");
			return;
		}
		char key = Hsp_Utils.readConfirmSelection();
		if(key == 'Y') {
			DiningTable diningTable = dt.getDiningTable(id);
			if (diningTable == null) {
				System.out.println("===============餐桌不存在！==============");
				return;
			}
			if(diningTable.getState().equals("已被预订")) {
				System.out.println("===============该餐桌已被预订！==============");
				return;
			}
			System.out.println("请输入预定人姓名：");
			String name = Hsp_Utils.readString(50);
			System.out.println("请输入预定人电话：");
			String tel = Hsp_Utils.readString(50);
			if(dt.orderDiningTable(id, name, tel)){
				System.out.println("===============餐桌预定成功！==============");
			}else {
				System.out.println("===============餐桌预定失败！==============");
			}
		}else {
			System.out.println("===============退出餐桌预定==============");
		}	
	}
	
	public void listAllMenu() {
		System.out.println("\t===============菜品表==============");
		System.out.println("菜品编号\t\t菜品名称\t\t菜品类别\t\t菜品价格");
		List<Menu> list = m.listMenu();
		for(Menu m:list) {
			System.out.println(m);
		}
	}
	
	public void orderMenu() {
		System.out.println("===============点餐服务==============");
		System.out.println("请输入点餐的桌号(-1退出)：");
		int orderDiningTableId = Hsp_Utils.readInt();
		if(orderDiningTableId==-1) {
			System.out.println("===============取消点餐==============");
			return;
		}
		System.out.println("请输入点餐的菜品号(-1退出)：");
		int orderMenuId = Hsp_Utils.readInt();
		if(orderDiningTableId==-1) {
			System.out.println("===============取消点餐==============");
			return;
		}
		System.out.println("请输入点餐的菜品量(-1退出)：");
		int orderNums = Hsp_Utils.readInt();
		if(orderDiningTableId==-1) {
			System.out.println("===============取消点餐==============");
			return;
		}
		if(dt.getDiningTable(orderDiningTableId)==null) {
			System.out.println("===============餐桌号不存在！==============");
			return;
		}
		if(m.getMenu(orderMenuId)==null) {
			System.out.println("===============菜品号不存在！==============");
			return;
		}
		if(b.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
			System.out.println("===============点餐成功！==============");
		}else {
			System.out.println("===============点餐失败！==============");
		}
	}
	
	public void listBill() {
		System.out.println("\t\t\t==============================账单=============================");
		System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t状态");
		List<Bill> bills = b.listBill();
		for(Bill b:bills) {
			System.out.println(b);
		}
	}
	
	public void paiTheBill() {
		System.out.println("===============结账服务==============");
		System.out.println("请选择要结账的桌号（-1退出）：");
		int tableId=Hsp_Utils.readInt();
		if(tableId==-1) {
			System.out.println("===============取消结账=============");
			return;
		}
		if(dt.getDiningTable(tableId)==null) {
			System.out.println("===============餐桌号不存在！==============");
			return;
		}
		if(!b.hasUnpaidBill(tableId)) {
			System.out.println("===============餐桌没有未结账单！==============");
			return;
		}
		System.out.println("请选择付款方式（现金/微信/支付宝）(回车取消结账)：");
		String paymentMethod=Hsp_Utils.readString(50,"");
		if("".equals(paymentMethod)) {
			System.out.println("===============取消结账=============");
			return;
		}
		char key = Hsp_Utils.readConfirmSelection();
		if(key=='Y') {
			if(b.payTheBill(tableId, paymentMethod)) {
				System.out.println("===============结账成功！=============");
			}else {
				System.out.println("===============结账失败！=============");
			}
		}else {
			System.out.println("===============取消结账=============");
		}
	}
	
	public void mainMenu() {
		while(loop) {
			System.out.println("===============满汉楼==============");
			System.out.println("\t1 登陆满汉楼系统");
			System.out.println("\t1 退出满汉楼系统");
			System.out.println("请输入您的选择：");
			key = Hsp_Utils.readString(1);
			switch(key) {
			case "1":
				System.out.println("请输入账号:");
				String empId = Hsp_Utils.readString(50);
				System.out.println("请输入密码:");
				String pwd = Hsp_Utils.readString(50);
				Emp employee = es.checkEmp(empId, pwd);
				if(employee!=null) {
					while(loop) {
						System.out.println("===============欢迎["+employee.getName()+"]登陆满汉楼系统==============");
						System.out.println("\t\t1 显示餐桌状态");
						System.out.println("\t\t2 预定餐桌");
						System.out.println("\t\t3 显示所有菜品");
						System.out.println("\t\t4 点餐");
						System.out.println("\t\t5 查看账单");
						System.out.println("\t\t6 结账");
						System.out.println("\t\t9 退出系统");
						System.out.println("请输入您的选择：");
						key = Hsp_Utils.readString(1);
						switch(key) {
						case "1":
							listDiningTable();
							break;
						case "2":
							orderDiningTable();
							break;
						case "3":
							listAllMenu();
							break;
						case "4":
							orderMenu();
							break;
						case "5":
							listBill();
/*
 * 如果要做账单的联合查询，看https://www.bilibili.com/video/BV1fh411y7R8?t=3.8&p=874 （hsp视频的p874）
 * 简单来讲就是写一个multiBean类和对应的DAO，该bean类包含联合查询的表的类的属性，在multiService中再调用查询时语句记得用规范的多表查询就行。
 */
							break;
						case "6":
							paiTheBill();
							break;
						case "9":
							loop = false;
							break;
						}
					}
				}
				else {
					System.out.println("登陆失败！");
				}
				break;
			case "2":
				loop = false;
				break;
			default:
				System.out.println("您的输入有误！");
			}
		}
		System.out.println("退出系统成功～");
	}
}

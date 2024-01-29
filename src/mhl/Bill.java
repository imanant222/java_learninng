package mhl;

import java.time.LocalDateTime;
//import java.util.Date;

public class Bill {
	private Integer id;
	private String billId;
	private Integer menuId;
	private Integer nums;
	private Double money;
	private Integer diningTableId;
	private LocalDateTime billDate;
	private String state;
	
	public Bill() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getDiningTableId() {
		return diningTableId;
	}

	public void setDiningTableId(Integer diningTableId) {
		this.diningTableId = diningTableId;
	}

	public LocalDateTime getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return id
				+"\t\t"+menuId
				+"\t\t"+nums
				+"\t\t"+money
				+"\t\t"+diningTableId
				+"\t\t"+billDate
				+"\t\t"+state;
	}
	
}

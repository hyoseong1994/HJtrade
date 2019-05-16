package model;

public class BuyVO {
	private int number;
	private double kg;
	private int cost;
	private String buyDate;
	private double totalMoney;
	private String state;

	public BuyVO() {
		super();
	}

	public BuyVO(int number, double kg, int cost, String buyDate, double totalMoney, String state) {
		super();
		this.number = number;
		this.kg = kg;
		this.cost = cost;
		this.buyDate = buyDate;
		this.totalMoney = totalMoney;
		this.state = state;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

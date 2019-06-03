package model;

public class SellVO {
	private int number;
	private double kg;
	private int cost;
	private String buyDate;
	private double totalMoney;

	public SellVO() {
		super();
	}

	public SellVO(int number, double kg, int cost, String buyDate, double totalMoney) {
		super();
		this.number = number;
		this.kg = kg;
		this.cost = cost;
		this.buyDate = buyDate;
		this.totalMoney = totalMoney;
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

}
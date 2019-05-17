package model;

public class DealVO {
	private int d_no;
	private int d_number;
	private double d_kg;
	private int d_cost;
	private String d_buyDate;
	private double d_totalMoney;
	private String d_state;

	public DealVO() {
		super();
	}

	public DealVO(int d_no, int d_number, double d_kg, int d_cost, String d_buyDate, double d_totalMoney,
			String d_state) {
		super();
		this.d_no = d_no;
		this.d_number = d_number;
		this.d_kg = d_kg;
		this.d_cost = d_cost;
		this.d_buyDate = d_buyDate;
		this.d_totalMoney = d_totalMoney;
		this.d_state = d_state;
	}

	public int getD_no() {
		return d_no;
	}

	public void setD_no(int d_no) {
		this.d_no = d_no;
	}

	public int getD_number() {
		return d_number;
	}

	public void setD_number(int d_number) {
		this.d_number = d_number;
	}

	public double getD_kg() {
		return d_kg;
	}

	public void setD_kg(double d_kg) {
		this.d_kg = d_kg;
	}

	public int getD_cost() {
		return d_cost;
	}

	public void setD_cost(int d_cost) {
		this.d_cost = d_cost;
	}

	public String getD_buyDate() {
		return d_buyDate;
	}

	public void setD_buyDate(String d_buyDate) {
		this.d_buyDate = d_buyDate;
	}

	public double getD_totalMoney() {
		return d_totalMoney;
	}

	public void setD_totalMoney(double d_totalMoney) {
		this.d_totalMoney = d_totalMoney;
	}

	public String getD_state() {
		return d_state;
	}

	public void setD_state(String d_state) {
		this.d_state = d_state;
	}

}

package model;

public class BuyVO {
	private int b_no;
	private int b_number;
	private double b_kg;
	private int b_cost;
	private String b_buyDate;
	private double b_totalMoney;
	private String b_state;

	public BuyVO() {
		super();
	}

	public BuyVO(int b_no, int b_number, double b_kg, int b_cost, String b_buyDate, double b_totalMoney,
			String b_state) {
		super();
		this.b_no = b_no;
		this.b_number = b_number;
		this.b_kg = b_kg;
		this.b_cost = b_cost;
		this.b_buyDate = b_buyDate;
		this.b_totalMoney = b_totalMoney;
		this.b_state = b_state;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getB_number() {
		return b_number;
	}

	public void setB_number(int b_number) {
		this.b_number = b_number;
	}

	public double getB_kg() {
		return b_kg;
	}

	public void setB_kg(double b_kg) {
		this.b_kg = b_kg;
	}

	public int getB_cost() {
		return b_cost;
	}

	public void setB_cost(int b_cost) {
		this.b_cost = b_cost;
	}

	public String getB_buyDate() {
		return b_buyDate;
	}

	public void setB_buyDate(String b_buyDate) {
		this.b_buyDate = b_buyDate;
	}

	public double getB_totalMoney() {
		return b_totalMoney;
	}

	public void setB_totalMoney(double b_totalMoney) {
		this.b_totalMoney = b_totalMoney;
	}

	public String getB_state() {
		return b_state;
	}

	public void setB_state(String b_state) {
		this.b_state = b_state;
	}

}

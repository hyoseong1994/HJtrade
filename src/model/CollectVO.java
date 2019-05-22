package model;

public class CollectVO {
	private int c_no;
	private String c_date;
	private String c_name;
	private String c_businessNumber;
	private String c_business;
	private int c_collectMoney;

	public CollectVO() {
		super();
	}

	public CollectVO(int c_no, String c_date, String c_name, String c_businessNumber, String c_business,
			int c_collectMoney) {
		super();
		this.c_no = c_no;
		this.c_date = c_date;
		this.c_name = c_name;
		this.c_businessNumber = c_businessNumber;
		this.c_business = c_business;
		this.c_collectMoney = c_collectMoney;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public String getC_businessNumber() {
		return c_businessNumber;
	}

	public void setC_businessNumber(String c_businessNumber) {
		this.c_businessNumber = c_businessNumber;
	}

	public String getC_business() {
		return c_business;
	}

	public void setC_business(String c_business) {
		this.c_business = c_business;
	}

	public int getC_collectMoney() {
		return c_collectMoney;
	}

	public void setC_collectMoney(int c_collectMoney) {
		this.c_collectMoney = c_collectMoney;
	}

}

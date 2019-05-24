package model;

public class CollectVO {
	private int c_no;// 수금 번호
	private String c_date;// 수금 일자
	private String c_name;// 판매 거래처 상호명
	private String c_business;// 판매거래
	private String a_businessNumber;// 판매거래처 사업자번호
	private int c_collectMoney;// 수금액

	// 디폴트 생성자
	public CollectVO() {
		super();
	}

	// 전체 생성자
	public CollectVO(int c_no, String c_date, String c_name, String c_business, int c_collectMoney) {
		super();
		this.c_no = c_no;
		this.c_date = c_date;
		this.c_name = c_name;
		this.c_business = c_business;
		this.c_collectMoney = c_collectMoney;
	}

	// getter and setter
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

	public String getA_businessNumber() {
		return a_businessNumber;
	}

	public void setA_businessNumber(String a_businessNumber) {
		this.a_businessNumber = a_businessNumber;
	}

}

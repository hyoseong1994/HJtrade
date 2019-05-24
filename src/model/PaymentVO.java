package model;

public class PaymentVO {
	private int p_no;
	private String p_date;
	private String p_name;
	private String i_businessNumber;
	private String p_business;
	private int p_paymentMoney;

	// 디폴트 생성자
	public PaymentVO() {
		super();
	}

	// 입금날자 제외한 생성자
	public PaymentVO(int p_no, String p_name, String i_businessNumber, String p_business, int p_paymentMoney) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.i_businessNumber = i_businessNumber;
		this.p_business = p_business;
		this.p_paymentMoney = p_paymentMoney;
	}

	// 전체 생성자
	public PaymentVO(int p_no, String p_date, String p_name, String i_businessNumber, String p_business,
			int p_paymentMoney) {
		super();
		this.p_no = p_no;
		this.p_date = p_date;
		this.p_name = p_name;
		this.i_businessNumber = i_businessNumber;
		this.p_business = p_business;
		this.p_paymentMoney = p_paymentMoney;
	}

	// getter and setter
	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getI_businessNumber() {
		return i_businessNumber;
	}

	public void setI_businessNumber(String i_businessNumber) {
		this.i_businessNumber = i_businessNumber;
	}

	public String getP_business() {
		return p_business;
	}

	public void setP_business(String p_business) {
		this.p_business = p_business;
	}

	public int getP_paymentMoney() {
		return p_paymentMoney;
	}

	public void setP_paymentMoney(int p_paymentMoney) {
		this.p_paymentMoney = p_paymentMoney;
	}

}

package model;

public class PaymentVO {
	private int p_no;
	private String p_date;
	private String p_name;
	private String p_businessNumber;
	private String p_business;
	private int p_paymentMoney;

	public PaymentVO() {
		super();
	}

	public PaymentVO(int p_no, String p_name, String p_businessNumber, String p_business, int p_paymentMoney) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_businessNumber = p_businessNumber;
		this.p_business = p_business;
		this.p_paymentMoney = p_paymentMoney;
	}

	public PaymentVO(int p_no, String p_date, String p_name, String p_businessNumber, String p_business,
			int p_paymentMoney) {
		super();
		this.p_no = p_no;
		this.p_date = p_date;
		this.p_name = p_name;
		this.p_businessNumber = p_businessNumber;
		this.p_business = p_business;
		this.p_paymentMoney = p_paymentMoney;
	}

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

	public String getP_businessNumber() {
		return p_businessNumber;
	}

	public void setP_businessNumber(String p_businessNumber) {
		this.p_businessNumber = p_businessNumber;
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

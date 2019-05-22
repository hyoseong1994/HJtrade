package model;

public class AccountVO {
	private int a_no;
	private String a_name;
	private String a_businessNumber;
	private String a_represent;
	private String a_representPhone;
	private String a_charge;
	private String a_chargePhone;
	private String a_address;
	private String a_email;
	private String a_business;
	private int a_collect;

	public AccountVO() {
		super();
	}

	public AccountVO(int a_no, String a_name, String a_businessNumber, String a_business, int a_collect) {
		super();
		this.a_no = a_no;
		this.a_name = a_name;
		this.a_businessNumber = a_businessNumber;
		this.a_business = a_business;
		this.a_collect = a_collect;
	}

	public AccountVO(int a_no, String a_name, String a_businessNumber, String a_represent, String a_representPhone,
			String a_charge, String a_chargePhone, String a_address, String a_email, String a_business) {
		super();
		this.a_no = a_no;
		this.a_name = a_name;
		this.a_businessNumber = a_businessNumber;
		this.a_represent = a_represent;
		this.a_representPhone = a_representPhone;
		this.a_charge = a_charge;
		this.a_chargePhone = a_chargePhone;
		this.a_address = a_address;
		this.a_email = a_email;
		this.a_business = a_business;
	}

	public AccountVO(int a_no, String a_name, String a_businessNumber, String a_represent, String a_representPhone,
			String a_charge, String a_chargePhone, String a_address, String a_email, String a_business, int a_collect) {
		super();
		this.a_no = a_no;
		this.a_name = a_name;
		this.a_businessNumber = a_businessNumber;
		this.a_represent = a_represent;
		this.a_representPhone = a_representPhone;
		this.a_charge = a_charge;
		this.a_chargePhone = a_chargePhone;
		this.a_address = a_address;
		this.a_email = a_email;
		this.a_business = a_business;
		this.a_collect = a_collect;
	}
	

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getA_businessNumber() {
		return a_businessNumber;
	}

	public void setA_businessNumber(String a_businessNumber) {
		this.a_businessNumber = a_businessNumber;
	}

	public String getA_represent() {
		return a_represent;
	}

	public void setA_represent(String a_represent) {
		this.a_represent = a_represent;
	}

	public String getA_representPhone() {
		return a_representPhone;
	}

	public void setA_representPhone(String a_representPhone) {
		this.a_representPhone = a_representPhone;
	}

	public String getA_charge() {
		return a_charge;
	}

	public void setA_charge(String a_charge) {
		this.a_charge = a_charge;
	}

	public String getA_chargePhone() {
		return a_chargePhone;
	}

	public void setA_chargePhone(String a_chargePhone) {
		this.a_chargePhone = a_chargePhone;
	}

	public String getA_address() {
		return a_address;
	}

	public void setA_address(String a_address) {
		this.a_address = a_address;
	}

	public String getA_email() {
		return a_email;
	}

	public void setA_email(String a_email) {
		this.a_email = a_email;
	}

	public String getA_business() {
		return a_business;
	}

	public void setA_business(String a_business) {
		this.a_business = a_business;
	}

	public int getA_collect() {
		return a_collect;
	}

	public void setA_collect(int a_collect) {
		this.a_collect = a_collect;
	}


}

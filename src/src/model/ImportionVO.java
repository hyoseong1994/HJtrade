package model;

public class ImportionVO {
	private int i_no;
	private String i_name;
	private String i_businessNumber;
	private String i_represent;
	private String i_representPhone;
	private String i_charge;
	private String i_chargePhone;
	private String i_address;
	private String i_email;
	private String i_business;
	private int i_payment;
	private String rbImportion;

	// 디폴트 생성자
	public ImportionVO() {
		super();
	}

	// 일련번호 제외한 생성자
	public ImportionVO(String i_name, String i_businessNumber, String i_represent, String i_representPhone,
			String i_charge, String i_chargePhone, String i_address, String i_email, String i_business, int i_payment) {
		super();
		this.i_name = i_name;
		this.i_businessNumber = i_businessNumber;
		this.i_represent = i_represent;
		this.i_representPhone = i_representPhone;
		this.i_charge = i_charge;
		this.i_chargePhone = i_chargePhone;
		this.i_address = i_address;
		this.i_email = i_email;
		this.i_business = i_business;
		this.i_payment = i_payment;
	}

	// 미입금액 제외한 생성자
	public ImportionVO(int i_no, String i_name, String i_businessNumber, String i_represent, String i_representPhone,
			String i_charge, String i_chargePhone, String i_address, String i_email, String i_business) {
		super();
		this.i_no = i_no;
		this.i_name = i_name;
		this.i_businessNumber = i_businessNumber;
		this.i_represent = i_represent;
		this.i_representPhone = i_representPhone;
		this.i_charge = i_charge;
		this.i_chargePhone = i_chargePhone;
		this.i_address = i_address;
		this.i_email = i_email;
		this.i_business = i_business;
	}

	// 전체 생성자
	public ImportionVO(int i_no, String i_name, String i_businessNumber, String i_represent, String i_representPhone,
			String i_charge, String i_chargePhone, String i_address, String i_email, String i_business, int i_payment) {
		super();
		this.i_no = i_no;
		this.i_name = i_name;
		this.i_businessNumber = i_businessNumber;
		this.i_represent = i_represent;
		this.i_representPhone = i_representPhone;
		this.i_charge = i_charge;
		this.i_chargePhone = i_chargePhone;
		this.i_address = i_address;
		this.i_email = i_email;
		this.i_business = i_business;
		this.i_payment = i_payment;
	}

	// getter and setter
	public int getI_no() {
		return i_no;
	}

	public void setI_no(int i_no) {
		this.i_no = i_no;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public String getI_businessNumber() {
		return i_businessNumber;
	}

	public void setI_businessNumber(String i_businessNumber) {
		this.i_businessNumber = i_businessNumber;
	}

	public String getI_represent() {
		return i_represent;
	}

	public void setI_represent(String i_represent) {
		this.i_represent = i_represent;
	}

	public String getI_representPhone() {
		return i_representPhone;
	}

	public void setI_representPhone(String i_representPhone) {
		this.i_representPhone = i_representPhone;
	}

	public String getI_charge() {
		return i_charge;
	}

	public void setI_charge(String i_charge) {
		this.i_charge = i_charge;
	}

	public String getI_chargePhone() {
		return i_chargePhone;
	}

	public void setI_chargePhone(String i_chargePhone) {
		this.i_chargePhone = i_chargePhone;
	}

	public String getI_address() {
		return i_address;
	}

	public void setI_address(String i_address) {
		this.i_address = i_address;
	}

	public String getI_email() {
		return i_email;
	}

	public void setI_email(String i_email) {
		this.i_email = i_email;
	}

	public String getI_business() {
		return i_business;
	}

	public void setI_business(String i_business) {
		this.i_business = i_business;
	}

	public int getI_payment() {
		return i_payment;
	}

	public void setI_payment(int i_payment) {
		this.i_payment = i_payment;
	}

	public String getRbImportion() {
		return rbImportion;
	}

	public void setRbImportion(String rbImportion) {
		this.rbImportion = rbImportion;
	}

}

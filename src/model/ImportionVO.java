package model;

public class ImportionVO {
	private int I_no;
	private String i_name;
	private String i_businessNumber;
	private String i_represent;
	private String i_representPhone;
	private String i_charge;
	private String i_chargePhone;
	private String i_adress;
	private String i_email;
	private String i_business;
	private int i_payment;

	public ImportionVO() {
		super();
	}

	public ImportionVO(int I_no, String i_name, String i_businessNumber, String i_represent, String i_representPhone,
			String i_charge, String i_chargePhone, String i_adress, String i_email, String i_business, int i_payment) {
		super();
		this.I_no = I_no;
		this.i_name = i_name;
		this.i_businessNumber = i_businessNumber;
		this.i_represent = i_represent;
		this.i_representPhone = i_representPhone;
		this.i_charge = i_charge;
		this.i_chargePhone = i_chargePhone;
		this.i_adress = i_adress;
		this.i_email = i_email;
		this.i_business = i_business;
		this.i_payment = i_payment;
	}

	

	public int getI_no() {
		return I_no;
	}

	public void setI_no(int i_no) {
		I_no = i_no;
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

	public String getI_adress() {
		return i_adress;
	}

	public void setI_adress(String i_adress) {
		this.i_adress = i_adress;
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

}

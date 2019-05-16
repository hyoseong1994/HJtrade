package model;

public class AccountVO {
	private String name;
	private String businessNumber;
	private String represent;
	private String representPhone;
	private String charge;
	private String chargePhone;
	private String adress;
	private String email;
	private String business;
	private String payment;

	public AccountVO() {
		super();
	}

	public AccountVO(String name, String businessNumber, String represent, String representPhone, String charge,
			String chargePhone, String adress, String email, String business, String payment) {
		super();
		this.name = name;
		this.businessNumber = businessNumber;
		this.represent = represent;
		this.representPhone = representPhone;
		this.charge = charge;
		this.chargePhone = chargePhone;
		this.adress = adress;
		this.email = email;
		this.business = business;
		this.payment = payment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public String getRepresentPhone() {
		return representPhone;
	}

	public void setRepresentPhone(String representPhone) {
		this.representPhone = representPhone;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getChargePhone() {
		return chargePhone;
	}

	public void setChargePhone(String chargePhone) {
		this.chargePhone = chargePhone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}

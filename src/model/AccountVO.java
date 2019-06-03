package model;

public class AccountVO {
	private int a_no;// 판매거래처 일련번호
	private String a_name;// 판매거래처 상호명
	private String a_businessNumber;// 판매거래처 사업자번호
	private String a_represent;// 판매거래처 대표자
	private String a_representPhone;// 판매거래처 대표자번호
	private String a_charge;// 판매거래처 담당자
	private String a_chargePhone;// 판매거래처 담당자번호
	private String a_address;// 판매거래처 주소
	private String a_email;// 판매거래처 이메일
	private String a_business;// 판매거래처 업태
	private int a_collect;// 수금액

	// 디폴트 생성자
	public AccountVO() {
		super();
	}

	// 대표자, 대표자번호, 담당자, 담당자번호, 주소, 이메일 제외한 생성자
	public AccountVO(int a_no, String a_name, String a_businessNumber, String a_business, int a_collect) {
		super();
		this.a_no = a_no;
		this.a_name = a_name;
		this.a_businessNumber = a_businessNumber;
		this.a_business = a_business;
		this.a_collect = a_collect;
	}

	// 미수금액 제회외한 생성자
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

	// 전체 생성자
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

	// getter and setter
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

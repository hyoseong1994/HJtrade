package model;

public class BuyVO {
	private int b_no; // 매입 번호
	private String b_buyDate; // 매입 주문 날짜
	private String b_date; // 매입 거래 날짜
	private String b_name; // 매입상호명
	private String b_code; // 매입 상품 식별 번호
	private String b_type; // 매입 상품 분류
	private String b_orgin; // 매입 상품 원산지
	private String b_brand; // 매입 상품 브랜드
	private String b_part; // 매입 상품 부위
	private int b_number; // 매입량
	private double b_kg; // 매입 중량
	private int b_cost; // 매입 단가
	private double b_totalMoney; // 매입 총 금액
	private String b_state; // 반품여부
	
	
	public BuyVO() {
		super();
	}
	
	

	public BuyVO(String b_buyDate, String b_name, String b_code, String b_type, String b_orgin, String b_brand,
			String b_part, int b_number, double b_kg, int b_cost) {
		super();
		this.b_buyDate = b_buyDate;
		this.b_name = b_name;
		this.b_code = b_code;
		this.b_type = b_type;
		this.b_orgin = b_orgin;
		this.b_brand = b_brand;
		this.b_part = b_part;
		this.b_number = b_number;
		this.b_kg = b_kg;
		this.b_cost = b_cost;
	}



	public BuyVO(int b_no, String b_buyDate, String b_date, String b_name, String b_code, String b_type, String b_orgin,
			String b_brand, String b_part, int b_number, double b_kg, int b_cost, double b_totalMoney, String b_state) {
		super();
		this.b_no = b_no;
		this.b_buyDate = b_buyDate;
		this.b_date = b_date;
		this.b_name = b_name;
		this.b_code = b_code;
		this.b_type = b_type;
		this.b_orgin = b_orgin;
		this.b_brand = b_brand;
		this.b_part = b_part;
		this.b_number = b_number;
		this.b_kg = b_kg;
		this.b_cost = b_cost;
		this.b_totalMoney = b_totalMoney;
		this.b_state = b_state;
	}



	public int getB_no() {
		return b_no;
	}



	public void setB_no(int b_no) {
		this.b_no = b_no;
	}



	public String getB_buyDate() {
		return b_buyDate;
	}



	public void setB_buyDate(String b_buyDate) {
		this.b_buyDate = b_buyDate;
	}



	public String getB_date() {
		return b_date;
	}



	public void setB_date(String b_date) {
		this.b_date = b_date;
	}



	public String getB_name() {
		return b_name;
	}



	public void setB_name(String b_name) {
		this.b_name = b_name;
	}



	public String getB_code() {
		return b_code;
	}



	public void setB_code(String b_code) {
		this.b_code = b_code;
	}



	public String getB_type() {
		return b_type;
	}



	public void setB_type(String b_type) {
		this.b_type = b_type;
	}



	public String getB_orgin() {
		return b_orgin;
	}



	public void setB_orgin(String b_orgin) {
		this.b_orgin = b_orgin;
	}



	public String getB_brand() {
		return b_brand;
	}



	public void setB_brand(String b_brand) {
		this.b_brand = b_brand;
	}



	public String getB_part() {
		return b_part;
	}



	public void setB_part(String b_part) {
		this.b_part = b_part;
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

	@Override
	public String toString() {
		return "BuyVO [getB_no()=" + getB_no() + ", getB_buyDate()=" + getB_buyDate() + ", getB_date()=" + getB_date()
				+ ", getB_name()=" + getB_name() + ", getB_code()=" + getB_code() + ", getB_type()=" + getB_type()
				+ ", getB_orgin()=" + getB_orgin() + ", getB_brand()=" + getB_brand() + ", getB_part()=" + getB_part()
				+ ", getB_number()=" + getB_number() + ", getB_kg()=" + getB_kg() + ", getB_cost()=" + getB_cost()
				+ ", getB_totalMoney()=" + getB_totalMoney() + ", getB_state()=" + getB_state() + "]";
	}

	
}

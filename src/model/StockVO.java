package model;

public class StockVO {
	private int s_no; // 매입 번호
	private String b_date; // 매입 거래 날짜
	private String b_code; // 매입 상품 식별 번호
	private String p_type; // 매입 상품 분류
	private String p_origin; // 매입 상품 원산지
	private String p_brand; // 매입 상품 브랜드
	private String p_part; // 매입 상품 부위
	private int s_number; // 매입량
	private double s_kg; // 매입 중량
	private int s_cost; // 매입 단가
	private int s_totalMoney; // 매입 총 금액
	private int p_no;//상품 일련번호

	// 디폴트 생성자
	public StockVO() {
		super();
	}

	// 전체 생성자
	public StockVO(int s_no, String b_date, String b_code, String p_type, String p_origin, String p_brand,
			String p_part, int s_number, double s_kg, int s_cost, int s_totalMoney) {
		super();
		this.s_no = s_no;
		this.b_date = b_date;
		this.b_code = b_code;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.s_number = s_number;
		this.s_kg = s_kg;
		this.s_cost = s_cost;
		this.s_totalMoney = s_totalMoney;
	}

	// getter and setter
	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public String getB_code() {
		return b_code;
	}

	public void setB_code(String b_code) {
		this.b_code = b_code;
	}

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getP_origin() {
		return p_origin;
	}

	public void setP_origin(String p_origin) {
		this.p_origin = p_origin;
	}

	public String getP_brand() {
		return p_brand;
	}

	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}

	public String getP_part() {
		return p_part;
	}

	public void setP_part(String p_part) {
		this.p_part = p_part;
	}

	public int getS_number() {
		return s_number;
	}

	public void setS_number(int s_number) {
		this.s_number = s_number;
	}

	public double getS_kg() {
		return s_kg;
	}

	public void setS_kg(double s_kg) {
		this.s_kg = s_kg;
	}

	public int getS_cost() {
		return s_cost;
	}

	public void setS_cost(int s_cost) {
		this.s_cost = s_cost;
	}

	public int getS_totalMoney() {
		return s_totalMoney;
	}

	public void setS_totalMoney(int s_totalMoney) {
		this.s_totalMoney = s_totalMoney;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

}

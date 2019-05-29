package model;

public class DealVO {
	private int d_no;// 출고 일련번호
	private int d_number;// 출고 수량
	private double d_kg;// 출고 중량
	private int d_cost;// 출고 단가
	private String d_buyDate;// 출고 주문일
	private double d_totalMoney;// 출고 총액
	private String s_state;// 재고 상태
	private String a_name;// 판매거래처 상호명
	private String b_code;// 매입상품 일련번호
	private String p_type;// 상품 소, 돼지
	private String p_origin;// 상품 원산지
	private String p_brand;// 상품 브랜드
	private String p_part;// 상품 부위
	private String d_date;// 출고 일자

	// 디폴트 생성자
	public DealVO() {
		super();
	}

	// 전체 생성자
	public DealVO(int d_no, int d_number, double d_kg, int d_cost, String d_buyDate, double d_totalMoney,
			String s_state, String a_name, String b_code, String p_type, String p_origin, String p_brand, String p_part,
			String d_date) {
		super();
		this.d_no = d_no;
		this.d_number = d_number;
		this.d_kg = d_kg;
		this.d_cost = d_cost;
		this.d_buyDate = d_buyDate;
		this.d_totalMoney = d_totalMoney;
		this.s_state = s_state;
		this.a_name = a_name;
		this.b_code = b_code;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.d_date = d_date;
	}

	// getter and setter
	public int getD_no() {
		return d_no;
	}

	public void setD_no(int d_no) {
		this.d_no = d_no;
	}

	public int getD_number() {
		return d_number;
	}

	public void setD_number(int d_number) {
		this.d_number = d_number;
	}

	public double getD_kg() {
		return d_kg;
	}

	public void setD_kg(double d_kg) {
		this.d_kg = d_kg;
	}

	public int getD_cost() {
		return d_cost;
	}

	public void setD_cost(int d_cost) {
		this.d_cost = d_cost;
	}

	public String getD_buyDate() {
		return d_buyDate;
	}

	public void setD_buyDate(String d_buyDate) {
		this.d_buyDate = d_buyDate;
	}

	public double getD_totalMoney() {
		return d_totalMoney;
	}

	public void setD_totalMoney(double d_totalMoney) {
		this.d_totalMoney = d_totalMoney;
	}

	public String getS_state() {
		return s_state;
	}

	public void setS_state(String s_state) {
		this.s_state = s_state;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
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

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String d_date) {
		this.d_date = d_date;
	}

}

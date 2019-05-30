package model;

public class DealVO {
	private int d_no; // 매출 번호
	private String d_dealDate; // 매출 주문 날짜
	private String d_date; // 매출 거래 날짜
	private int a_no; // 거래처 일련 번호
	private String a_name; // 매출 상호명
	private String b_code; // 매입 상품 식별 번호
	private int p_no; // 상품번호
	private int s_no; // 재고번호
	private String p_type; // 매입 상품 분류
	private String p_origin; // 매입 상품 원산지
	private String p_brand; // 매입 상품 브랜드
	private String p_part; // 매입 상품 부위
	private int d_number; // 매입량
	private double d_kg; // 매입 중량
	private int d_cost; // 매입 단가
	private int d_totalMoney; // 매입 총 금액

	public DealVO() {
		super();
	}

	public DealVO(int d_no, String d_dealDate, String d_date, int a_no, String a_name, String b_code, int p_no,
			int s_no, String p_type, String p_origin, String p_brand, String p_part, int d_number, double d_kg,
			int d_cost, int d_totalMoney) {
		super();
		this.d_no = d_no;
		this.d_dealDate = d_dealDate;
		this.d_date = d_date;
		this.a_no = a_no;
		this.a_name = a_name;
		this.b_code = b_code;
		this.p_no = p_no;
		this.s_no = s_no;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.d_number = d_number;
		this.d_kg = d_kg;
		this.d_cost = d_cost;
		this.d_totalMoney = d_totalMoney;
	}

	public DealVO(int d_no, String d_dealDate, String d_date, String a_name, String b_code, String p_type,
			String p_origin, String p_brand, String p_part, int d_number, double d_kg, int d_cost, int d_totalMoney) {
		super();
		this.d_no = d_no;
		this.d_dealDate = d_dealDate;
		this.d_date = d_date;
		this.a_name = a_name;
		this.b_code = b_code;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.d_number = d_number;
		this.d_kg = d_kg;
		this.d_cost = d_cost;
		this.d_totalMoney = d_totalMoney;
	}

	public int getD_no() {
		return d_no;
	}

	public void setD_no(int d_no) {
		this.d_no = d_no;
	}

	public String getD_dealDate() {
		return d_dealDate;
	}

	public void setD_dealDate(String d_dealDate) {
		this.d_dealDate = d_dealDate;
	}

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String d_date) {
		this.d_date = d_date;
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

	public String getB_code() {
		return b_code;
	}

	public void setB_code(String b_code) {
		this.b_code = b_code;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
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

	public int getD_totalMoney() {
		return d_totalMoney;
	}

	public void setD_totalMoney(int d_totalMoney) {
		this.d_totalMoney = d_totalMoney;
	}

}

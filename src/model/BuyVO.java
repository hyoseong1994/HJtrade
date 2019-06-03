package model;

public class BuyVO {
	private int b_no; // 매입 번호
	private String b_buyDate; // 매입 주문 날짜
	private String b_date; // 매입 거래 날짜
	private int i_no; // 거래처번호
	private String i_name; // 매입상호명
	private String b_code; // 매입 상품 식별 번호
	private int p_no; // 상품번호
	private int s_no; // 재고번호
	private String p_type; // 매입 상품 분류
	private String p_origin; // 매입 상품 원산지
	private String p_brand; // 매입 상품 브랜드
	private String p_part; // 매입 상품 부위
	private int b_number; // 매입량
	private double b_kg; // 매입 중량
	private int b_cost; // 매입 단가
	private int b_totalMoney; // 매입 총 금액
	
	//디폴트 생성자
	public BuyVO() {
		super();
	}

	//입고 일련번호와 총가격을 제외한 생성자
	public BuyVO(String b_buyDate, int i_no, String i_name, String b_code, int p_no, String p_type, String p_origin,
			String p_brand, String p_part, int b_number, double b_kg, int b_cost) {
		super();
		this.b_buyDate = b_buyDate;
		this.i_no = i_no;
		this.i_name = i_name;
		this.b_code = b_code;
		this.p_no = p_no;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.b_number = b_number;
		this.b_kg = b_kg;
		this.b_cost = b_cost;
	}
	
	//전체 생성자
	public BuyVO(int b_no, String b_buyDate, String b_date, int i_no, String i_name, String b_code, int p_no, int s_no,
			String p_type, String p_origin, String p_brand, String p_part, int b_number, double b_kg, int b_cost,
			int b_totalMoney) {
		super();
		this.b_no = b_no;
		this.b_buyDate = b_buyDate;
		this.b_date = b_date;
		this.i_no = i_no;
		this.i_name = i_name;
		this.b_code = b_code;
		this.p_no = p_no;
		this.s_no = s_no;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
		this.b_number = b_number;
		this.b_kg = b_kg;
		this.b_cost = b_cost;
		this.b_totalMoney = b_totalMoney;
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

	public int getB_totalMoney() {
		return b_totalMoney;
	}

	public void setB_totalMoney(int b_totalMoney) {
		this.b_totalMoney = b_totalMoney;
	}

	public double getB_totalMoney(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

}

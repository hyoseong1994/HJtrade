package model;

public class ProductVO {
	private int p_no;
	private String p_type;
	private String p_origin;
	private String p_brand;
	private String p_part;

	// 디폴트 생성자
	public ProductVO() {
		super();
	}

	// 일련번호 제외한 생성자
	public ProductVO(String p_type, String p_origin, String p_brand, String p_part) {
		super();
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
	}

	// 전체 생성자
	public ProductVO(int p_no, String p_type, String p_origin, String p_brand, String p_part) {
		super();
		this.p_no = p_no;
		this.p_type = p_type;
		this.p_origin = p_origin;
		this.p_brand = p_brand;
		this.p_part = p_part;
	}

	// getter and setter
	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
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

}

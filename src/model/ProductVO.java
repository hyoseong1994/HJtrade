package model;

public class ProductVO {
	private int p_no;
	private String p_code;
	private String p_type;
	private String p_orgin;
	private String p_brand;
	private String p_part;

	public ProductVO() {
		super();
	}

	public ProductVO(int p_no, String p_code, String p_type, String p_orgin, String p_brand, String p_part) {
		super();
		this.p_no = p_no;
		this.p_code = p_code;
		this.p_type = p_type;
		this.p_orgin = p_orgin;
		this.p_brand = p_brand;
		this.p_part = p_part;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getP_orgin() {
		return p_orgin;
	}

	public void setP_orgin(String p_orgin) {
		this.p_orgin = p_orgin;
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

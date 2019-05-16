package model;

public class ProductVO {
	private String code;
	private String type;
	private String orgin;
	private String brand;
	private String part;

	public ProductVO() {
		super();
	}

	public ProductVO(String code, String type, String orgin, String brand, String part) {
		super();
		this.code = code;
		this.type = type;
		this.orgin = orgin;
		this.brand = brand;
		this.part = part;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

}

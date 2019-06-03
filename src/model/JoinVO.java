package model;

public class JoinVO {

	String id;// 아이디
	String password;// 패스워드
	String name;// 직원명
	String address;// 직원 주소
	String phone;// 핸드폰번호

	// 디폴트 생성자
	public JoinVO() {
		super();
	}

	// 이름, 패스워드, 주소, 전화번호 제외한 생성자
	public JoinVO(String id) {
		super();
		this.id = id;
	}

	// 이름, 주소, 전화번호 제외한 생성자
	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	// 주소, 전화번호 제외한 생성자
	public JoinVO(String id, String password, String name, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	// 전체 생성자
	public JoinVO(String id, String password, String name, String address, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

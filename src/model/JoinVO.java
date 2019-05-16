package model;

public class JoinVO {
	
	String id;
	String password;
	String name;
	String adress;
	String phone;

	public JoinVO() {
		super();
	}
	
	public JoinVO(String id) {
		super();
		this.id = id;
	}

	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	public JoinVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	
	public JoinVO(String id, String password, String name, String adress, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.adress = adress;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

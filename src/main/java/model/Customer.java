package model;

public class Customer {

	private int customer_id;
	private String customer_name;
	private String password;
	private Sex sex;
	private String phone;
	
	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public void setCustomerName(String name) {
		this.customer_name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public int getCustomerId() {
		return this.customer_id;
	}
	
	public String getCustomerName() {
		return this.customer_name;
	}
	
	public String getSex() {
		String sex;
		if(this.sex.equals(Sex.male)) {
			sex = "男";
			return sex;
		}else {
			sex = "女";
			return sex;
		}
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getPassword() {
		return this.password;
	}
}

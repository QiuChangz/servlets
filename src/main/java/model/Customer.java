package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Id
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

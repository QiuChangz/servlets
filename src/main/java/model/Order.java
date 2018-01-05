package model;

public class Order {

	private int order_id;
	private String order_num;
	private int total_price;
	private int customer_id;
	
	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}
	
	public void setOrderNum(String order_num) {
		this.order_num = order_num;
	}
	
	public void setTotalPrice(int total_price) {
		this.total_price = total_price;
	}
	
	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getOrderId() {
		return this.order_id;
	}
	
	public String getOrderNum() {
		return this.order_num;
	}
	
	public int getTotalPrice() {
		return this.total_price;
	}
	
	public int getCustomerId() {
		return this.customer_id;
	}
}

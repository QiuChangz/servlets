package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String order_num;
	private int goods_id;
	private int goods_num;
	private int goods_price;
	private String goods_name;
	private Order order;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_num")
	public Order getOrder() {
		return this.order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setOrderNum(String order_num) {
		this.order_num = order_num;
	}
	
	public void setGoodsId(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public void setGoodsNum(int goods_num) {
		this.goods_num = goods_num;
	}
	
	public void setGoodsPrice(int goods_price) {
		this.goods_price = goods_price;
	}
	
	public void setGoodsName(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public String getOrderNum() {
		return this.order_num;
	}
	
	public int getGoodsId() {
		return this.goods_id;
	}
	
	public int getGoodsNum() {
		return this.goods_num;
	}
	
	public int getGoodsPrice() {
		return this.goods_price;
	}
	
	public String getGoodsName() {
		return this.goods_name;
	}
}

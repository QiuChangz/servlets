package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="goods")
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int goods_id;
	private String goods_name;
	private int goods_price;
	private int goods_num;
	private int goods_total_price;
	
	public void setGoodsId(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public void setGoodsName(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public void setGoodsPrice(int goods_price) {
		this.goods_price = goods_price;
	}
	
	public void setGoodsNum(int goods_num) {
		this.goods_num = goods_num;
	}
	
	public void setGoodsTotalPrice(int goods_total_price) {
		this.goods_total_price = goods_total_price;
	}

	@Id
	public int getGoodsId() {
		return this.goods_id;
	}
	
	public String getGoodsName() {
		return this.goods_name;
	}
	
	public int getGoodsPrice() {
		return this.goods_price;
	}
	
	public int getGoodsNum() {
		return this.goods_num;
	}
	
	public int getGoodsTotalPrice() {
		return this.goods_total_price;
	}
}

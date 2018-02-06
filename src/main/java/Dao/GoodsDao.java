package Dao;

import model.Goods;

public interface GoodsDao extends BaseDao{

	public Goods getGoods(int goods_id);
	public void addGoods(Goods goods);
	public void deleteGoods(int goods_id);
	public void updateGoods(Goods goods);
}

package DaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DaoHelper;
import Dao.GoodsDao;
import model.Goods;

public class GoodsDaoImpl implements GoodsDao {
	
	private Connection connection;
	
	private static GoodsDao goodsDao = new GoodsDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	private GoodsDaoImpl() {
		
	}
	
	public static GoodsDao getInstance() {
		return goodsDao;
	}

	public Goods getGoods(int goods_id) {
		// TODO Auto-generated method stub
		connection = daoHelper.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Goods goods = new Goods();
		try {
			goods.setGoodsId(goods_id);
			statement = connection.createStatement();
			String sql = "select * from myj2ee.goods where goods_id = '"+goods_id+"'";
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				goods.setGoodsName(resultSet.getString("goods_name"));
				goods.setGoodsNum(resultSet.getInt("goods_num"));
				goods.setGoodsPrice(resultSet.getInt("goods_price"));
				goods.setGoodsTotalPrice(resultSet.getInt("goods_total_price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeResult(resultSet);
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
		return goods;
	}

	public void addGoods(Goods goods) {
		// TODO Auto-generated method stub
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "insert into myj2ee.goods (goods_name,goods_price,goods_num,goods_total_price) values ("
							+ "'"+goods.getGoodsName()+"',"
							+ "'"+goods.getGoodsPrice()+"',"
							+ "'"+goods.getGoodsNum()+"',"
							+ "'"+goods.getGoodsTotalPrice()+"')";
			statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
	}

	public void deleteGoods(int goods_id) {
		// TODO Auto-generated method stub
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "delete from myj2ee.goods where goods_id = '"+goods_id+"'";
			statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
	}

	public void updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "update myj2ee.goods set "
							+ "goods_name = '"+goods.getGoodsName()+"',"
							+ "goods_price = '"+goods.getGoodsPrice()+"',"
							+ "goods_num = '"+goods.getGoodsNum()+"',"
							+ "goods_total_price = '"+goods.getGoodsTotalPrice()+"'"
							+ " where goods_id = '"+goods.getGoodsId()+"'";
			statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
	}

}

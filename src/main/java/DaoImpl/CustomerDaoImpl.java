package DaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.CustomerDao;
import Dao.DaoHelper;
import model.Customer;
import model.Sex;

public class CustomerDaoImpl implements CustomerDao {

	private Connection connection;
	private static CustomerDao customerDao = new CustomerDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	private CustomerDaoImpl() {
		
	}
	
	public static CustomerDao getInstance() {
		return customerDao;
	}
	
	public Customer getCustomer(String customer_name) {
		Customer customer = new Customer();
		connection = daoHelper.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			String sql = "select * from customer where customer_name = '"+customer_name+"'";
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				customer.setCustomerId(resultSet.getInt("customer_id"));
				customer.setCustomerName(resultSet.getString("customer_name"));
				customer.setPassword(resultSet.getString("password"));
				customer.setPhone(resultSet.getString("phone"));
				if(resultSet.getString("sex").equals("男")) {
					customer.setSex(Sex.male);;
				}else {
					customer.setSex(Sex.female);
				}
			}else {
				System.out.println("找不到用户！");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeResult(resultSet);
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
		return customer;
	}

	public void addCustomer(Customer customer) {
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "insert into customer(customer_name,password,phone,sex) values ("
							+ "'"+customer.getCustomerName()+"',"
							+ "'"+customer.getPassword()+"',"
							+ "'"+customer.getPhone()+"',"
							+ "'"+customer.getSex()+"')";
			statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
		
	}
	
	public void deleteCustomer(int customer_id) {
		// TODO Auto-generated method stub
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "delete from customer where customer_id = '"+customer_id+"'";
			statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoHelper.closeStatement(statement);
			daoHelper.closeConnection(connection);
		}
	}
	
	public void updateCustomer(Customer customer) {
		connection = daoHelper.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "update customer set customer_name = '"+customer.getCustomerName()+"',"
					+ "phone = '"+ customer.getPhone()+"',"
					+ "password = '"+customer.getPassword()+"',"
					+ "sex = '"+customer.getSex()+"' "
					+ "where customer_id = '"+customer.getCustomerId()+"'";
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

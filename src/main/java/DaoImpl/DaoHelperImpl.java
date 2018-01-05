package DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DaoHelper;

public class DaoHelperImpl implements DaoHelper{

	private static DaoHelper daoHelper = new DaoHelperImpl();
	private Connection connection;
	
	private DaoHelperImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DaoHelper getInstance() {
		return daoHelper;
	}
	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myj2ee?useSSL=false","root","741236985qcz");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}

	public void closeConnection(Connection connection) {
		// TODO Auto-generated method stub
		if(connection!=null)
		{
			try 
			{
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void closeStatement(Statement statement) {
		// TODO Auto-generated method stub
		if(statement!=null)
		{
			try
			{
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeResult(ResultSet resultSet) {
		// TODO Auto-generated method stub
		if(resultSet!=null)
		{
			try
			{
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

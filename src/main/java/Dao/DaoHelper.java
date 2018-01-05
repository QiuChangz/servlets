package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DaoHelper 
{
	public Connection getConnection();
	public void closeConnection(Connection connection);
	public void closeStatement(Statement statement);
	public void closeResult(ResultSet resultSet);
}

package Dao;

import java.util.ArrayList;

import model.Customer;

public interface CustomerDao {

	public ArrayList<Customer> getCustomers();
	public void addCustomer(Customer customer);
	public void deleteCustomer(int customer_id);
	public void updateCustomer(Customer customer);
	public void showCustomer(int customer_id);
	
}

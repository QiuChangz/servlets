package Dao;

import model.Customer;

public interface CustomerDao {

	public void addCustomer(Customer customer);
	public void deleteCustomer(int customer_id);
	public void updateCustomer(Customer customer);
	public Customer getCustomer(String customer_name);
	
}

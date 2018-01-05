package factory;

import service.CustomerManagerService;
import serviceImpl.CustomerManagerServiceImpl;

public class ServiceFactory {

	public static CustomerManagerService getCustomerManagerService() {
		return CustomerManagerServiceImpl.getInstance();
	}
}

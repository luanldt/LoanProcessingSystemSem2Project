package dao;

import entities.Customers;

public class CustomersDAO extends AbstractModel<Customers>{
	public CustomersDAO() {
		super(Customers.class);
	}
	public String getCustomerNameByID(int customerID){
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (String) sessionFactory.getCurrentSession()
					.createQuery("select c.customerName from Customers c where c.customerId = :customerID")
					.setInteger("customerID", customerID)
					.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
}
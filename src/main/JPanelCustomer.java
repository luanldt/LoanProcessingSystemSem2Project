package main;

import dao.CustomersDAO;
import entities.Customers;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelCustomer extends AbstractJPanel {

	private static final long serialVersionUID = 1L;

	public JPanelCustomer() {
		super(JPanelCustomer.class);
		setName("Customers");
		loadTable();
	}

	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String[] columns = { "customerId", "customerName", "address", "phoneNumber", "email", "accountNumber",
				"identityCardNo", "notes", "salary" };
		customTableModel.addColumn(columns);

		for (Customers customers : new CustomersDAO().findAll()) {
			customTableModel.addRow(new Object[] { customers.getCustomerId(), customers.getCustomerName(),
					customers.getAddress(), customers.getPhoneNumber(), customers.getEmail(), customers.getAccountNumber(),
					customers.getIdentityCardNo(), customers.getNotes(), customers.getSalary() });
		}
		setModel(customTableModel);
	}

}

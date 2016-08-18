package moderator;

import dao.ContractsDAO;
import dao.CustomersDAO;
import dao.DepartmentDAO;
import dao.LoanTypesDAO;
import dao.PaymentDAO;
import dao.StaffsDAO;
import entities.Contracts;
import entities.Customers;
import entities.Department;
import entities.LoanTypes;
import entities.Payment;
import entities.Staffs;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelApproveDeleting extends AbstractJPanel{

	private static final long serialVersionUID = 1L;
	private String tableName;
	public JPanelApproveDeleting(String tableName) {
		super(JPanelApproveDeleting.class);
		this.tableName = tableName;
		loadTable();
	}
	
	@Override
	public void loadTable() {
		String[] cases = {"mntmLoanTypes", "mntmCustomers", "mntmDepartments", "mntmStaffs", "mntmContracts", "mntmPayments"};
		CustomTableModel customTableModel = new CustomTableModel();
		customTableModel.getColumnClass(0);
		int i;
		for(i = 0; i < cases.length; i++)
		    if(tableName.contains(cases[i])) break;

		switch(i) {
		    case 0: //mntmLoanTypes
		    	String []typeColumns = { "Selected", "ID", "Name", "InterestRate", "LoanBase", "LoanRate"};
				customTableModel.addColumn(typeColumns);
				
				for(LoanTypes loanTypes : new LoanTypesDAO().findPending()) {
					customTableModel.addRow(new Object[] {
							Boolean.FALSE, loanTypes.getLoanTypeId(), loanTypes.getLoanTypeName(), loanTypes.getInterestRate(), loanTypes.getLoanBase(), loanTypes.getLoanRate()
					});
				}
		    break;
		    case 1: //mntmCustomers
		    	String[] customerColumns = { "Selected", "customerId", "customerName", "address", "phoneNumber", "email", "accountNumber",
						"identityCardNo", "notes", "salary" };
				customTableModel.addColumn(customerColumns);

				for (Customers customers : new CustomersDAO().findPending()) {
					customTableModel.addRow(new Object[] {Boolean.FALSE, customers.getCustomerId(), customers.getCustomerName(),
							customers.getAddress(), customers.getPhoneNumber(), customers.getEmail(), customers.getAccountNumber(),
							customers.getIdentityCardNo(), customers.getNotes(), customers.getSalary() });
				}
		    break;
		    case 2: //mntmDepartments
		    	String[] departmentColumns = { "Selected", "Department Id", "Department Name", "Loan Type Name" };
				customTableModel.addColumn(departmentColumns);

				for (Department department : new DepartmentDAO().findPending()) {
					customTableModel.addRow(new Object[] {Boolean.FALSE, department.getDepartmentId(), department.getDepartmentName(),
							department.getLoanTypes().getLoanTypeName() });
				}
		    break;
		    case 3: //mntmStaffs
		    	String[] staffColumns = { "Selected", "StaffID", "StaffName", "Username", "DepartmentName", "IsAdmin" };
				customTableModel.addColumn(staffColumns);

				for (Staffs staffs : new StaffsDAO().findPending()) {
					customTableModel.addRow(new Object[] {Boolean.FALSE, staffs.getStaffId(), staffs.getStaffName(), staffs.getUsername(),
							staffs.getDepartment().getDepartmentName(), staffs.isIsAdmin() });
				}
		    break;
		    case 4: //mntmContracts
		    	String [] contractColumns = { "Selected" , "ID", "Contract Date", "Customer Name", "Staff Name", "Loan Type", "Maturity Period", "Paid Times", 
						"Loan Term", "Initial Amount", "Remain Amount", "Due Date", "Initiate Date", "Loan Max"
				};
					customTableModel.addColumn(contractColumns);
				
				for(Contracts contracts: new ContractsDAO().findPending()) {
					customTableModel.addRow(new Object[] {
							false, contracts.getContractId(), contracts.getContractDate().toString(), contracts.getCustomers().getCustomerName(),
							contracts.getStaffs().getStaffName(), contracts.getLoanTypes().getLoanTypeName(), contracts.getMaturityPeriod(),
							contracts.getPaidTimes(), contracts.getLoanTerm(), contracts.getInitialAmount(), contracts.getRemainAmount(),
							contracts.getDueDate().toString(), contracts.getInitiateDate().toString(), contracts.getLoanMax()
					});
				}
				break;
		    case 5: //mntmPayments
				String[] paymentColumns = { "Selected", "PaymentID", "contractID", "PaymentType", "PaymentDate", "duePeriod", "PaymentAmount",
						"FineRate", "FineAmount", "staffID", "paid" };
				customTableModel.addColumn(paymentColumns);

				for (Payment payment : new PaymentDAO().findPending()) {
					customTableModel.addRow(new Object[] {Boolean.FALSE, payment.getPaymentId(), payment.getContracts().getContractId(),
							payment.getPaymentType(), payment.getPaymentDate(), payment.getDuePeriod(),
							payment.getPaymentAmount(), payment.getFineRate(), payment.getFineAmount(),
							payment.getStaffs().getStaffId(), payment.isPaid() });
				}

		    default:
		        System.out.println("do nothing");
		}
		setModel(customTableModel);
	}

	public Class<?> getColumnClass(int columnIndex) {
	    @SuppressWarnings("rawtypes")
		Class classType = String.class;
	    switch (columnIndex) {
	        case 0:
	            classType = Boolean.class;
	            break;
	    }
	    return classType;
	}
}

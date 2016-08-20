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

public class JPanelApproveDeleting extends AbstractJPanel {
	private static final int LOANTYPE = 0;
	private static final int CUSTOMER = 1;
	private static final int DEPARTMENT = 2;
	private static final int STAFF = 3;
	private static final int CONTRACT = 4;
	private static final int PAYMENT = 5;

	private static final long serialVersionUID = 1L;
	private String tableName;

	public JPanelApproveDeleting(String tableName) {
		super(JPanelApproveDeleting.class);
		this.tableName = tableName;
		loadTable();
	}

	@Override
	public void loadTable() {
		String[] cases = { "Loan Types", "Customers", "Departments", "Staffs", "Contracts", "Payments" };
		CustomTableModel customTableModel = new CustomTableModel();
		customTableModel.getColumnClass(0);
		int i;
		for (i = 0; i < cases.length; i++)
			if (tableName.contains(cases[i]) && tableName.contains("Mod"))
				break;

		switch (i) {
		case LOANTYPE: // mntmLoanTypes
			String[] typeColumns = { "ID", "Name", "InterestRate", "LoanBase", "LoanRate", "Selected" };
			customTableModel.addColumn(typeColumns);

			for (LoanTypes loanTypes : new LoanTypesDAO().findPending()) {
				customTableModel.addRow(new Object[] { loanTypes.getLoanTypeId(), loanTypes.getLoanTypeName(),
						loanTypes.getInterestRate(), loanTypes.getLoanBase(), loanTypes.getLoanRate(), Boolean.FALSE });
			}
			break;
		case CUSTOMER: // mntmCustomers
			String[] customerColumns = { "customerId", "customerName", "address", "phoneNumber", "email",
					"accountNumber", "identityCardNo", "notes", "salary", "Selected" };
			customTableModel.addColumn(customerColumns);

			for (Customers customers : new CustomersDAO().findPending()) {
				customTableModel.addRow(new Object[] { customers.getCustomerId(), customers.getCustomerName(),
						customers.getAddress(), customers.getPhoneNumber(), customers.getEmail(),
						customers.getAccountNumber(), customers.getIdentityCardNo(), customers.getNotes(),
						customers.getSalary(), Boolean.FALSE });
			}
			break;
		case DEPARTMENT: // mntmDepartments
			String[] departmentColumns = { "Department Id", "Department Name", "Loan Type Name", "Selected" };
			customTableModel.addColumn(departmentColumns);

			for (Department department : new DepartmentDAO().findPending()) {
				customTableModel.addRow(new Object[] { department.getDepartmentId(), department.getDepartmentName(),
						department.getLoanTypes().getLoanTypeName(), Boolean.FALSE });
			}
			break;
		case STAFF: // mntmStaffs
			String[] staffColumns = { "StaffID", "StaffName", "Username", "DepartmentName", "Role", "Selected" };
			customTableModel.addColumn(staffColumns);

			for (Staffs staffs : new StaffsDAO().findPending()) {
				customTableModel.addRow(new Object[] { staffs.getStaffId(), staffs.getStaffName(), staffs.getUsername(),
						staffs.getDepartment().getDepartmentName(), staffs.getRole(), Boolean.FALSE });
			}
			break;
		case CONTRACT: // mntmContracts
			String[] contractColumns = { "ID", "Contract Date", "Customer Name", "Staff Name", "Loan Type",
					"Maturity Period", "Paid Times", "Loan Term", "Initial Amount", "Remain Amount", "Due Date",
					"Initiate Date", "Loan Max", "Selected" };
			customTableModel.addColumn(contractColumns);

			for (Contracts contracts : new ContractsDAO().findPending()) {
				customTableModel.addRow(new Object[] { contracts.getContractId(),
						contracts.getContractDate().toString(), contracts.getCustomers().getCustomerName(),
						contracts.getStaffs().getStaffName(), contracts.getLoanTypes().getLoanTypeName(),
						contracts.getMaturityPeriod(), contracts.getPaidTimes(), contracts.getLoanTerm(),
						contracts.getInitialAmount(), contracts.getRemainAmount(), contracts.getDueDate().toString(),
						contracts.getInitiateDate().toString(), contracts.getLoanMax(), Boolean.FALSE });
			}
			break;
		case PAYMENT: // mntmPayments
			String[] paymentColumns = { "PaymentID", "contractID", "PaymentType", "PaymentDate", "duePeriod",
					"PaymentAmount", "FineRate", "FineAmount", "staffID", "paid", "Selected" };
			customTableModel.addColumn(paymentColumns);

			for (Payment payment : new PaymentDAO().findPending()) {
				customTableModel.addRow(new Object[] { payment.getPaymentId(), payment.getContracts().getContractId(),
						payment.getPaymentType(), payment.getPaymentDate(), payment.getDuePeriod(),
						payment.getPaymentAmount(), payment.getFineRate(), payment.getFineAmount(),
						payment.getStaffs().getStaffId(), payment.isPaid(), Boolean.FALSE });
			}
		default:
			System.out.println("do nothing");
		}
		setModel(customTableModel);
	}
}
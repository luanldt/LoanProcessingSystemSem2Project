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

public class ArchiveData {
	public static void processArchive(String state, String name, int id) {
		String[] cases = { "Loan Types", "Customers", "Departments", "Staffs", "Contracts", "Payments" };
		int i;
		for (i = 0; i < cases.length; i++)
			if (name.contains(cases[i]) && name.contains("Mod"))
				break;

		switch (i) {
		case 0:
			LoanTypesDAO loanTypesDAO = new LoanTypesDAO();
			LoanTypes loanType = loanTypesDAO.find(id);
			if (state == "Archive") {
				loanType.setIsArchive(true);
				loanTypesDAO.update(loanType);
			} else if (state == "Restore") {
				loanType.setIsArchive(false);
				loanTypesDAO.update(loanType);
			} else if (state == "Delete") {
				loanTypesDAO.delete(loanType);
			}
			break;
		case 1:
			CustomersDAO customersDAO = new CustomersDAO();
			Customers customer = customersDAO.find(id);
			if (state == "Archive") {
				customer.setIsArchive(true);
				customersDAO.update(customer);
			} else if (state == "Restore") {
				customer.setIsArchive(false);
				customersDAO.update(customer);
			} else if (state == "Delete") {
				customersDAO.delete(customer);
			}
			break;
		case 2:
			DepartmentDAO departmentDAO = new DepartmentDAO();
			Department department = departmentDAO.find(id);
			if (state == "Archive") {
				department.setIsArchive(true);
				departmentDAO.update(department);
			} else if (state == "Restore") {
				department.setIsArchive(false);
				departmentDAO.update(department);
			} else if (state == "Delete") {
				departmentDAO.delete(department);
			}
			break;
		case 3:
			StaffsDAO staffsDAO = new StaffsDAO();
			Staffs staff = staffsDAO.find(id);
			if (state == "Archive") {
				staff.setIsArchive(true);
				staffsDAO.update(staff);
			} else if (state == "Restore") {
				staff.setIsArchive(false);
				staffsDAO.update(staff);
			} else if (state == "Delete") {
				staffsDAO.delete(staff);
			}
			break;
		case 4:
			ContractsDAO contractsDAO = new ContractsDAO();
			Contracts contract = contractsDAO.find(id);
			if (state == "Archive") {
				contract.setIsArchive(true);
				contractsDAO.update(contract);
			} else if (state == "Restore") {
				contract.setIsArchive(false);
				contractsDAO.update(contract);
			} else if (state == "Delete") {
				contractsDAO.delete(contract);
			}
			break;
		case 5:
			PaymentDAO paymentDAO = new PaymentDAO();
			Payment payment = paymentDAO.find(id);
			if (state == "Archive") {
				payment.setIsArchive(true);
				paymentDAO.update(payment);
			} else if (state == "Restore") {
				payment.setIsArchive(false);
				paymentDAO.update(payment);
			} else if (state == "Delete") {
				paymentDAO.delete(payment);
			}
			break;
		}
	}
}
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
		switch (name) {
		case "Loan Types":
			LoanTypesDAO loanTypesDAO = new LoanTypesDAO();
			LoanTypes loanType = loanTypesDAO.find(id);
			if(state == "Archive"){
				loanType.setIsArchive(true);
				loanTypesDAO.update(loanType);	
			}else if(state == "Restore"){
				loanType.setIsArchive(false);
				loanTypesDAO.update(loanType);
			}else if(state == "Delete"){
				loanTypesDAO.delete(loanType);
			}
			break;
		case "Staffs":
			StaffsDAO staffsDAO = new StaffsDAO();
			Staffs staff = staffsDAO.find(id);
			if(state == "Archive"){
				staff.setIsArchive(true);
				staffsDAO.update(staff);	
			}else if(state == "Restore"){
				staff.setIsArchive(false);
				staffsDAO.update(staff);
			}else if(state == "Delete"){
				staffsDAO.delete(staff);
			}
			break;
		case "Departments":
			DepartmentDAO departmentDAO = new DepartmentDAO();
			Department department = departmentDAO.find(id);
			if(state == "Archive"){
				department.setIsArchive(true);
				departmentDAO.update(department);	
			}else if(state == "Restore"){
				department.setIsArchive(false);
				departmentDAO.update(department);
			}else if(state == "Delete"){
				departmentDAO.delete(department);
			}
			break;
		case "Contracts":
			ContractsDAO contractsDAO = new ContractsDAO();
			Contracts contract = contractsDAO.find(id);
			if(state == "Archive"){
				contract.setIsArchive(true);
				contractsDAO.update(contract);	
			}else if(state == "Restore"){
				contract.setIsArchive(false);
				contractsDAO.update(contract);
			}else if(state == "Delete"){
				contractsDAO.delete(contract);
			}
			break;
		case "Customers":
			CustomersDAO customersDAO = new CustomersDAO();
			Customers customer = customersDAO.find(id);
			if(state == "Archive"){
				customer.setIsArchive(true);
				customersDAO.update(customer);	
			}else if(state == "Restore"){
				customer.setIsArchive(false);
				customersDAO.update(customer);
			}else if(state == "Delete"){
				customersDAO.delete(customer);
			}
			break;
		case "Payments":
			PaymentDAO paymentDAO = new PaymentDAO();
			Payment payment = paymentDAO.find(id);
			if(state == "Archive"){
				payment.setIsArchive(true);
				paymentDAO.update(payment);	
			}else if(state == "Restore"){
				payment.setIsArchive(false);
				paymentDAO.update(payment);
			}else if(state == "Delete"){
				paymentDAO.delete(payment);
			}
			break;
		}
	}
}
package model;

import main.JDialogAddLoanCustomer;

public class ProcessAction {

	// tableName là tên của jInternal frame đã được set từ trước để lúc truyền vào
	// thì phương thức biết là
	// nút button đang đứng ở jinternal frame nào
	public void addAction(String name) {
		switch(name) {
		case "LoanType":
			new JDialogAddLoanCustomer().setVisible(true);
			break;
		}
	}
}

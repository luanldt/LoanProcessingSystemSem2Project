package main;

import dao.LoanTypesDAO;
import entities.LoanTypes;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelLoanType extends AbstractJPanel{

	private static final long serialVersionUID = 1L;

	public JPanelLoanType() {
		super("LoanTypes");
		JPanelLoanType.loadTable();
	}
	
	public static void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String []columns = {"ID", "Name", "InterestRate", "LoanBase", "LoanRate"};
		for(String column : columns) {
			customTableModel.addColumn(column);
		}
		
		for(LoanTypes loanTypes : new LoanTypesDAO().findAll()) {
			customTableModel.addRow(new Object[] {
				loanTypes.getLoanTypeId(), loanTypes.getLoanTypeName(), loanTypes.getInterestRate(), loanTypes.getLoanBase(), loanTypes.getLoanRate()
			});
		}
		setModel(customTableModel);
	}

}

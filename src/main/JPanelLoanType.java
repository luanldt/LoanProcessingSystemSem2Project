package main;

import dao.LoanTypesDAO;
import entities.LoanTypes;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelLoanType extends AbstractJPanel{

	private static final long serialVersionUID = 1L;

	public JPanelLoanType() {
		super(JPanelLoanType.class);
		loadTable();
	}
	
	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String []columns = {"ID", "Name", "InterestRate", "LoanBase", "LoanRate"};
		customTableModel.addColumn(columns);
		
		for(LoanTypes loanTypes : new LoanTypesDAO().findAll()) {
			customTableModel.addRow(new Object[] {
				loanTypes.getLoanTypeId(), loanTypes.getLoanTypeName(), loanTypes.getInterestRate(), loanTypes.getLoanBase(), loanTypes.getLoanRate()
			});
		}
		setModel(customTableModel);
	}

}

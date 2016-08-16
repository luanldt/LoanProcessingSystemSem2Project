package main;

import dao.ContractsDAO;
import entities.Contracts;
import factory.AbstractJPanel;
import model.CustomTableModel;

@SuppressWarnings("serial")
public class JPanelContract extends AbstractJPanel {

	/**
	 * Create the panel.
	 */
	public JPanelContract() {
		super(JPanelContract.class);
		loadTable();
	}
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String []columns = {"ID", "Contract Date", "Customer Name", "Staff Name", "Loan Type", "Maturity Period", "Paid Times", 
				"Loan Term", "Initial Amount", "Remain Amount", "Due Date", "Initiate Date", "Loan Max"
		};
			customTableModel.addColumn(columns);
		
		for(Contracts contracts: new ContractsDAO().findAll()) {
			customTableModel.addRow(new Object[] {
					contracts.getContractId(), contracts.getContractDate().toString(), contracts.getCustomers().getCustomerName(),
					contracts.getStaffs().getStaffName(), contracts.getLoanTypes().getLoanTypeName(), contracts.getMaturityPeriod(),
					contracts.getPaidTimes(), contracts.getLoanTerm(), contracts.getInitialAmount(), contracts.getRemainAmount(),
					contracts.getDueDate().toString(), contracts.getInitiateDate().toString(), contracts.getLoanMax()
			});
		}
		setModel(customTableModel);
	}
}

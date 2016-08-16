package main;

import dao.DepartmentDAO;
import entities.Department;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelDepartment extends AbstractJPanel {
	private static final long serialVersionUID = 1L;

	public JPanelDepartment() {
		super(JPanelDepartment.class);
		loadTable();
	}

	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String[] columns = { "Department Id", "Department Name", "Loan Type Name" };
		customTableModel.addColumn(columns);

		for (Department department : new DepartmentDAO().findAll()) {
			customTableModel.addRow(new Object[] { department.getDepartmentId(), department.getDepartmentName(),
					department.getLoanTypes().getLoanTypeName() });
		}
		setModel(customTableModel);
	}
}

package main;

import dao.StaffsDAO;
import entities.Staffs;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelStaff extends AbstractJPanel {
	private static final long serialVersionUID = 1L;

	public JPanelStaff() {
		super(JPanelStaff.class);
		loadTable();
	}

	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String[] columns = { "StaffID", "StaffName", "Username", "DepartmentName" };
		customTableModel.addColumn(columns);

		for (Staffs staffs : new StaffsDAO().findAll()) {
			customTableModel.addRow(new Object[] { staffs.getStaffId(), staffs.getStaffName(), staffs.getUsername(),
					staffs.getDepartment().getDepartmentName() });
		}
		setModel(customTableModel);
	}

}

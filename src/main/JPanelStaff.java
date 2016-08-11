package main;

import dao.StaffsDAO;
import entities.Staffs;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelStaff extends AbstractJPanel {
	private static final long serialVersionUID = 1L;

	public JPanelStaff() {
		super("Staffs");
		JPanelStaff.loadTable();
	}

	public static void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String []columns = {"StaffID", "StaffName", "Username", "DepartmentName", "IsAdmin"};
		for(String column : columns) {
			customTableModel.addColumn(column);
		}
		
		for(Staffs staffs : new StaffsDAO().findAll()) {
			customTableModel.addRow(new Object[] {
				staffs.getStaffId(), staffs.getStaffName(), staffs.getUsername(), staffs.getDepartment().getDepartmentName(), staffs.isIsAdmin()
			});
		}
		setModel(customTableModel);
	}

}

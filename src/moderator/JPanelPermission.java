package moderator;

import dao.StaffsDAO;
import entities.Staffs;
import factory.AbstractJPanel;
import model.CustomTableModel;

@SuppressWarnings("serial")
public class JPanelPermission extends AbstractJPanel {

	/**
	 * Create the panel.
	 */
	public JPanelPermission() {
		super(JPanelPermission.class);
		loadTable();
	}

	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		customTableModel.getColumnClass(0);
		String[] staffColumns = { "StaffID", "StaffName", "Username", "Department", "Role" };
		customTableModel.addColumn(staffColumns);

		for (Staffs staffs : new StaffsDAO().findAll()) {
			customTableModel.addRow(new Object[] { staffs.getStaffId(), staffs.getStaffName(), staffs.getUsername(),
					staffs.getDepartment().getDepartmentName(), staffs.getRole() });
		}
		setModel(customTableModel);
	}

}

package controlExtension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.CustomersDAO;
import dao.LoanTypesDAO;
import dao.StaffsDAO;
import entities.Customers;
import entities.LoanTypes;
import entities.Staffs;
import model.CustomTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

@SuppressWarnings("serial")
public class JDialogLookup extends JDialog {
	private JScrollPane scrollPane;
	public static JTable table;
	private static TableModel tableModel;
	@SuppressWarnings("rawtypes")
	private HashMap componentMap;

	/**
	 * Create the dialog.
	 */
	public JDialogLookup() {
		setUndecorated(true);
	}

	@SuppressWarnings("static-access")
	public JDialogLookup(String keyLookUp, JTextFieldList jTextFieldList) {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setName("scrollPane");
		getContentPane().add(this.scrollPane, BorderLayout.CENTER);

		JDialogLookup.table = new JTable();
		JDialogLookup.table.setName("table");
		JDialogLookup.table.setRowSelectionAllowed(true);
		JDialogLookup.table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				do_thisTable_keyPressed(arg0, jTextFieldList);
			}
		});
		this.scrollPane.setViewportView(JDialogLookup.table);
		createComponentMap(jTextFieldList.getParent());
		loadData(keyLookUp);
		this.setAlwaysOnTop(true);
	}

	@SuppressWarnings("unchecked")
	private void createComponentMap(Container container) {
		componentMap = new HashMap<String, Component>();
		Component[] components = container.getComponents();
		for (int i = 0; i < components.length; i++) {
			componentMap.put(components[i].getName(), components[i]);
		}
	}

	public Component getComponentByName(String name) {
		if (componentMap.containsKey(name)) {
			return (Component) componentMap.get(name);
		} else
			return null;
	}

	public static void refreshData(String searchKey) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(rowSorter);

		if (searchKey.length() == 0)
			rowSorter.setRowFilter(null);
		else
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchKey));
	}

	private void loadData(String keyLookUp) {
		CustomTableModel customTableModel = new CustomTableModel();
		switch (keyLookUp) {
		case "customer": {
			String[] columns = { "Customer ID", "Customer name" };
			customTableModel.addColumn(columns);

			for (Customers customers : new CustomersDAO().findAll()) {
				customTableModel.addRow(new Object[] { customers.getCustomerId(), customers.getCustomerName() });
			}
		}
			break;
		case "type": {
			String[] columns = { "Loan type ID", "Loan type name", "Loan type base", "Loan type rate" };
			customTableModel.addColumn(columns);

			for (LoanTypes loanTypes : new LoanTypesDAO().findAll()) {
				customTableModel.addRow(new Object[] { loanTypes.getLoanTypeId(), loanTypes.getLoanTypeName(),
						loanTypes.getLoanBase(), loanTypes.getLoanRate() });
			}
		}
			break;
		case "staff": {
			String[] columns = { "Staff ID", "Staff name", "Department" };
			customTableModel.addColumn(columns);

			for (Staffs staffs : new StaffsDAO().findAll()) {
				customTableModel.addRow(new Object[] { staffs.getStaffId(), staffs.getStaffName(),
						staffs.getDepartment().getDepartmentName() });
			}
		}
			break;
		default:
			break;
		}
		table.setModel(customTableModel);
		tableModel = customTableModel;
	}

	@SuppressWarnings("static-access")
	protected void do_thisTable_keyPressed(KeyEvent arg0, JTextFieldList jTextFieldList) {
		if (arg0.getKeyCode() != KeyEvent.VK_DOWN && arg0.getKeyCode() != KeyEvent.VK_UP
				&& arg0.getKeyCode() != KeyEvent.VK_ENTER && arg0.getKeyCode() != KeyEvent.VK_TAB) {
			this.transferFocus();
			jTextFieldList.requestFocus();
		} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER || arg0.getKeyCode() == KeyEvent.VK_TAB) {
			String value = JDialogLookup.table.getValueAt(JDialogLookup.table.getSelectedRow(), 0).toString();
			jTextFieldList.setText(value);

			switch (jTextFieldList.getName()) {
			case "txtCustomerId":
				CustomersDAO customersDAO = new CustomersDAO();
				((JLabel) getComponentByName("lblCustomerName")).setText(
						String.valueOf(customersDAO.getCustomerNameByID(Integer.parseInt(value))));
				System.out.println(customersDAO.getCustomerNameByID(Integer.parseInt(value)));
				break;
			case "txtStaffId":
				StaffsDAO staffsDAO = new StaffsDAO();
				((JLabel) getComponentByName("lblStaffName")).setText(
						String.valueOf(staffsDAO.getStaffNameByID(Integer.parseInt(value))));
				System.out.println(staffsDAO.getStaffNameByID(Integer.parseInt(value)));
				break;
			case "txtTypeId":
				LoanTypesDAO loanTypesDAO = new LoanTypesDAO();
				((JLabel) getComponentByName("lblLoanType")).setText(
						String.valueOf(loanTypesDAO.getLoanTypeNameByID(Integer.parseInt(value))));
				System.out.println(loanTypesDAO.getLoanTypeNameByID(Integer.parseInt(value)));
				break;
			}
			
			this.transferFocus();
			jTextFieldList.transferFocus();
			this.setVisible(false);
		}
	}
}
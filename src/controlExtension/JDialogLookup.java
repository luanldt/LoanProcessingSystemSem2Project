package controlExtension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
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

@SuppressWarnings("serial")
public class JDialogLookup extends JDialog {
	private JScrollPane scrollPane;
	public static JTable table;
	private static TableModel tableModel;

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
		loadData(keyLookUp);
		this.setAlwaysOnTop(true);
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
			jTextFieldList.setText(JDialogLookup.table.getValueAt(JDialogLookup.table.getSelectedRow(), 0).toString());
			// this.transferFocus();
			// jTextFieldList.transferFocus();
			this.setVisible(false);
		}
	}
}
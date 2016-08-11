package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DepartmentDAO;
import dao.LoanTypesDAO;
import entities.Department;
import entities.LoanTypes;

public class JDialogModifyDepartments extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblDepartmentId;
	private JTextField JTextFieldDepartmentID;
	private JLabel lblDepartmentname;
	private JTextField JTextFieldDepartmentName;
	private JLabel lblLoantypes;
	private JComboBox JComboBoxLoanTypeName;
	private Department departments;
	private boolean isUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogModifyDepartments dialog = new JDialogModifyDepartments();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogModifyDepartments() {
		setBounds(100, 100, 450, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblDepartmentId = new JLabel("Department ID");
		lblDepartmentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentId.setName("lblDepartmentId");
		
		JTextFieldDepartmentID = new JTextField();
		JTextFieldDepartmentID.setEnabled(false);
		JTextFieldDepartmentID.setEditable(false);
		JTextFieldDepartmentID.setColumns(10);
		JTextFieldDepartmentID.setName("JTextFieldDepartmentID");
		
		lblDepartmentname = new JLabel("DepartmentName");
		lblDepartmentname.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentname.setName("lblDepartmentname");
		
		JTextFieldDepartmentName = new JTextField();
		JTextFieldDepartmentName.setColumns(10);
		JTextFieldDepartmentName.setName("JTextFieldDepartmentName");
		
		lblLoantypes = new JLabel("LoanTypeName");
		lblLoantypes.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoantypes.setName("lblLoantypes");
		
		JComboBoxLoanTypeName = new JComboBox();
		JComboBoxLoanTypeName.setName("JComboBoxLoanTypeName");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblDepartmentId, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(JTextFieldDepartmentID, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblDepartmentname, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(JTextFieldDepartmentName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblLoantypes, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(JComboBoxLoanTypeName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
					.addGap(46))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(23, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblDepartmentId))
						.addComponent(JTextFieldDepartmentID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblDepartmentname))
						.addComponent(JTextFieldDepartmentName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(JComboBoxLoanTypeName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoantypes))
					.addGap(12))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						do_okButton_actionPerformed(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadComboBox();
	}
	
	
	private void modifyDepartment() {

		try {
			if (departments == null) {
				departments = new Department();
			}
			departments.setDepartmentName(JTextFieldDepartmentName.getText());
			departments.setLoanTypes(new LoanTypesDAO().findByName(JComboBoxLoanTypeName.getSelectedItem().toString()));
			
			if (isUpdate) {
				new DepartmentDAO().update(departments);
			} else {
				new DepartmentDAO().create(departments);
			}
			JOptionPane.showMessageDialog(null, (isUpdate ? "Update" : "Add") + " department success!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			JPanelDepartment.loadTable();
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Can't " + (isUpdate ? "Update" : "Add") + " new department!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadComboBox() {
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
		LoanTypesDAO loanTypesDAO = new LoanTypesDAO();
		for (LoanTypes loanTypes : loanTypesDAO.findAll()) {
			defaultComboBoxModel.addElement(loanTypes.getLoanTypeName());
		}
		JComboBoxLoanTypeName.setModel(defaultComboBoxModel);
	}

	
	public JDialog isUpdate(Department departments) {
		isUpdate = true;
		this.departments = departments;
		this.JTextFieldDepartmentID.setText(Integer.toString(departments.getDepartmentId()));
		this.JTextFieldDepartmentName.setText(departments.getDepartmentName());
		this.JComboBoxLoanTypeName.setSelectedItem(departments.getLoanTypes().getLoanTypeName());
		return this;
	}
	protected void do_okButton_actionPerformed(ActionEvent e) {
		modifyDepartment();
	}
}

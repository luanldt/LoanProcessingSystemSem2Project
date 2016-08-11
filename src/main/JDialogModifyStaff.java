package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.DepartmentDAO;
import dao.LoanTypesDAO;
import dao.StaffsDAO;
import entities.Department;
import entities.LoanTypes;
import entities.Staffs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class JDialogModifyStaff extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblStaffid;
	private JTextField JTextFieldStaffID;
	private JLabel lblStaffname;
	private JLabel lblUsername;
	private JTextField JTextFieldStaffName;
	private JTextField JTextFieldUsername;
	private JLabel lblDepartmentname;
	private JComboBox JComboboxDepartmentName;
	private JLabel lblIsadmin;
	private JCheckBox JCheckboxIsAdmin;
	private boolean isUpdate;
	private Staffs staffs;
	private JPasswordField JPasswordFieldRepassword;
	private JLabel lblRepassword;
	private JPasswordField JPasswordFieldPassword;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogModifyStaff dialog = new JDialogModifyStaff();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogModifyStaff() {
		setBounds(100, 100, 450, 483);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblStaffid = new JLabel("StaffID");
		lblStaffid.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffid.setName("lblStaffid");

		JTextFieldStaffID = new JTextField();
		JTextFieldStaffID.setEnabled(false);
		JTextFieldStaffID.setEditable(false);
		JTextFieldStaffID.setColumns(10);
		JTextFieldStaffID.setName("JTextFieldStaffID");

		lblStaffname = new JLabel("StaffName");
		lblStaffname.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffname.setName("lblStaffname");

		lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setName("lblUsername");

		JTextFieldStaffName = new JTextField();
		JTextFieldStaffName.setColumns(10);
		JTextFieldStaffName.setName("JTextFieldStaffName");

		JTextFieldUsername = new JTextField();
		JTextFieldUsername.setColumns(10);
		JTextFieldUsername.setName("JTextFieldUsername");

		lblDepartmentname = new JLabel("DepartmentName");
		lblDepartmentname.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentname.setName("lblDepartmentname");

		JComboboxDepartmentName = new JComboBox();
		JComboboxDepartmentName.setName("JComboboxDepartmentName");

		lblIsadmin = new JLabel("IsAdmin");
		lblIsadmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblIsadmin.setName("lblIsadmin");

		JCheckboxIsAdmin = new JCheckBox();
		JCheckboxIsAdmin.setName("JCheckboxIsAdmin");
		
		JPasswordFieldRepassword = new JPasswordField();
		JPasswordFieldRepassword.setColumns(10);
		JPasswordFieldRepassword.setName("JPasswordFieldRepassword");
		
		lblRepassword = new JLabel("Re-password");
		lblRepassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblRepassword.setName("lblRepassword");
		
		JPasswordFieldPassword = new JPasswordField();
		JPasswordFieldPassword.setColumns(10);
		JPasswordFieldPassword.setName("JPasswordFieldPassword");
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setName("lblPassword");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDepartmentname)
						.addComponent(lblIsadmin, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepassword, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStaffname, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStaffid, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(JTextFieldStaffID, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JTextFieldStaffName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JTextFieldUsername, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JPasswordFieldRepassword, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JComboboxDepartmentName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(JCheckboxIsAdmin, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblStaffid))
						.addComponent(JTextFieldStaffID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblStaffname))
						.addComponent(JTextFieldStaffName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblUsername))
						.addComponent(JTextFieldUsername, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblPassword))
						.addComponent(JPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblRepassword))
						.addComponent(JPasswordFieldRepassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(JComboboxDepartmentName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblDepartmentname)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblIsadmin))
						.addComponent(JCheckboxIsAdmin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblStaffid, lblStaffname, lblUsername, lblDepartmentname, lblIsadmin});
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
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

	private void modifyStaff() {

		try {
			if (staffs == null) {
				staffs = new Staffs();
			}
			staffs.setStaffName(JTextFieldStaffName.getText());
			staffs.setDepartment(new DepartmentDAO().findByName(JComboboxDepartmentName.getSelectedItem().toString()));
			staffs.setIsAdmin(JCheckboxIsAdmin.isSelected());
			staffs.setPassword(String.valueOf(JPasswordFieldPassword.getPassword()));
			
			
			if (isUpdate) {
				new StaffsDAO().update(staffs);
			} else {
				staffs.setUsername(JTextFieldUsername.getText());
				new StaffsDAO().create(staffs);
			}
			JOptionPane.showMessageDialog(null, (isUpdate ? "Update" : "Add") + " staff success!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			JPanelStaff.loadTable();
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Can't " + (isUpdate ? "Update" : "Add") + " new staff!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadComboBox() {
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		for (Department department : departmentDAO.findAll()) {
			defaultComboBoxModel.addElement(department.getDepartmentName());
		}
		JComboboxDepartmentName.setModel(defaultComboBoxModel);
	}

	
	public JDialog isUpdate(Staffs staffs) {
		isUpdate = true;
		this.staffs = staffs;
		this.JTextFieldStaffID.setText(String.valueOf(staffs.getStaffId()));
		this.JTextFieldStaffName.setText(staffs.getStaffName());
		this.JTextFieldUsername.setText(staffs.getUsername());
		this.JComboboxDepartmentName.setSelectedItem(staffs.getDepartment().getDepartmentName());
		this.JCheckboxIsAdmin.setSelected(staffs.isIsAdmin());
		this.JTextFieldUsername.setEnabled(false);
		return this;
	}
	protected void do_okButton_actionPerformed(ActionEvent e) {
		modifyStaff();
	}
}

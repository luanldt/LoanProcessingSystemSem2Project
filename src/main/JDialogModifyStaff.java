package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import dao.DepartmentDAO;
import dao.StaffsDAO;
import entities.Department;
import entities.Staffs;
import helper.EncryptPasswordWithPBKDF2WithHmacSHA1;

@SuppressWarnings("serial")
public class JDialogModifyStaff extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblStaffid;
	private JTextField JTextFieldStaffID;
	private JLabel lblStaffname;
	private JLabel lblUsername;
	private JTextField JTextFieldStaffName;
	private JTextField JTextFieldUsername;
	private JLabel lblDepartmentname;
	private JComboBox<String> JComboboxDepartmentName;
	private JLabel lblIsadmin;
	private JCheckBox JCheckboxIsAdmin;
	private boolean isUpdate;
	private Staffs staffs;
	private JPasswordField JPasswordFieldRepassword;
	private JLabel lblRepassword;
	private JPasswordField JPasswordFieldPassword;
	private JLabel lblPassword;

	public JDialogModifyStaff() {
		setTitle("ModifyStaff");
		setBounds(100, 100, 450, 464);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblStaffid = new JLabel("StaffID :");
		lblStaffid.setForeground(new Color(0, 100, 0));
		lblStaffid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStaffid.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffid.setName("lblStaffid");

		JTextFieldStaffID = new JTextField();
		JTextFieldStaffID.setText("Automatically . . .");
		JTextFieldStaffID.setForeground(new Color(139, 0, 0));
		JTextFieldStaffID.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldStaffID.setEnabled(false);
		JTextFieldStaffID.setEditable(false);
		JTextFieldStaffID.setColumns(10);
		JTextFieldStaffID.setName("JTextFieldStaffID");

		lblStaffname = new JLabel("StaffName :");
		lblStaffname.setForeground(new Color(0, 100, 0));
		lblStaffname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStaffname.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffname.setName("lblStaffname");

		lblUsername = new JLabel("Username :");
		lblUsername.setForeground(new Color(0, 100, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setName("lblUsername");

		JTextFieldStaffName = new JTextField();
		JTextFieldStaffName.setForeground(new Color(139, 0, 0));
		JTextFieldStaffName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldStaffName.setColumns(10);
		JTextFieldStaffName.setName("JTextFieldStaffName");

		JTextFieldUsername = new JTextField();
		JTextFieldUsername.setForeground(new Color(139, 0, 0));
		JTextFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldUsername.setColumns(10);
		JTextFieldUsername.setName("JTextFieldUsername");

		lblDepartmentname = new JLabel("DepartmentName :");
		lblDepartmentname.setForeground(new Color(0, 100, 0));
		lblDepartmentname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartmentname.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentname.setName("lblDepartmentname");

		JComboboxDepartmentName = new JComboBox<String>();
		JComboboxDepartmentName.setForeground(new Color(139, 0, 0));
		JComboboxDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JComboboxDepartmentName.setName("JComboboxDepartmentName");

		lblIsadmin = new JLabel("IsAdmin :");
		lblIsadmin.setForeground(new Color(0, 100, 0));
		lblIsadmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIsadmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblIsadmin.setName("lblIsadmin");

		JCheckboxIsAdmin = new JCheckBox();
		JCheckboxIsAdmin.setForeground(new Color(139, 0, 0));
		JCheckboxIsAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		JCheckboxIsAdmin.setName("JCheckboxIsAdmin");

		JPasswordFieldRepassword = new JPasswordField();
		JPasswordFieldRepassword.setForeground(new Color(139, 0, 0));
		JPasswordFieldRepassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		JPasswordFieldRepassword.setColumns(10);
		JPasswordFieldRepassword.setName("JPasswordFieldRepassword");

		lblRepassword = new JLabel("Re-password :");
		lblRepassword.setForeground(new Color(0, 100, 0));
		lblRepassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblRepassword.setName("lblRepassword");

		JPasswordFieldPassword = new JPasswordField();
		JPasswordFieldPassword.setForeground(new Color(139, 0, 0));
		JPasswordFieldPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		JPasswordFieldPassword.setColumns(10);
		JPasswordFieldPassword.setName("JPasswordFieldPassword");

		lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(0, 100, 0));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setName("lblPassword");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(39, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStaffid, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStaffname, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRepassword, GroupLayout.PREFERRED_SIZE,
										123, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDepartmentname)
								.addComponent(lblIsadmin, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(JComboboxDepartmentName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										219, GroupLayout.PREFERRED_SIZE)
								.addComponent(JCheckboxIsAdmin, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JPasswordFieldRepassword, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										219, GroupLayout.PREFERRED_SIZE)
								.addComponent(JPasswordFieldPassword, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										219, GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldUsername, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldStaffName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldStaffID, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE))
						.addGap(29)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStaffid, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JTextFieldStaffID, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStaffname, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JTextFieldStaffName, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JTextFieldUsername, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRepassword, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JPasswordFieldRepassword, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDepartmentname, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JComboboxDepartmentName, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblIsadmin, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JCheckboxIsAdmin, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(10)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton JButtonSave = new JButton("Save");
				JButtonSave.setForeground(new Color(0, 0, 128));
				JButtonSave.setFont(new Font("Algerian", Font.BOLD, 15));
				JButtonSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						do_JButtonSave_actionPerformed(arg0);
					}
				});
				JButtonSave.setName("JButtonSave");
				JButtonSave.setActionCommand("OK");
				buttonPane.add(JButtonSave);
				getRootPane().setDefaultButton(JButtonSave);
			}
			{
				JButton JButtonCancel = new JButton("Cancel");
				JButtonCancel.setForeground(new Color(0, 0, 128));
				JButtonCancel.setFont(new Font("Algerian", Font.BOLD, 15));
				JButtonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						do_JButtonCancel_actionPerformed(e);
					}
				});
				JButtonCancel.setName("JButtonCancel");
				JButtonCancel.setActionCommand("Cancel");
				buttonPane.add(JButtonCancel);
			}
		}
		loadComboBox();
		setModal(true);
	}

	//TODO: lam tiep pham kiem tra username (luan dep trai)
	private void modifyStaff() {
		try {
			if (staffs == null) {
				staffs = new Staffs();
			}
			staffs.setStaffName(JTextFieldStaffName.getText());
			staffs.setDepartment(new DepartmentDAO().findByName(JComboboxDepartmentName.getSelectedItem().toString()));
			staffs.setIsAdmin(JCheckboxIsAdmin.isSelected());
			staffs.setUsername(JTextFieldUsername.getText());
			// Kiem tra mat khau va mat khau nhap lai
			if (String.valueOf(JPasswordFieldRepassword.getPassword())
					.equals(String.valueOf(JPasswordFieldPassword.getPassword()))) {
				if (JPasswordFieldPassword.getPassword().length > 0) {
					staffs.setPassword(EncryptPasswordWithPBKDF2WithHmacSHA1
							.generateStorngPasswordHash(String.valueOf(JPasswordFieldPassword.getPassword())));
				}
			} else {
				JOptionPane.showMessageDialog(null, "Password do not analogous!");
				return;
			}
			// End kiem tra mat khau va mat khau nhap lai
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<Staffs>> constraintViolations = validator.validate(staffs);
			if (!constraintViolations.isEmpty()) {
				String error = "";
				for (ConstraintViolation<Staffs> violation : constraintViolations) {
					error += violation.getMessage() + "\n";
				}
				JOptionPane.showMessageDialog(null, error);
			} else {
				if (isUpdate) {
					new StaffsDAO().update(staffs);
				} else {
					new StaffsDAO().create(staffs);
				}
				JOptionPane.showMessageDialog(null, (isUpdate ? "Update" : "Add") + " staff success!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Can't " + (isUpdate ? "Update" : "Add") + " new staff!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadComboBox() {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
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

	protected void do_JButtonSave_actionPerformed(ActionEvent arg0) {
		modifyStaff();
	}

	protected void do_JButtonCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}

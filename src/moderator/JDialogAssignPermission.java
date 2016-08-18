package moderator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.regex.Matcher;

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
import javax.validation.constraints.Pattern;

import dao.DepartmentDAO;
import dao.StaffsDAO;
import entities.Department;
import entities.Staffs;
import helper.EncryptPasswordWithPBKDF2WithHmacSHA1;

@SuppressWarnings("serial")
public class JDialogAssignPermission extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblStaffid;
	private JTextField JTextFieldStaffID;
	private JLabel lblStaffname;
	private JLabel lblUsername;
	private JTextField JTextFieldStaffName;
	private JTextField JTextFieldUsername;
	private JLabel lblDepartmentname;
	private JTextField JTextFieldDepartmentName;
	private JLabel lblRole;
	@SuppressWarnings("rawtypes")
	private JComboBox JComboBoxRole;
	private Staffs staffs;

	@SuppressWarnings("unchecked")
	public JDialogAssignPermission(Staffs staffs) {
		setTitle("ModifyStaff");
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblStaffid = new JLabel("StaffID :");
		lblStaffid.setForeground(new Color(0, 100, 0));
		lblStaffid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStaffid.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffid.setName("lblStaffid");

		JTextFieldStaffID = new JTextField();
		this.JTextFieldStaffID.setEnabled(false);
		this.JTextFieldStaffID.setEditable(false);
		JTextFieldStaffID.setText("Automatically . . .");
		JTextFieldStaffID.setForeground(new Color(139, 0, 0));
		JTextFieldStaffID.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		this.JTextFieldStaffName.setEnabled(false);
		this.JTextFieldStaffName.setEditable(false);
		JTextFieldStaffName.setForeground(new Color(139, 0, 0));
		JTextFieldStaffName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldStaffName.setColumns(10);
		JTextFieldStaffName.setName("JTextFieldStaffName");

		JTextFieldUsername = new JTextField();
		this.JTextFieldUsername.setEnabled(false);
		this.JTextFieldUsername.setEditable(false);
		JTextFieldUsername.setForeground(new Color(139, 0, 0));
		JTextFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldUsername.setColumns(10);
		JTextFieldUsername.setName("JTextFieldUsername");

		lblDepartmentname = new JLabel("DepartmentName :");
		lblDepartmentname.setForeground(new Color(0, 100, 0));
		lblDepartmentname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartmentname.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentname.setName("lblDepartmentname");

		JTextFieldDepartmentName = new JTextField();
		this.JTextFieldDepartmentName.setEnabled(false);
		this.JTextFieldDepartmentName.setEditable(false);
		JTextFieldDepartmentName.setForeground(new Color(139, 0, 0));
		JTextFieldDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldDepartmentName.setName("JTextFieldDepartmentName");

		lblRole = new JLabel("Role :");
		lblRole.setForeground(new Color(0, 100, 0));
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRole.setHorizontalAlignment(SwingConstants.LEFT);
		lblRole.setName("lblRole");

		JComboBoxRole = new JComboBox();
		this.JComboBoxRole
				.setModel(new DefaultComboBoxModel(new String[] {"0 - Administrator", "1 - Moderator", "2 - Standard User"}));
		JComboBoxRole.setForeground(new Color(139, 0, 0));
		JComboBoxRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		JComboBoxRole.setName("JComboBoxRole");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(
						gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
								.createSequentialGroup().addContainerGap(39,
										Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(this.lblRole, GroupLayout.PREFERRED_SIZE, 123,
														GroupLayout.PREFERRED_SIZE)
												.addGap(14).addComponent(this.JComboBoxRole,
														GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPanel
														.createSequentialGroup().addComponent(this.lblDepartmentname)
														.addGap(5).addComponent(this.JTextFieldDepartmentName,
																GroupLayout.PREFERRED_SIZE, 219,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(this.lblStaffid,
																		GroupLayout.PREFERRED_SIZE, 123,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(this.lblStaffname,
																		GroupLayout.PREFERRED_SIZE, 123,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(this.lblUsername,
																		GroupLayout.PREFERRED_SIZE, 123,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(14)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(this.JTextFieldUsername,
																		Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
																		219, GroupLayout.PREFERRED_SIZE)
																.addComponent(this.JTextFieldStaffName,
																		Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
																		219, GroupLayout.PREFERRED_SIZE)
																.addComponent(this.JTextFieldStaffID,
																		Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
																		219, GroupLayout.PREFERRED_SIZE)))))
								.addGap(29)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblStaffid, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JTextFieldStaffID, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblStaffname, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JTextFieldStaffName, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblUsername, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JTextFieldUsername, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblDepartmentname, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JTextFieldDepartmentName, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblRole, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JComboBoxRole, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addGap(116)));
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

		this.JTextFieldStaffID.setText(String.valueOf(staffs.getStaffId()));
		this.JTextFieldStaffName.setText(staffs.getStaffName());
		this.JTextFieldUsername.setText(staffs.getUsername());
		this.JTextFieldDepartmentName.setText(staffs.getDepartment().getDepartmentName());
		this.JComboBoxRole.setSelectedIndex(staffs.getRole());
		setModal(true);
	}

	protected void do_JButtonSave_actionPerformed(ActionEvent arg0) {
		try {
			StaffsDAO staffsDAO = new StaffsDAO();
			if(staffs == null)
				staffs = staffsDAO.find(Integer.parseInt(JTextFieldStaffID.getText()));
			staffs.setRole(JComboBoxRole.getSelectedIndex());
			staffsDAO.update(staffs);
			JOptionPane.showMessageDialog(null, "Assignment completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Assignment failed!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void do_JButtonCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}

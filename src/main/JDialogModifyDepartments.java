package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;


public class JDialogModifyDepartments extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblDepartmentId;
	private JTextField JTextFieldDepartmentID;
	private JLabel lblDepartmentname;
	private JTextField JTextFieldDepartmentName;
	private JLabel lblLoantypes;
	private JComboBox<String> JComboBoxLoanTypeName;
	private Department departments;
	private boolean isUpdate;

	public JDialogModifyDepartments() {
		setTitle("ModifyDepartments");
		setBounds(100, 100, 450, 268);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblDepartmentId = new JLabel("Department ID :");
		lblDepartmentId.setForeground(new Color(0, 100, 0));
		lblDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartmentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentId.setName("lblDepartmentId");

		JTextFieldDepartmentID = new JTextField();
		JTextFieldDepartmentID.setText("Automatically . . .");
		JTextFieldDepartmentID.setForeground(new Color(139, 0, 0));
		JTextFieldDepartmentID.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldDepartmentID.setEnabled(false);
		JTextFieldDepartmentID.setEditable(false);
		JTextFieldDepartmentID.setColumns(10);
		JTextFieldDepartmentID.setName("JTextFieldDepartmentID");

		lblDepartmentname = new JLabel("Department Name :");
		lblDepartmentname.setForeground(new Color(0, 100, 0));
		lblDepartmentname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartmentname.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentname.setName("lblDepartmentname");

		JTextFieldDepartmentName = new JTextField();
		JTextFieldDepartmentName.setForeground(new Color(139, 0, 0));
		JTextFieldDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldDepartmentName.setColumns(10);
		JTextFieldDepartmentName.setName("JTextFieldDepartmentName");

		lblLoantypes = new JLabel("Loan Type Name :");
		lblLoantypes.setForeground(new Color(0, 100, 0));
		lblLoantypes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoantypes.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoantypes.setName("lblLoantypes");

		JComboBoxLoanTypeName = new JComboBox<String>();
		JComboBoxLoanTypeName.setForeground(new Color(139, 0, 0));
		JComboBoxLoanTypeName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JComboBoxLoanTypeName.setName("JComboBoxLoanTypeName");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDepartmentId, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDepartmentname).addComponent(lblLoantypes))
						.addGap(15)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(JTextFieldDepartmentName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldDepartmentID, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JComboBoxLoanTypeName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE))
						.addGap(22)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(23, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDepartmentId, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldDepartmentID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(JTextFieldDepartmentName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDepartmentname, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLoantypes, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addComponent(
										JComboBoxLoanTypeName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(13)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { lblDepartmentId, lblDepartmentname, lblLoantypes });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { JTextFieldDepartmentID, JTextFieldDepartmentName, JComboBoxLoanTypeName });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton JButtonSave = new JButton("Save");
				JButtonSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						do_JButtonSave_actionPerformed(e);
					}
				});
				JButtonSave.setName("JButtonSave");
				JButtonSave.setForeground(new Color(0, 0, 128));
				JButtonSave.setFont(new Font("Algerian", Font.BOLD, 15));
				JButtonSave.setActionCommand("OK");
				buttonPane.add(JButtonSave);
				getRootPane().setDefaultButton(JButtonSave);
			}
			{
				JButton JButtonCancel = new JButton("Cancel");
				JButtonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						do_JButtonCancel_actionPerformed(e);
					}
				});
				JButtonCancel.setName("JButtonCancel");
				JButtonCancel.setForeground(new Color(0, 0, 128));
				JButtonCancel.setFont(new Font("Algerian", Font.BOLD, 15));
				JButtonCancel.setActionCommand("Cancel");
				buttonPane.add(JButtonCancel);
			}
		}

		loadComboBox();
		setModal(true);
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
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Can't " + (isUpdate ? "Update" : "Add") + " new department!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadComboBox() {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
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

	protected void do_JButtonSave_actionPerformed(ActionEvent e) {
		modifyDepartment();
	}

	protected void do_JButtonCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}

}

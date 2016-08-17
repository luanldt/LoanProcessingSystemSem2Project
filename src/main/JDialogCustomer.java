package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.CustomersDAO;
import entities.Customers;

public class JDialogCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblIdentityCardNumber;
	private JLabel lblSalary;
	private JLabel lblNotes;
	private JTextField IDcardTxt;
	private JTextField nameTxt;
	private JTextField addrTxt;
	private JTextField phoneTxt;
	private JTextField emailTxt;
	private JTextField accnoTxt;
	private JTextField salaryTxt;
	private JScrollPane scrollPane;
	private JTextArea noteTxtArea;
	private boolean isUpdate = false;
	Customers Customer;
	private JCheckBox chckbxDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			JDialogCustomer dialog = new JDialogCustomer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogCustomer() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 480, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblNewLabel = new JLabel("Name");
		lblNewLabel.setName("lblNewLabel");

		lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setName("lblNewLabel_1");

		lblNewLabel_2 = new JLabel("Phone number");
		lblNewLabel_2.setName("lblNewLabel_2");

		lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setName("lblNewLabel_3");

		lblNewLabel_4 = new JLabel("Account number");
		lblNewLabel_4.setName("lblNewLabel_4");

		lblIdentityCardNumber = new JLabel("Identity card number");
		lblIdentityCardNumber.setName("lblIdentityCardNumber");

		lblSalary = new JLabel("Salary");
		lblSalary.setName("lblSalary");

		lblNotes = new JLabel("Notes");
		lblNotes.setName("lblNotes");

		IDcardTxt = new JTextField();
		IDcardTxt.setName("IDcardTxt");
		IDcardTxt.setColumns(10);

		nameTxt = new JTextField();
		nameTxt.setName("nameTxt");
		nameTxt.setColumns(10);

		addrTxt = new JTextField();
		addrTxt.setName("addrTxt");
		addrTxt.setColumns(10);

		phoneTxt = new JTextField();
		phoneTxt.setName("phoneTxt");
		phoneTxt.setColumns(10);

		emailTxt = new JTextField();
		emailTxt.setName("emailTxt");
		emailTxt.setColumns(10);

		accnoTxt = new JTextField();
		accnoTxt.setName("accnoTxt");
		accnoTxt.setColumns(10);

		salaryTxt = new JTextField();
		salaryTxt.setName("salaryTxt");
		salaryTxt.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setName("scrollPane");

		chckbxDelete = new JCheckBox("Delete?");
		chckbxDelete.setVisible(false);
		chckbxDelete.setEnabled(false);
		chckbxDelete.setName("chckbxDelete");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
										.addComponent(lblNotes))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblIdentityCardNumber).addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel_2).addComponent(lblNewLabel_3)
												.addComponent(lblNewLabel_4).addComponent(lblSalary))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(salaryTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(accnoTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(emailTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(addrTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(nameTxt).addComponent(IDcardTxt,
																GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
										.addGap(50)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(chckbxDelete)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPanel
										.createParallelGroup(Alignment.LEADING).addComponent(
												nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(
												gl_contentPanel
														.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPanel.createSequentialGroup()
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblNewLabel)
																				.addComponent(lblNotes))
																		.addGap(18)
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblNewLabel_1)
																				.addComponent(addrTxt,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblNewLabel_2)
																				.addComponent(phoneTxt,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblNewLabel_3)
																				.addComponent(emailTxt,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
																.addComponent(
																		scrollPane, GroupLayout.PREFERRED_SIZE, 154,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(18)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPanel
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblNewLabel_4).addComponent(
																				accnoTxt, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addComponent(chckbxDelete))))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblIdentityCardNumber).addComponent(IDcardTxt,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSalary).addComponent(salaryTxt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { IDcardTxt, nameTxt, addrTxt, phoneTxt, emailTxt, accnoTxt, salaryTxt });

		noteTxtArea = new JTextArea();
		noteTxtArea.setName("noteTxtArea");
		scrollPane.setViewportView(noteTxtArea);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelButtonActionListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	private class CancelButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class OkButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Customer == null) {
				Customer = new Customers();
			}
			Customer.setCustomerName(nameTxt.getText());
			Customer.setAddress(addrTxt.getText());
			Customer.setAccountNumber(accnoTxt.getText());
			Customer.setPhoneNumber(phoneTxt.getText());
			Customer.setEmail(emailTxt.getText());
			Customer.setIdentityCardNo(IDcardTxt.getText());
			BigDecimal bd = new BigDecimal((salaryTxt.getText()));
			Customer.setSalary(bd);
			Customer.setNotes(noteTxtArea.getText());
			Customer.setCreateLog("Why does this exist?");
			try {
				if (isUpdate) {
					Customer.setIsArchive(chckbxDelete.isSelected());
					new CustomersDAO().update(Customer);
				} else {
					new CustomersDAO().create(Customer);
				}
				JOptionPane.showMessageDialog(null, (isUpdate ? "Update this" : "Add new") + " contract success!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Can't " + (isUpdate ? "Update this" : "Add new") + " contract!" + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public JDialog isUpdate(Customers find) {
		isUpdate = true;
		chckbxDelete.setEnabled(true);
		chckbxDelete.setVisible(true);
		this.Customer = find;
		nameTxt.setText(find.getCustomerName());
		addrTxt.setText(find.getAddress());
		phoneTxt.setText(find.getPhoneNumber());
		emailTxt.setText(find.getEmail());
		accnoTxt.setText(find.getAccountNumber());
		IDcardTxt.setText(find.getIdentityCardNo());
		salaryTxt.setText(find.getSalary().stripTrailingZeros().toPlainString());
		noteTxtArea.setText(find.getNotes());
		chckbxDelete.setSelected(find.isIsArchive());
		return this;
	}
}

package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.LoanTypes;
import dao.LoanTypesDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogAddLoanTypes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblLoanType;
	private JLabel lblLoanBase;
	private JLabel lblLoanRate;
	private JTextField JTextFieldLoanTypeName;
	private JTextField JTextFieldLoanTypeInterestRate;
	private JTextField JTextFieldLoanBase;
	private JTextField JTextFieldLoanRate;
	private JTextField JTextFieldLoanTypeID;
	private JLabel lblId;
	private boolean isUpdate = false;
	private LoanTypes loanTypes;

	public JDialogAddLoanTypes() {
		setTitle("Add Loan Types");
		setBounds(100, 100, 436, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblNewLabel = new JLabel("Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		lblLoanType = new JLabel("Interest Rate");
		lblLoanType.setHorizontalAlignment(SwingConstants.RIGHT);

		lblLoanBase = new JLabel("Loan Base");
		lblLoanBase.setHorizontalAlignment(SwingConstants.RIGHT);

		lblLoanRate = new JLabel("Loan Rate");
		lblLoanRate.setHorizontalAlignment(SwingConstants.RIGHT);

		JTextFieldLoanTypeName = new JTextField();
		JTextFieldLoanTypeName.setColumns(10);

		JTextFieldLoanTypeInterestRate = new JTextField();
		JTextFieldLoanTypeInterestRate.setColumns(10);

		JTextFieldLoanBase = new JTextField();
		JTextFieldLoanBase.setColumns(10);

		JTextFieldLoanRate = new JTextField();
		JTextFieldLoanRate.setColumns(10);

		JTextFieldLoanTypeID = new JTextField();
		JTextFieldLoanTypeID.setEditable(false);
		JTextFieldLoanTypeID.setEnabled(false);
		JTextFieldLoanTypeID.setColumns(10);

		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(60, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addGap(18)
										.addComponent(JTextFieldLoanTypeID, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(lblLoanType))
												.addGap(18)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(JTextFieldLoanTypeName, GroupLayout.PREFERRED_SIZE, 219,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(JTextFieldLoanTypeInterestRate, GroupLayout.PREFERRED_SIZE, 205,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblLoanBase, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(JTextFieldLoanBase, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblLoanRate, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(JTextFieldLoanRate, GroupLayout.PREFERRED_SIZE, 205,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(50)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap(45, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(9).addComponent(lblId))
						.addComponent(JTextFieldLoanTypeID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
						.addComponent(JTextFieldLoanTypeName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLoanType)
						.addComponent(JTextFieldLoanTypeInterestRate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLoanBase)
						.addComponent(JTextFieldLoanBase, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLoanRate)
						.addComponent(JTextFieldLoanRate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(34)));
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { lblLoanBase, lblLoanRate, lblNewLabel, lblLoanType });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] { JTextFieldLoanTypeName,
				JTextFieldLoanTypeInterestRate, JTextFieldLoanBase, JTextFieldLoanRate });
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton JButtonSave = new JButton("Save");
				JButtonSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addUpdateLoanType();
					}
				});
				JButtonSave.setActionCommand("OK");
				buttonPane.add(JButtonSave);
				getRootPane().setDefaultButton(JButtonSave);
			}
			{
				JButton JButtonCancel = new JButton("Cancel");
				JButtonCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				JButtonCancel.setActionCommand("Cancel");
				buttonPane.add(JButtonCancel);
			}
		}

	}

	private void addUpdateLoanType() {
		// Thêm vào một loan type
		try {
			if (loanTypes == null) {
				loanTypes = new LoanTypes();
			}
			loanTypes.setLoanTypeName(JTextFieldLoanTypeName.getText());
			loanTypes.setLoanBase(BigDecimal.valueOf(Double.parseDouble(JTextFieldLoanBase.getText())));
			loanTypes.setInterestRate(BigDecimal.valueOf(Double.parseDouble(JTextFieldLoanTypeInterestRate.getText())));
			loanTypes.setLoanRate(Long.parseLong(JTextFieldLoanRate.getText()));
			if (isUpdate) {
				new LoanTypesDAO().update(loanTypes);
			} else {
				new LoanTypesDAO().create(loanTypes);
			}
			JOptionPane.showMessageDialog(null, (isUpdate ? "Update" : "Add") + " loan type success!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			JPanelLoanType.loadTable();
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Can't " + (isUpdate ? "Update" : "Add") + " new loan type!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public JDialog isUpdate(LoanTypes loanTypes) {
		isUpdate = true;
		this.loanTypes = loanTypes;
		this.JTextFieldLoanTypeID.setText(Integer.toString(loanTypes.getLoanTypeId()));
		this.JTextFieldLoanTypeName.setText(loanTypes.getLoanTypeName());
		this.JTextFieldLoanTypeInterestRate.setText(loanTypes.getInterestRate().toString());
		this.JTextFieldLoanBase.setText(loanTypes.getLoanBase().toString());
		this.JTextFieldLoanRate.setText(Long.toString(loanTypes.getLoanRate()));
		return this;
	}
}

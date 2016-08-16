package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.LoanTypes;
//import model.ProcessAction;
import dao.LoanTypesDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 * 
 * @author nguyenminhluan
 *
 */
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
		setTitle("ModifyLoanTypes");
		setBounds(100, 100, 436, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblNewLabel = new JLabel("Loan Type Name :");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);

		lblLoanType = new JLabel("Interest Rate :");
		lblLoanType.setForeground(new Color(0, 100, 0));
		lblLoanType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoanType.setHorizontalAlignment(SwingConstants.LEFT);

		lblLoanBase = new JLabel("Loan Base :");
		lblLoanBase.setForeground(new Color(0, 100, 0));
		lblLoanBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoanBase.setHorizontalAlignment(SwingConstants.LEFT);

		lblLoanRate = new JLabel("Loan Rate :");
		lblLoanRate.setForeground(new Color(0, 100, 0));
		lblLoanRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoanRate.setHorizontalAlignment(SwingConstants.LEFT);

		JTextFieldLoanTypeName = new JTextField();
		JTextFieldLoanTypeName.setForeground(new Color(139, 0, 0));
		JTextFieldLoanTypeName.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldLoanTypeName.setColumns(10);

		JTextFieldLoanTypeInterestRate = new JTextField();
		JTextFieldLoanTypeInterestRate.setForeground(new Color(139, 0, 0));
		JTextFieldLoanTypeInterestRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldLoanTypeInterestRate.setColumns(10);

		JTextFieldLoanBase = new JTextField();
		JTextFieldLoanBase.setForeground(new Color(139, 0, 0));
		JTextFieldLoanBase.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldLoanBase.setColumns(10);

		JTextFieldLoanRate = new JTextField();
		JTextFieldLoanRate.setForeground(new Color(139, 0, 0));
		JTextFieldLoanRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldLoanRate.setColumns(10);

		JTextFieldLoanTypeID = new JTextField();
		JTextFieldLoanTypeID.setText("Automatically . . .");
		JTextFieldLoanTypeID.setForeground(new Color(139, 0, 0));
		JTextFieldLoanTypeID.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextFieldLoanTypeID.setEditable(false);
		JTextFieldLoanTypeID.setEnabled(false);
		JTextFieldLoanTypeID.setColumns(10);

		lblId = new JLabel("Loan Type ID :");
		lblId.setForeground(new Color(0, 100, 0));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap(28, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE).addGap(15)
								.addComponent(JTextFieldLoanTypeID, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE).addGap(15)
								.addComponent(JTextFieldLoanTypeName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblLoanType, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE).addGap(15)
								.addComponent(JTextFieldLoanTypeInterestRate, GroupLayout.PREFERRED_SIZE, 219,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblLoanBase, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE).addGap(15)
								.addComponent(JTextFieldLoanBase, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblLoanRate, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE).addGap(15)
								.addComponent(JTextFieldLoanRate, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
				.addGap(17)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(JTextFieldLoanTypeID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addComponent(
										JTextFieldLoanTypeName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLoanType, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addComponent(
										JTextFieldLoanTypeInterestRate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLoanBase, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addComponent(
										JTextFieldLoanBase, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLoanRate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addComponent(
										JTextFieldLoanRate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(14)));
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
				JButtonCancel.setForeground(new Color(0, 0, 128));
				JButtonCancel.setFont(new Font("Algerian", Font.BOLD, 15));
				JButtonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				JButtonCancel.setActionCommand("Cancel");
				buttonPane.add(JButtonCancel);
			}
		}
		setModal(true);
	}

	/**
	 * METHOD THAO TÁC THÊM VÀ SỬA VỚI DATABASE TRUYỀN VÀO JPANEL TỨC LÀ TRUYỀN NÀO JPANEL CHỨA JTABLE
	 * @param jPanel
	 */
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
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Can't " + (isUpdate ? "Update" : "Add") + " new loan type!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * ĐÂY LÀ METHOD THỰC HIỆN HÀNH ĐỘNG LOAD DỮ LIỆU LÊN CỦA UPDATE
	 * 
	 * @param loanTypes
	 * @return
	 */
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

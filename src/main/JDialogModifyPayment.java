package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlExtension.JTextFieldList;
import dao.ContractsDAO;
import dao.PaymentDAO;
import dao.StaffsDAO;
import entities.Payment;
import entities.Staffs;

public class JDialogModifyPayment extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPaymentId;
	private JTextField JTextFieldPaymentID;
	private JLabel lblContractId;
	private JLabel lblPayment;
	private JTextField JTextFieldPaymentType;
	private JLabel lblPaymentDate;
	private JLabel lblDueP;
	private JTextField JTextFieldDuePeriod;
	private JLabel lblPaymentAmout;
	private JTextField JTextFieldPaymentAmount;
	private JLabel lblFineRate;
	private JTextField JTextFieldFineRate;
	private JLabel lblFineAmout;
	private JTextField JTextFieldFineAmout;
	private JLabel lblStaffId;
	private JComboBox<String> JComboBoxStaffId;
	private JLabel lblPaid;
	private JCheckBox JCheckBoxPaid;
	private JTextFieldList txtContractId;
	// Declare to set up date picker from jdatepickerimpl.
	private Properties p;
	private JDatePickerImpl JDatePickerPaymentDate;
	private JDatePanelImpl JDatePanelPaymentDate;
	private boolean isUpdate;
	// end declare set up date picker
	private Payment payment;

	/**
	 * Create the dialog.
	 */
	public JDialogModifyPayment() {
		setResizable(false);
		setTitle("Add Payment");
		setBounds(100, 100, 346, 563);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Set up properties for date picker.
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		// End set up properties for date picker.

		lblPaymentId = new JLabel("Payment ID");

		JTextFieldPaymentID = new JTextField();
		JTextFieldPaymentID.setEnabled(false);
		JTextFieldPaymentID.setColumns(10);

		lblContractId = new JLabel("Contract ID");
		lblContractId.setName("txtContract");

		lblPayment = new JLabel("Payment Type");

		JTextFieldPaymentType = new JTextField();
		JTextFieldPaymentType.setColumns(10);

		// Set up for jdatepicker payment date
		JDatePanelPaymentDate = new JDatePanelImpl(new UtilDateModel(), p);
		JDatePickerPaymentDate = new JDatePickerImpl(JDatePanelPaymentDate, new DateLabelFormatter());
		JDatePickerPaymentDate.getJFormattedTextField().setBounds(new Rectangle(0, 0, 0, 12));
		// End set up

		lblPaymentDate = new JLabel("Payment Date");

		lblDueP = new JLabel("Due Period");

		JTextFieldDuePeriod = new JTextField();
		JTextFieldDuePeriod.setColumns(10);

		lblPaymentAmout = new JLabel("Payment Amout");

		JTextFieldPaymentAmount = new JTextField();
		JTextFieldPaymentAmount.setColumns(10);

		lblFineRate = new JLabel("Fine Rate");

		JTextFieldFineRate = new JTextField();
		JTextFieldFineRate.setColumns(10);

		lblFineAmout = new JLabel("Fine Amout");

		JTextFieldFineAmout = new JTextField();
		JTextFieldFineAmout.setColumns(10);

		lblStaffId = new JLabel("Staff ID");

		/*
		 * Sử dụng jcombox là vì staff số lượng nhân viên không có nhiều lắm nên
		 * trong trường hợp có ít dữ liệu thì jcombobox dễ chịu hơn đối với người
		 * dùng
		 */
		JComboBoxStaffId = new JComboBox<String>();
		/*
		 * End reason. LDT
		 */

		lblPaid = new JLabel("Paid");

		JCheckBoxPaid = new JCheckBox();

		txtContractId = new JTextFieldList();
		txtContractId.setName("txtContractId");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPaymentId, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JTextFieldPaymentID, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblContractId, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(txtContractId, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPayment, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JTextFieldPaymentType, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPaymentDate, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JDatePickerPaymentDate, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblDueP, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JTextFieldDuePeriod, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPaymentAmout, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(JTextFieldPaymentAmount, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblFineRate, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JTextFieldFineRate, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblFineAmout, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JTextFieldFineAmout, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblStaffId, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(
												6)
										.addComponent(JComboBoxStaffId, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPaid, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(6)
										.addComponent(JCheckBoxPaid, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
						.addGap(11)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_contentPanel.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblPaymentId))
								.addComponent(JTextFieldPaymentID, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblContractId))
								.addComponent(txtContractId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblPayment))
								.addComponent(JTextFieldPaymentType, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblPaymentDate))
								.addComponent(JDatePickerPaymentDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblDueP))
								.addComponent(JTextFieldDuePeriod, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblPaymentAmout))
								.addComponent(JTextFieldPaymentAmount, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblFineRate))
								.addComponent(JTextFieldFineRate, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblFineAmout))
								.addComponent(JTextFieldFineAmout, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblStaffId))
								.addComponent(JComboBoxStaffId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(6).addComponent(lblPaid))
								.addComponent(JCheckBoxPaid, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(10)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addPayment();
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
		loadStaffCombox();
		setModal(true);
	}

	// Inner class dùng để tạo thành 1 formater cho việc truy xuất dữ liệu jdate
	// picker
	public class DateLabelFormatter extends AbstractFormatter {

		private static final long serialVersionUID = 1L;
		private String datePattern = "dd/MM/yyyy";
		private DateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	private void loadStaffCombox() {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (Staffs staffs : new StaffsDAO().findAll()) {
			defaultComboBoxModel.addElement(Integer.toString(staffs.getStaffId()));
		}
		JComboBoxStaffId.setModel(defaultComboBoxModel);
	}

	public void addPayment() {
		if(payment == null) {
			payment = new Payment();
		}
		payment.setPaymentType(Integer.parseInt(JTextFieldPaymentType.getText()));
		payment.setDuePeriod(Integer.parseInt(JTextFieldDuePeriod.getText()));
		payment.setFineAmount(BigDecimal.valueOf(Double.parseDouble(JTextFieldPaymentType.getText())));
		payment.setContracts(new ContractsDAO().find(Integer.parseInt(txtContractId.getText())));
		payment.setFineRate(Long.parseLong(JTextFieldPaymentType.getText()));
		payment.setPaid(JCheckBoxPaid.isSelected());
		payment.setPaymentDate((Date) JDatePickerPaymentDate.getModel().getValue());
		payment.setStaffs(new StaffsDAO().find(Integer.parseInt(JComboBoxStaffId.getSelectedItem().toString())));
		payment.setPaymentAmount(BigDecimal.valueOf(Double.parseDouble(JTextFieldPaymentAmount.getText())));
		payment.setCreateLog("");
		try {
			if (isUpdate) {
				new PaymentDAO().update(payment);
			} else {
				new PaymentDAO().create(payment);
			}
			JOptionPane.showMessageDialog(null, (isUpdate ? "Update this" : "Add new") + " payment success!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Can't " + (isUpdate ? "Update this" : "Add new") + " payment!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public JDialogModifyPayment isUpdate(Payment payment) {
		setTitle("Update Payment");
		this.isUpdate = true;
		this.payment = payment;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(payment.getPaymentDate());
		this.JDatePickerPaymentDate.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		this.JDatePickerPaymentDate.getModel().setSelected(true);
		this.JTextFieldPaymentID.setText(Integer.toString(payment.getPaymentId()));
		this.JTextFieldFineRate.setText(Long.toString(payment.getFineRate()));
		this.JTextFieldDuePeriod.setText(Integer.toString(payment.getDuePeriod()));
		this.JTextFieldPaymentType.setText(Integer.toString(payment.getPaymentType()));
		this.JTextFieldFineAmout.setText(payment.getFineAmount().toString());
		this.JComboBoxStaffId.setSelectedItem(Integer.toString(payment.getStaffs().getStaffId()));
		this.JCheckBoxPaid.setSelected(payment.isPaid());
		this.JTextFieldPaymentAmount.setText(payment.getPaymentAmount().toString());
		this.txtContractId.setText(Integer.toString(payment.getContracts().getContractId()));
		return this;
	}
}

package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Set;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import entities.Contracts;
import entities.LoanTypes;

import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.Component;
import controlExtension.JTextFieldList;
import dao.ContractsDAO;
import dao.CustomersDAO;
import dao.LoanTypesDAO;
import dao.StaffsDAO;

import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class JDialogModifyContract extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel pnlGeneralInfos;
	private JLabel lblContracts;
	private JLabel lblId;
	private JTextField txtContractId;
	private JLabel lblDate;
	Properties p;
	private JDatePickerImpl dpkContract;
	private JDatePanelImpl dpnlContract;
	private JDatePickerImpl dpkInitiate;
	private JDatePanelImpl dpnlInitiate;
	private JDatePickerImpl dpkDueDate;
	private JDatePanelImpl dpnlDueDate;
	private JLabel lblStaffId;
	private JTextFieldList txtStaffId;
	private JLabel lblCustomerId;
	private JTextFieldList txtCustomerId;
	private JLabel lblTypeId;
	private JTextFieldList txtTypeId;
	private JLabel lblCustomerName;
	private JLabel lblStaffName;
	private JLabel lblLoanType;
	private JLabel lblRate;
	private JFormattedTextField JFormatedTextFieldRate;
	private JPanel pnlDuration;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblMaturityPeriod;
	private JFormattedTextField JFormatedTextFieldMaturityPeriod;
	private JFormattedTextField JFormattedTextFieldLoanTerm;
	private JLabel lblLoanTerm;
	private JPanel panel;
	private JTextField JTextFieldInitialAmount;
	private JLabel lblInitial;
	private JLabel lblRemain;
	private JFormattedTextField JFormatedTextFieldRemain;
	private JFormattedTextField JFormatedTextFieldMax;
	private JLabel lblMax;
	private JLabel lblPaidtimes;
	private JFormattedTextField JFormatedTextFieldPaidTime;
	private JTextArea txaNotes;
	private JLabel lblNotes;
	private boolean isUpdate = false;
	private Contracts contracts;
	private JLabel lblLoginfo;

	/**
	 * Launch the application.
	 */

	public JDialogModifyContract() {
		setTitle("Add Contract");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setResizable(false);
		setBounds(100, 100, 794, 690);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);

		this.pnlGeneralInfos = new JPanel();
		this.pnlGeneralInfos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"General Informations", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.pnlGeneralInfos.setName("pnlGeneralInfos");

		this.lblContracts = new JLabel("CONTRACTS");
		this.lblContracts.setForeground(Color.BLUE);
		this.lblContracts.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblContracts.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblContracts.setName("lblContracts");

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		this.lblRate = new JLabel("Rate");
		this.lblRate.setForeground(Color.RED);
		this.lblRate.setName("lblRate");

		this.JFormatedTextFieldRate = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
		this.JFormatedTextFieldRate.setColumns(10);
		this.JFormatedTextFieldRate.setName("txtRate");

		this.pnlDuration = new JPanel();
		this.pnlDuration.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Duration",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.pnlDuration.setName("pnlDuration");

		this.panel = new JPanel();
		this.panel
				.setBorder(new TitledBorder(null, "Amount", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel.setName("panel");

		this.lblPaidtimes = new JLabel("Paid times");
		this.lblPaidtimes.setForeground(Color.RED);
		this.lblPaidtimes.setName("lblPaidtimes");

		this.JFormatedTextFieldPaidTime = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
		this.JFormatedTextFieldPaidTime.setColumns(10);
		this.JFormatedTextFieldPaidTime.setName("txtPaidTimes");

		this.txaNotes = new JTextArea();
		this.txaNotes.setBorder(new LineBorder(SystemColor.controlShadow));
		this.txaNotes.setName("txaNotes");

		this.lblNotes = new JLabel("Notes");
		this.lblNotes.setName("lblNotes");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(137)
						.addComponent(this.lblContracts, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE).addGap(126))
				.addGroup(Alignment.LEADING,
						gl_contentPanel.createSequentialGroup().addGap(33).addComponent(this.lblNotes).addGap(18)
								.addComponent(this.txaNotes, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(
						gl_contentPanel.createSequentialGroup().addGap(113)
								.addComponent(this.lblPaidtimes, GroupLayout.PREFERRED_SIZE, 70,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.JFormatedTextFieldPaidTime, GroupLayout.PREFERRED_SIZE, 55,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(536, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(22)
						.addComponent(this.pnlDuration, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(113)
						.addComponent(this.lblRate, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(19)
						.addComponent(this.JFormatedTextFieldRate, GroupLayout.PREFERRED_SIZE, 55,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(536, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(22)
						.addComponent(this.pnlGeneralInfos, GroupLayout.PREFERRED_SIZE, 746, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(22)
						.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE).addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
								.addComponent(this.lblContracts, GroupLayout.PREFERRED_SIZE, 29,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(this.pnlGeneralInfos, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.lblRate)
										.addComponent(this.JFormatedTextFieldRate, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										ComponentPlacement.UNRELATED)
								.addComponent(this.pnlDuration, GroupLayout.PREFERRED_SIZE, 73,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(14)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(this.JFormatedTextFieldPaidTime,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(this.lblPaidtimes))
												.addGap(18)
												.addComponent(this.txaNotes, GroupLayout.PREFERRED_SIZE, 81,
														GroupLayout.PREFERRED_SIZE)
												.addGap(21))
										.addGroup(Alignment.TRAILING,
												gl_contentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(this.lblNotes).addGap(55)))));
		gl_contentPanel.linkSize(SwingConstants.VERTICAL, new Component[] { this.pnlDuration, this.panel });
		gl_contentPanel.linkSize(SwingConstants.VERTICAL,
				new Component[] { this.JFormatedTextFieldRate, this.JFormatedTextFieldPaidTime });
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { this.JFormatedTextFieldRate, this.JFormatedTextFieldPaidTime });

		this.JTextFieldInitialAmount = new JTextField();
		this.JTextFieldInitialAmount.setColumns(10);
		this.JTextFieldInitialAmount.setName("JTextFieldInitialAmount");

		this.lblInitial = new JLabel("Initial");
		this.lblInitial.setName("lblInitial");

		this.lblRemain = new JLabel("Remain");
		this.lblRemain.setName("lblRemain");
		
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		numberFormat.setMaximumFractionDigits(999999999);

		this.JFormatedTextFieldRemain = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(numberFormat)));
		this.JFormatedTextFieldRemain.setColumns(10);
		this.JFormatedTextFieldRemain.setName("txtRemainAmount");

		this.JFormatedTextFieldMax = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
		this.JFormatedTextFieldMax.setColumns(10);
		this.JFormatedTextFieldMax.setName("txtLoanMax");

		this.lblMax = new JLabel("Max");
		this.lblMax.setName("lblMax");
		GroupLayout gl_panel = new GroupLayout(this.panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addComponent(this.lblInitial, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.JTextFieldInitialAmount, GroupLayout.PREFERRED_SIZE,
												138, GroupLayout.PREFERRED_SIZE)
										.addGap(69)
										.addComponent(this.lblRemain, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(this.JFormatedTextFieldRemain, GroupLayout.PREFERRED_SIZE, 138,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
										.addComponent(this.lblMax, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.JFormatedTextFieldMax, GroupLayout.PREFERRED_SIZE, 138,
												GroupLayout.PREFERRED_SIZE)
										.addGap(16)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(this.lblMax)
										.addComponent(this.JFormatedTextFieldMax, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(this.lblRemain))
								.addComponent(this.JFormatedTextFieldRemain, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(this.lblInitial)
										.addComponent(this.JTextFieldInitialAmount, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] { this.JTextFieldInitialAmount,
				this.JFormatedTextFieldRemain, this.JFormatedTextFieldMax });
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] { this.JTextFieldInitialAmount,
				this.JFormatedTextFieldRemain, this.JFormatedTextFieldMax });
		this.panel.setLayout(gl_panel);

		this.dpnlInitiate = new JDatePanelImpl(new UtilDateModel(), p);
		this.dpkInitiate = new JDatePickerImpl(dpnlInitiate, new DateLabelFormatter());
		this.dpkInitiate.setName("datePickerInitiate");

		this.lblStart = new JLabel("Start");
		this.lblStart.setName("lblStart");

		this.dpnlDueDate = new JDatePanelImpl(new UtilDateModel(), p);
		this.dpkDueDate = new JDatePickerImpl(dpnlDueDate, new DateLabelFormatter());
		this.dpkDueDate.setName("datePickerDueDate");

		this.lblEnd = new JLabel("End");
		this.lblEnd.setName("lblEnd");

		this.lblMaturityPeriod = new JLabel("Maturity Period");
		this.lblMaturityPeriod.setName("lblMaturityPeriod");

		this.JFormatedTextFieldMaturityPeriod = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
		this.JFormatedTextFieldMaturityPeriod.setColumns(10);
		this.JFormatedTextFieldMaturityPeriod.setName("txtMaturityPeriod");

		this.JFormattedTextFieldLoanTerm = new JFormattedTextField(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
		this.JFormattedTextFieldLoanTerm.setColumns(10);
		this.JFormattedTextFieldLoanTerm.setName("txtLoanTerm");

		this.lblLoanTerm = new JLabel("Loan Term");
		this.lblLoanTerm.setName("lblLoanTerm");

		GroupLayout gl_pnlDuration = new GroupLayout(this.pnlDuration);
		gl_pnlDuration
				.setHorizontalGroup(
						gl_pnlDuration.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlDuration.createSequentialGroup().addContainerGap()
										.addComponent(this.lblStart).addGap(18)
										.addComponent(this.dpkInitiate, GroupLayout.PREFERRED_SIZE, 123,
												GroupLayout.PREFERRED_SIZE)
										.addGap(44).addComponent(this.lblMaturityPeriod).addGap(10)
										.addComponent(this.JFormatedTextFieldMaturityPeriod, GroupLayout.PREFERRED_SIZE,
												55, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(this.lblLoanTerm, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.JFormattedTextFieldLoanTerm, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
										.addComponent(this.lblEnd, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.dpkDueDate,
												GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addGap(33)));
		gl_pnlDuration.setVerticalGroup(gl_pnlDuration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDuration.createSequentialGroup().addGap(14).addComponent(this.lblStart)
						.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(gl_pnlDuration.createSequentialGroup().addGap(14)
						.addComponent(this.dpkInitiate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_pnlDuration.createSequentialGroup()
						.addContainerGap(14, Short.MAX_VALUE)
						.addGroup(gl_pnlDuration.createParallelGroup(Alignment.LEADING)
								.addComponent(this.dpkDueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlDuration.createParallelGroup(Alignment.LEADING)
										.addComponent(this.lblMaturityPeriod)
										.addComponent(this.JFormatedTextFieldMaturityPeriod, Alignment.TRAILING,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(this.lblLoanTerm)
								.addGroup(gl_pnlDuration.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.JFormattedTextFieldLoanTerm, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblEnd)))
						.addGap(13)));
		gl_pnlDuration
				.linkSize(SwingConstants.VERTICAL,
						new Component[] { this.lblStart, this.dpkDueDate, this.lblEnd, this.lblMaturityPeriod,
								this.JFormatedTextFieldMaturityPeriod, this.JFormattedTextFieldLoanTerm,
								this.lblLoanTerm });
		this.pnlDuration.setLayout(gl_pnlDuration);

		this.lblId = new JLabel("Id");
		this.lblId.setName("lblId");

		this.txtContractId = new JTextField();
		txtContractId.setEnabled(false);
		this.txtContractId.setName("txtContractId");
		this.txtContractId.setColumns(10);

		this.lblDate = new JLabel("Date");
		this.lblDate.setName("lblDate");

		this.dpnlContract = new JDatePanelImpl(new UtilDateModel(), p);
		this.dpkContract = new JDatePickerImpl(dpnlContract, new DateLabelFormatter());
		this.dpkContract.setName("datePickerContract");

		this.lblStaffId = new JLabel("Staff Id");
		this.lblStaffId.setName("lblStaffId");

		this.txtStaffId = new JTextFieldList();
		this.txtStaffId.setColumns(10);
		this.txtStaffId.setName("txtStaffId");

		this.lblCustomerId = new JLabel("Customer Id");
		this.lblCustomerId.setName("lblCustomerId");

		this.txtCustomerId = new JTextFieldList();
		this.txtCustomerId.setColumns(10);
		this.txtCustomerId.setName("txtCustomerId");

		this.lblTypeId = new JLabel("Type Id");
		this.lblTypeId.setName("lblTypeId");

		this.txtTypeId = new JTextFieldList();
		this.txtTypeId.setColumns(10);
		this.txtTypeId.setName("txtTypeId");

		this.lblCustomerName = new JLabel("");
		this.lblCustomerName.setForeground(Color.BLUE);
		this.lblCustomerName.setName("lblCustomerName");

		this.lblStaffName = new JLabel("");
		this.lblStaffName.setForeground(Color.BLUE);
		this.lblStaffName.setName("lblStaffName");

		this.lblLoanType = new JLabel("");
		this.lblLoanType.setForeground(Color.BLUE);
		this.lblLoanType.setName("lblLoanType");
		GroupLayout gl_pnlGeneralInfos = new GroupLayout(this.pnlGeneralInfos);
		gl_pnlGeneralInfos.setHorizontalGroup(gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addGroup(gl_pnlGeneralInfos
										.createParallelGroup(Alignment.LEADING).addComponent(this.lblId)
										.addComponent(this.lblCustomerId, GroupLayout.PREFERRED_SIZE, 67,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblStaffId))
										.addGap(37)
										.addGroup(gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlGeneralInfos.createSequentialGroup()
																.addComponent(this.txtContractId,
																		GroupLayout.PREFERRED_SIZE, 138,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(27)
																.addGroup(gl_pnlGeneralInfos
																		.createParallelGroup(Alignment.TRAILING)
																		.addGroup(gl_pnlGeneralInfos
																				.createSequentialGroup().addGap(153)
																				.addComponent(this.lblDate).addGap(18)
																				.addComponent(this.dpkContract,
																						GroupLayout.PREFERRED_SIZE, 171,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(117))
																		.addGroup(Alignment.LEADING, gl_pnlGeneralInfos
																				.createSequentialGroup().addGap(18)
																				.addGroup(gl_pnlGeneralInfos
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								this.lblCustomerName,
																								GroupLayout.PREFERRED_SIZE,
																								138,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(this.lblStaffName,
																								GroupLayout.PREFERRED_SIZE,
																								76,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(this.lblLoanType,
																								GroupLayout.PREFERRED_SIZE,
																								76,
																								GroupLayout.PREFERRED_SIZE))
																				.addContainerGap(326,
																						Short.MAX_VALUE))))
														.addGroup(gl_pnlGeneralInfos
																.createParallelGroup(Alignment.TRAILING)
																.addComponent(this.txtCustomerId,
																		GroupLayout.PREFERRED_SIZE, 138,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(this.txtStaffId,
																		GroupLayout.PREFERRED_SIZE, 138,
																		GroupLayout.PREFERRED_SIZE)))
												.addComponent(this.txtTypeId, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)))
								.addComponent(this.lblTypeId, GroupLayout.PREFERRED_SIZE, 67,
										GroupLayout.PREFERRED_SIZE))));
		gl_pnlGeneralInfos
				.setVerticalGroup(gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlGeneralInfos
						.createSequentialGroup().addGroup(gl_pnlGeneralInfos
								.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlGeneralInfos
										.createSequentialGroup().addGroup(gl_pnlGeneralInfos
												.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlGeneralInfos
														.createSequentialGroup().addGap(15).addComponent(this.lblDate))
												.addGroup(gl_pnlGeneralInfos
														.createSequentialGroup()
														.addGroup(gl_pnlGeneralInfos
																.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlGeneralInfos.createSequentialGroup()
																		.addContainerGap().addComponent(
																				this.dpkContract,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlGeneralInfos.createSequentialGroup()
																		.addGap(15).addComponent(this.lblId)))
														.addGap(11).addComponent(this.lblCustomerId))
												.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addGap(42)
														.addComponent(this.lblCustomerName)))
										.addGroup(
												gl_pnlGeneralInfos.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlGeneralInfos.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(this.lblStaffName))
														.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addGap(14)
																.addComponent(this.lblStaffId)))
										.addGroup(
												gl_pnlGeneralInfos
														.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlGeneralInfos.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(this.lblLoanType))
														.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addGap(14)
																.addComponent(this.lblTypeId))))
								.addGroup(gl_pnlGeneralInfos.createSequentialGroup().addContainerGap()
										.addComponent(this.txtContractId, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(this.txtCustomerId, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(this.txtStaffId, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.txtTypeId,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(54, Short.MAX_VALUE)));
		gl_pnlGeneralInfos.linkSize(SwingConstants.VERTICAL,
				new Component[] { this.txtContractId, this.txtStaffId, this.txtCustomerId, this.txtTypeId });
		gl_pnlGeneralInfos.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { this.lblCustomerName, this.lblStaffName, this.lblLoanType });
		gl_pnlGeneralInfos.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { this.lblId, this.lblStaffId, this.lblCustomerId, this.lblTypeId });
		this.pnlGeneralInfos.setLayout(gl_pnlGeneralInfos);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setName("btnSave");
				btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						do_okButton_actionPerformed(e);
					}
				});

				this.lblLoginfo = new JLabel("");
				this.lblLoginfo.setForeground(Color.BLUE);
				this.lblLoginfo.setName("lblLoginfo");
				buttonPane.add(this.lblLoginfo);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setName("btnCancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	public JDialog isUpdate(Contracts contracts) {
		isUpdate = true;
		this.setTitle("Add Contract");
		txtContractId.setText(Integer.toString(contracts.getContractId()));
		Calendar cldContractDate = new GregorianCalendar();
		cldContractDate.setTime(contracts.getContractDate());
		dpkContract.getModel().setDate(cldContractDate.get(Calendar.YEAR), cldContractDate.get(Calendar.MONTH),
				cldContractDate.get(Calendar.DAY_OF_MONTH));
		dpkContract.getModel().setSelected(true);
		txtCustomerId.setText(Integer.toString(contracts.getCustomers().getCustomerId()));
		lblCustomerName.setText(contracts.getCustomers().getCustomerName());
		txtStaffId.setText(Integer.toString(contracts.getStaffs().getStaffId()));
		lblStaffName.setText(contracts.getStaffs().getStaffName());
		txtTypeId.setText(Integer.toString(contracts.getLoanTypes().getLoanTypeId()));
		lblLoanType.setText(contracts.getLoanTypes().getLoanTypeName());
		JFormatedTextFieldRate.setText(String.valueOf(contracts.getLoanTypes().getLoanRate()));
		Calendar cldInitiateDate = new GregorianCalendar();
		cldInitiateDate.setTime(contracts.getInitiateDate());
		dpkInitiate.getModel().setDate(cldInitiateDate.get(Calendar.YEAR), cldInitiateDate.get(Calendar.MONTH),
				cldInitiateDate.get(Calendar.DAY_OF_MONTH));
		dpkInitiate.getModel().setSelected(true);
		JFormatedTextFieldMaturityPeriod.setText(Integer.toString(contracts.getMaturityPeriod()));
		JFormattedTextFieldLoanTerm.setText(Integer.toString(contracts.getLoanTerm()));
		Calendar cldDueDate = new GregorianCalendar();
		cldDueDate.setTime(contracts.getDueDate());
		dpkDueDate.getModel().setDate(cldDueDate.get(Calendar.YEAR), cldDueDate.get(Calendar.MONTH),
				cldDueDate.get(Calendar.DAY_OF_MONTH));
		dpkDueDate.getModel().setSelected(true);
		JTextFieldInitialAmount.setText(String.valueOf(contracts.getInitialAmount()));
		JFormatedTextFieldRemain.setText(String.valueOf(contracts.getRemainAmount()));
		JFormatedTextFieldMax.setText(String.valueOf(contracts.getLoanMax()));
		JFormatedTextFieldPaidTime.setText(Integer.toString(contracts.getPaidTimes()));
		long date = Long.parseLong(contracts.getCreateLog().substring(0, 13));
		String user = contracts.getCreateLog().substring(13, contracts.getCreateLog().length());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date resultdate = new Date(date);
		lblLoginfo.setText("Create by: " + user + " Date: " + sdf.format(resultdate));
		return this;
	}

	public class DateLabelFormatter extends AbstractFormatter {

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

	protected void do_this_windowClosing(WindowEvent arg0) {

	}

	protected void do_okButton_actionPerformed(ActionEvent e) {
		if (contracts == null) {
			contracts = new Contracts();
		}
		try {
			contracts.setContractDate((Date) dpkContract.getModel().getValue());
			contracts.setCustomers(new CustomersDAO().find(Integer.parseInt(txtCustomerId.getText())));
			contracts.setStaffs(new StaffsDAO().find(Integer.parseInt(txtStaffId.getText())));
			contracts.setLoanTypes(new LoanTypesDAO().find(Integer.parseInt(txtTypeId.getText())));
			contracts.setInitiateDate((Date) dpkInitiate.getModel().getValue());
			contracts.setMaturityPeriod(Integer.parseInt(JFormatedTextFieldMaturityPeriod.getText()));
			contracts.setLoanTerm(Integer.parseInt(JFormattedTextFieldLoanTerm.getText()));
			contracts.setDueDate((Date) dpkDueDate.getModel().getValue());
			contracts.setInitialAmount(
					BigDecimal.valueOf(Double.parseDouble(JTextFieldInitialAmount.getText())));
			System.out.println(JTextFieldInitialAmount.getText());
			contracts.setRemainAmount(BigDecimal.valueOf(Double.parseDouble(JFormatedTextFieldRemain.getText())));
			contracts.setLoanMax(BigDecimal.valueOf(Double.parseDouble(JFormatedTextFieldMax.getText())));
			contracts.setCreateLog(System.currentTimeMillis() + JFrameLogin.username);
			contracts.setNotes(txaNotes.getText());
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<Contracts>> constraintViolations = validator.validate(contracts);
			if (!constraintViolations.isEmpty()) {
				String error = "";
				for (ConstraintViolation<Contracts> violation : constraintViolations) {
					error += violation.getMessage() + "\n";
				}
				JOptionPane.showMessageDialog(null, error);
			} else {
				if (isUpdate) {
					new ContractsDAO().update(contracts);
				} else {
					new ContractsDAO().create(contracts);
				}
				JOptionPane.showMessageDialog(null, (isUpdate ? "Update this" : "Add new") + " contract success!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Can't " + (isUpdate ? "Update this" : "Add new") + " contract!" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
}
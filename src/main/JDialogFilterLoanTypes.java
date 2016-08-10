package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

public class JDialogFilterLoanTypes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> JComboBoxField;
	private JLabel lblFindBy;
	private JCheckBox JCheckBoxIsAscending;
	private JTextField JTextFieldValue;
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton JButtonAddNewField;
	private JPanel JPanelRowFilter;
	GridBagConstraints gbc_JPanelRowFilter = new GridBagConstraints();
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public JDialogFilterLoanTypes() {
		setTitle("Filter");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 622, 0 };
		gridBagLayout.rowHeights = new int[] { 39, 39, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		gbc_JPanelRowFilter.gridy = 0;
		gbc_JPanelRowFilter.gridx = 0;
		contentPanel.add(createNewComponent(), gbc_JPanelRowFilter);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("Filter");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filter();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.pack();
		this.setModal(true);

	}
	//ĐANG LÀM ldt
	public Component createNewComponent() {
		JPanelRowFilter = new JPanel();
		GridBagLayout gbl_JPanelRowFilter = new GridBagLayout();
		gbl_JPanelRowFilter.columnWidths = new int[] { 55, 141, 192, 106, 106, 0 };
		gbl_JPanelRowFilter.rowHeights = new int[] { 29, 0 };
		gbl_JPanelRowFilter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_JPanelRowFilter.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		JPanelRowFilter.setLayout(gbl_JPanelRowFilter);

		lblFindBy = new JLabel("Find by");
		GridBagConstraints gbc_lblFindBy = new GridBagConstraints();
		gbc_lblFindBy.fill = GridBagConstraints.BOTH;
		gbc_lblFindBy.insets = new Insets(0, 0, 0, 5);
		gbc_lblFindBy.gridx = 0;
		gbc_lblFindBy.gridy = 0;
		JPanelRowFilter.add(lblFindBy, gbc_lblFindBy);

		JComboBoxField = new JComboBox<String>();
		GridBagConstraints gbc_JComboBoxField = new GridBagConstraints();
		gbc_JComboBoxField.fill = GridBagConstraints.BOTH;
		gbc_JComboBoxField.insets = new Insets(0, 0, 0, 5);
		gbc_JComboBoxField.gridx = 1;
		gbc_JComboBoxField.gridy = 0;
		JPanelRowFilter.add(JComboBoxField, gbc_JComboBoxField);
		JComboBoxField.setName("Field1");

		JTextFieldValue = new JTextField();
		GridBagConstraints gbc_JTextFieldValue = new GridBagConstraints();
		gbc_JTextFieldValue.fill = GridBagConstraints.BOTH;
		gbc_JTextFieldValue.insets = new Insets(0, 0, 0, 5);
		gbc_JTextFieldValue.gridx = 2;
		gbc_JTextFieldValue.gridy = 0;
		JPanelRowFilter.add(JTextFieldValue, gbc_JTextFieldValue);
		JTextFieldValue.setName("Value1");

		JCheckBoxIsAscending = new JCheckBox("Is Ascending");
		GridBagConstraints gbc_JCheckBoxIsAscending = new GridBagConstraints();
		gbc_JCheckBoxIsAscending.fill = GridBagConstraints.BOTH;
		gbc_JCheckBoxIsAscending.insets = new Insets(0, 0, 0, 5);
		gbc_JCheckBoxIsAscending.gridx = 3;
		gbc_JCheckBoxIsAscending.gridy = 0;
		JPanelRowFilter.add(JCheckBoxIsAscending, gbc_JCheckBoxIsAscending);
		JCheckBoxIsAscending.setName("IsAsc1");

		JButtonAddNewField = new JButton("");
		JButtonAddNewField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewRow();
			}
		});
		GridBagConstraints gbc_JButtonAddNewField = new GridBagConstraints();
		gbc_JButtonAddNewField.fill = GridBagConstraints.BOTH;
		gbc_JButtonAddNewField.gridx = 4;
		gbc_JButtonAddNewField.gridy = 0;
		JPanelRowFilter.add(JButtonAddNewField, gbc_JButtonAddNewField);
		JButtonAddNewField.setName("Add1");
		JButtonAddNewField.setToolTipText("Add new field filter.");
		return JPanelRowFilter;
	}

	public void loadComboBox(String[] columns) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (String column : columns) {
			defaultComboBoxModel.addElement(column);
		}
		JComboBoxField.setModel(defaultComboBoxModel);
	}

	public void filter() {
		JPanelLoanType.tableRowSort(JTextFieldValue.getText(), JComboBoxField.getSelectedIndex() + 1);
		this.dispose();
	}
	//ĐANG LÀM ldt
	private void addNewRow() {
		gbc_JPanelRowFilter.gridy++;
		gbc_JPanelRowFilter.gridx = 0;
		contentPanel.add(createNewComponent(), gbc_JPanelRowFilter);
		repaint();
	}
}

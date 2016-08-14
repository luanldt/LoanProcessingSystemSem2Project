package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import factory.AbstractJPanel;

public class JDialogFilterLoanType<T> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel lblFindBy;
	private JComboBox JComboBoxField1;
	private JTextField JTextFieldValue1;
	private JCheckBox JCheckBoxIsDescending1;
	private JButton JButtonAddRow1;
	private T jPanel;
	
	public static void main(String[] args) {
		new JDialogFilterLoanType<>(null).setVisible(true);;
	}

	/**
	 * Create the dialog.
	 * @param <T>
	 */
	public <T> JDialogFilterLoanType(T jPanel) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		panel = new JPanel();
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 46, 141, 206, 121, 75, 0 };
		gbl_panel.rowHeights = new int[] { 29, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblFindBy = new JLabel("Find by");
		GridBagConstraints gbc_lblFindBy = new GridBagConstraints();
		gbc_lblFindBy.anchor = GridBagConstraints.WEST;
		gbc_lblFindBy.insets = new Insets(0, 0, 0, 5);
		gbc_lblFindBy.gridx = 0;
		gbc_lblFindBy.gridy = 0;
		panel.add(lblFindBy, gbc_lblFindBy);

		JComboBoxField1 = new JComboBox();
		JComboBoxField1.setModel(new DefaultComboBoxModel(
				new String[] { "Test Row 1", "Test Row 2", "Test Row 3", "Test Row 4", "Test Row 5", "Test Row 6" }));
		GridBagConstraints gbc_JComboBoxField1 = new GridBagConstraints();
		gbc_JComboBoxField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_JComboBoxField1.insets = new Insets(0, 0, 0, 5);
		gbc_JComboBoxField1.gridx = 1;
		gbc_JComboBoxField1.gridy = 0;
		panel.add(JComboBoxField1, gbc_JComboBoxField1);

		JTextFieldValue1 = new JTextField();
		JTextFieldValue1.setColumns(10);
		GridBagConstraints gbc_JTextFieldValue1 = new GridBagConstraints();
		gbc_JTextFieldValue1.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextFieldValue1.insets = new Insets(0, 0, 0, 5);
		gbc_JTextFieldValue1.gridx = 2;
		gbc_JTextFieldValue1.gridy = 0;
		panel.add(JTextFieldValue1, gbc_JTextFieldValue1);

		JCheckBoxIsDescending1 = new JCheckBox("Is Descending");
		GridBagConstraints gbc_JCheckBoxIsDescending1 = new GridBagConstraints();
		gbc_JCheckBoxIsDescending1.anchor = GridBagConstraints.WEST;
		gbc_JCheckBoxIsDescending1.insets = new Insets(0, 0, 0, 5);
		gbc_JCheckBoxIsDescending1.gridx = 3;
		gbc_JCheckBoxIsDescending1.gridy = 0;
		panel.add(JCheckBoxIsDescending1, gbc_JCheckBoxIsDescending1);

		JButtonAddRow1 = new JButton("+");
		JButtonAddRow1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewRow();
			}
		});
		GridBagConstraints gbc_JButtonAddRow1 = new GridBagConstraints();
		gbc_JButtonAddRow1.anchor = GridBagConstraints.NORTHWEST;
		gbc_JButtonAddRow1.gridx = 4;
		gbc_JButtonAddRow1.gridy = 0;
		panel.add(JButtonAddRow1, gbc_JButtonAddRow1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
		
		filter();
	}

	private void addNewRow() {

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 46, 141, 206, 121, 75, 0 };
		gbl_panel.rowHeights = new int[] { 29, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		GridBagConstraints gbc_lblFindBy = new GridBagConstraints();
		gbc_lblFindBy.anchor = GridBagConstraints.WEST;
		gbc_lblFindBy.insets = new Insets(0, 0, 0, 5);
		gbc_lblFindBy.gridx = 0;
		gbc_lblFindBy.gridy = 0;
		panel.add(new Label("Find by"), gbc_lblFindBy);

		GridBagConstraints gbc_JComboBoxField1 = new GridBagConstraints();
		gbc_JComboBoxField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_JComboBoxField1.insets = new Insets(0, 0, 0, 5);
		gbc_JComboBoxField1.gridx = 1;
		gbc_JComboBoxField1.gridy = 0;
		JComboBox<String> jComboBox = new JComboBox<String>();
		panel.add(jComboBox, gbc_JComboBoxField1);

		GridBagConstraints gbc_JTextFieldValue1 = new GridBagConstraints();
		gbc_JTextFieldValue1.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextFieldValue1.insets = new Insets(0, 0, 0, 5);
		gbc_JTextFieldValue1.gridx = 2;
		gbc_JTextFieldValue1.gridy = 0;
		panel.add(new JTextField(), gbc_JTextFieldValue1);
		
		GridBagConstraints gbc_JCheckBoxIsDescending1 = new GridBagConstraints();
		gbc_JCheckBoxIsDescending1.anchor = GridBagConstraints.WEST;
		gbc_JCheckBoxIsDescending1.insets = new Insets(0, 0, 0, 5);
		gbc_JCheckBoxIsDescending1.gridx = 3;
		gbc_JCheckBoxIsDescending1.gridy = 0;
		panel.add(new JCheckBox("Is Descending"), gbc_JCheckBoxIsDescending1);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewRow();
			}
		});
		GridBagConstraints gbc_JButtonAddRow1 = new GridBagConstraints();
		gbc_JButtonAddRow1.anchor = GridBagConstraints.NORTHWEST;
		gbc_JButtonAddRow1.gridx = 4;
		gbc_JButtonAddRow1.gridy = 0;
		panel.add(button, gbc_JButtonAddRow1);
		this.revalidate();
		this.pack();
	}
	
	private void filter() {
		int row = 0;
		String value = null;
		for(Component component : ((JPanel)this.getContentPane().getComponent(0)).getComponents()) {
			for(Component component2 : ((JPanel)component).getComponents()) {
				if(component2 instanceof JComboBox) {
					 row = ((JComboBox) component2).getSelectedIndex();
				}
				if(component2 instanceof JTextField) {
					value = ((JTextField) component2).getText();
				}
			}
		}

		//((JPanelLoanType)jPanel).tableRowSort(value, row);
	}

}

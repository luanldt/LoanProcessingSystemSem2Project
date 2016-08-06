package main;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class JInternalFrameLoanCustomer extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable JTableLoanCustomer;

	/**
	 * Create the frame.
	 */
	public JInternalFrameLoanCustomer() {
		setTitle("Customer");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					setMaximum(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		setBounds(100, 100, 450, 300);

		JTableLoanCustomer = new JTable();
		getContentPane().add(JTableLoanCustomer, BorderLayout.CENTER);

	}
}

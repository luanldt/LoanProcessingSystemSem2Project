package main;

import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;

public class JInternalFrameLoanType extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable JTableLoanType;

	/**
	 * Create the frame.
	 */
	public JInternalFrameLoanType() {
		setTitle("LoanType");
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

		JTableLoanType = new JTable();
		getContentPane().add(JTableLoanType, BorderLayout.CENTER);

	}

	public void loadTable() {
		
	}

}

package main;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;

public class JInternalFrameVoucher extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable JTableVoucher;

	/**
	 * Create the frame.
	 */
	public JInternalFrameVoucher() {
		setTitle("Voucher");
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
		
		JTableVoucher = new JTable();
		getContentPane().add(JTableVoucher, BorderLayout.CENTER);
	}

}

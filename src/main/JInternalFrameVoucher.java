package main;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;

public class JInternalFrameVoucher extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JTable JTableVoucherHeader;
	private JTable JTableVoucherDetail;

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
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JTableVoucherHeader = new JTable();
		splitPane.setLeftComponent(JTableVoucherHeader);
		
		JTableVoucherDetail = new JTable();
		splitPane.setRightComponent(JTableVoucherDetail);
	}

}

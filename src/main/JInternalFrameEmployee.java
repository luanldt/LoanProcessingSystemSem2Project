package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


public class JInternalFrameEmployee extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable JTableEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameEmployee frame = new JInternalFrameEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JInternalFrameEmployee() {
		setTitle("Employee");
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

		JTableEmployee = new JTable();
		getContentPane().add(JTableEmployee, BorderLayout.CENTER);

	}

	
}

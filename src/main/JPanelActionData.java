package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import model.ProcessAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelActionData extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton JButtonAdd;
	private JButton JButtonUpdate;
	private JButton JButtonDelete;
	private JButton JButtonSearch;
	private JButton JButtonRefresh;

	/**
	 * Create the panel.
	 */
	public JPanelActionData() {
		
		ProcessAction processAction = new ProcessAction();
		
		setBorder(new LineBorder(new Color(0, 153, 255)));
		
		JButtonAdd = new JButton("Add");
		JButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.addAction(getNameJInternalFrame());
			}
		});
		add(JButtonAdd);
		
		JButtonUpdate = new JButton("Update");
		JButtonUpdate.setEnabled(false);
		add(JButtonUpdate);
		
		JButtonDelete = new JButton("Delete");
		JButtonDelete.setEnabled(false);
		add(JButtonDelete);
		
		JButtonSearch = new JButton("Filter");
		add(JButtonSearch);
		
		JButtonRefresh = new JButton("Refresh");
		add(JButtonRefresh);

	}
	
	
	private String getNameJInternalFrame() {
		return this.getParent().getParent().getParent().getParent().getName();
	}

}

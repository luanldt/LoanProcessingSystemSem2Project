package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import model.ProcessAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelActionData extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton JButtonAdd;
	public static JButton JButtonUpdate;
	public static JButton JButtonDelete;
	public static JButton JButtonSearch;
	public static JButton JButtonRefresh;

	/**
	 * Create the panel.
	 */
	public JPanelActionData() {

		ProcessAction processAction = new ProcessAction();

		setBorder(new LineBorder(new Color(0, 153, 255)));

		JButtonAdd = new JButton("Add");
		JButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.addUpdateAction(getJPanelName(), ProcessAction.ADD);
			}
		});
		add(JButtonAdd);

		JButtonUpdate = new JButton("Update");
		JButtonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.addUpdateAction(getJPanelName(), ProcessAction.UPDATE);
			}
		});
		JButtonUpdate.setEnabled(false);
		add(JButtonUpdate);

		JButtonDelete = new JButton("Delete");
		JButtonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.deleteAction(getJPanelName());
			}
		});
		JButtonDelete.setEnabled(false);
		add(JButtonDelete);

		JButtonSearch = new JButton("Filter");
		JButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.filter(getJPanelName());
			}
		});
		add(JButtonSearch);

		JButtonRefresh = new JButton("Refresh");
		JButtonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.refresh(getJPanelName());
			}
		});
		
		add(JButtonRefresh);

	}

	private String getJPanelName() {
		return this.getParent().getName();
	}

}

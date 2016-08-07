package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
		setBorder(new LineBorder(new Color(0, 153, 255)));
		
		JButtonAdd = new JButton("Add");
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

}

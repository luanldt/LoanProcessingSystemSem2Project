package model;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.JFrameMain;

public class JTabbedPaneCloseButton extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTabbedPaneCloseButton() {
		super();
	}

	/* Override Addtab in order to add the close Button everytime */
	@Override
	public void addTab(String title, Icon icon, Component component, String tip) {
		super.addTab(title, icon, component, tip);
		int count = this.getTabCount() - 1;
		setTabComponentAt(count, new CloseButtonTab(component, title, icon));
	}

	@Override
	public void addTab(String title, Icon icon, Component component) {
		addTab(title, icon, component, null);
	}

	@Override
	public void addTab(String title, Component component) {
		addTab(title, null, component);
	}

	/* addTabNoExit */
	public void addTabNoExit(String title, Icon icon, Component component, String tip) {
		super.addTab(title, icon, component, tip);
	}

	public void addTabNoExit(String title, Icon icon, Component component) {
		addTabNoExit(title, icon, component, null);
	}

	public void addTabNoExit(String title, Component component) {
		addTabNoExit(title, null, component);
	}

	/* Button */
	public class CloseButtonTab extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private Component tab;

		public CloseButtonTab(final Component tab, String title, Icon icon) {
			this.tab = tab;
			setOpaque(false);
			FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
			setLayout(flowLayout);
			JLabel jLabel = new JLabel(title);
			jLabel.setIcon(icon);
			add(jLabel);
			JButton button = new JButton(MakeIcon.getIcon("close", 12));
			button.setOpaque(false);
			button.setMargin(new Insets(-5, -13, -5, -13));
			button.addMouseListener(new CloseListener(tab));
			add(button);
		}
	}

	/* ClickListener */
	public class CloseListener implements MouseListener {
		private Component tab;

		public CloseListener(Component tab) {
			this.tab = tab;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
				tabbedPane.remove(tab);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@SuppressWarnings("unused")
		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				// clickedButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
			}
		}

		@SuppressWarnings("unused")
		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				// clickedButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
			}
		}
	}
}
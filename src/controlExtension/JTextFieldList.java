package controlExtension;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class JTextFieldList extends JTextField {
	private static String key;
	private JDialogLookup dialogLookup;

	public JTextFieldList() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				do_this_keyReleased(arg0);
			}
		});
		// Sự kiện để phát hiện ra khi người dùng nhập chữ vào JTextField.
	}

	@SuppressWarnings("static-access")
	protected void do_this_keyReleased(KeyEvent arg0) {
		if (this.getName().toLowerCase().contains("customer")) {
			key = "customer";
		} else if (this.getName().toLowerCase().contains("type")) {
			key = "type";
		} else if (this.getName().toLowerCase().contains("staff")) {
			key = "staff";
		} else if (this.getName().toLowerCase().contains("contract")) { // Thêm vào cho contract id ở jdialog modify payment
			JTextFieldList.key = "contract";
		}
		if (dialogLookup == null || !dialogLookup.isVisible()) {
			dialogLookup = new JDialogLookup(key, this);
			dialogLookup.setLocation(this.getLocationOnScreen().x, this.getLocationOnScreen().y + this.getHeight());
			dialogLookup.setVisible(true);
			this.requestFocus();
		}

		if (this.getText().length() > 0)
			dialogLookup.refreshData(this.getText());
		else {
			dialogLookup.setVisible(false);
		}

		if (arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_UP) {
			this.transferFocus();
			dialogLookup.requestFocus();
			dialogLookup.transferFocus();
		}
		// else if(arg0.getKeyCode() == KeyEvent.VK_ENTER||arg0.getKeyCode() ==
		// KeyEvent.VK_TAB){
		// dialogLookup.setVisible(false);
		// }
	}
}
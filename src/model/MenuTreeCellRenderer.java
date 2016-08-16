package model;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

/**
 * Render các item trong từng menu item của JTree menu
 * 
 * @author LuậnĐẹpTrai
 *
 */
public class MenuTreeCellRenderer implements TreeCellRenderer {

	private JLabel label;

	public MenuTreeCellRenderer() {
		label = new JLabel();
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasForcus) {
		Object o = ((DefaultMutableTreeNode) value).getUserObject();
		if (o != null) {
			// Trường hợp tại đây phải + thêm menu là vì trong thư mục icon đặt thêm
			// chữ menu để phân biệt
			label.setFont(new Font(label.getFont().getFontName(), label.getFont().getStyle(), 14));
			label.setIcon(MakeIcon.getIcon("menu" + o.toString().replaceAll("\\s+", ""), MakeIcon.ICON_20));
			label.setText(o.toString());
		} else {
			label.setFont(new Font(label.getFont().getFontName(), label.getFont().getStyle(), 18));
			label.setIcon(null);
			label.setText("" + value);
		}
		return label;
		
	}
	
	

}

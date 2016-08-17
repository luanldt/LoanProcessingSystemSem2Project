package factory;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;
import javax.swing.table.JTableHeader;
import main.JFrameMain;
import model.CustomTableModel;

/**
 * Đây là class dùng để kế thừa để tạo nên 1 jpanel có table và các sự kiện của
 * table chỉ cần gọi và sử dụng các table giống nhau nhưng sẽ có các model khác
 * nhau và tên khác nhau cần truyền vào tên của panel để nhận biết CLASS NÀY ĐÃ
 * ĐẦY ĐỦ VÀ KHÔNG CHỈNH SỬA NỮA TRỪ KHI CÓ YỀU CẦU
 * 
 * @author LUANDEPTRAI
 *
 */
public abstract class AbstractJPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////////
	// CLASS NAY DA HOAN THANH VA KHI XU DUNG NEU CO GI THAY DOI HOAC MUON THAY //
	// DOI THI XIN VIET THEM CHU DUNG THAY DOI CODE GOC //
	//////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = 1L;

	public JTable JTable;
	private JScrollPane jScrollPane;
	private JPopupMenu popupMenu;
	private JMenuItem mntmUpdate;
	private JMenuItem mntmAdd;
	private JMenuItem mntmDelete;
	private JMenuItem mntmRefresh;
	private CustomTableModel customTableModel;
	private TableRowSorter<CustomTableModel> tableRowSorter;

	public AbstractJPanel() {

	}

	public AbstractJPanel(Class<?> classObj) {
		setName(classObj.getName());
		setLayout(new BorderLayout());
		jScrollPane = new JScrollPane();
		jScrollPane.setBorder(null);
		JTable = new JTable();

		JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					Point p = e.getPoint();
					int rowNumber = JTable.rowAtPoint(p);
					JTable.setRowSelectionInterval(rowNumber, rowNumber);
				}
				JFrameMain.JButtonUpdate.setEnabled(true);
				JFrameMain.JButtonDelete.setEnabled(true);
				JFrameMain.currentId = (int) customTableModel.getValueAt(JTable.getSelectedRow(), 0);
			}
		});
		JTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTable.setRowSelectionAllowed(true);
		JTable.setColumnSelectionAllowed(false);
		jScrollPane.setViewportView(JTable);

		add(jScrollPane, BorderLayout.CENTER);

		popupMenu = new JPopupMenu();

		mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// actionAdd();
			}
		});

		popupMenu.add(mntmAdd);

		mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// actionUpdate();
			}
		});
		popupMenu.add(mntmUpdate);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// actionDelete();
			}
		});
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		popupMenu.add(mntmDelete);

		mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// actionRefresh();
			}
		});
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		popupMenu.add(mntmRefresh);

		JTable.setComponentPopupMenu(popupMenu);
		JTable.setRowSorter(null);
		JTable.getTableHeader().setReorderingAllowed(false);
	}

	/**
	 * TRUYỀN VÀO MỘT MODEL KIỂU <br>
	 * <b style="color: blue">CustomTableModel</b><br>
	 * <b style="color: blue">AbstractTableModel</b>
	 * 
	 * @param defaultTableModel
	 */
	public void setModel(CustomTableModel customTableModel) {
		JTable.setModel(customTableModel);
		this.customTableModel = customTableModel;
		tableRowSorter = new TableRowSorter<CustomTableModel>(customTableModel);
	}

	public CustomTableModel getModal() {
		return customTableModel;
	}

	/**
	 * METHOD NÀY PHẢI ĐƯỢC KẾ THỪA ĐỂ VIẾT LẠI<br>
	 */
	public abstract void loadTable();

	public void filter(String sValue) {
		if (sValue.isEmpty()) {
			tableRowSorter.setRowFilter(null);
		} else {
			tableRowSorter.setRowFilter(RowFilter.regexFilter(sValue));
		}
		JTable.setRowSorter(tableRowSorter);

	}
}

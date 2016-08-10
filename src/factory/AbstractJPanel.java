package factory;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import main.JPanelActionData;
import model.CustomTableModel;
import model.ProcessAction;

/**
 * Đây là class dùng để kế thừa để tạo nên 1 jpanel có table và các sự kiện của
 * table chỉ cần gọi và sử dụng các table giống nhau nhưng sẽ có các model khác
 * nhau và tên khác nhau cần truyền vào tên của panel để nhận biết
 * 
 * @author LUANDEPTRAI
 *
 */
public class AbstractJPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////////
	// CLASS NAY DA HOAN THANH VA KHI XU DUNG NEU CO GI THAY DOI HOAC MUON THAY //
	// DOI THI XIN VIET THEM CHU DUNG THAY DOI CODE GOC //
	//////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = 1L;
	public static JTable JTable;
	private JScrollPane jScrollPane;
	private JPopupMenu popupMenu;
	private JMenuItem mntmUpdate;
	private JMenuItem mntmAdd;
	private JMenuItem mntmDelete;
	private JMenuItem mntmRefresh;
	private static TableRowSorter<CustomTableModel> rowSorter;
	private ProcessAction processAction = new ProcessAction();
	private static CustomTableModel customTableModel;

	public AbstractJPanel(String name) {
		setName(name);
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
				JPanelActionData.JButtonUpdate.setEnabled(true);
				JPanelActionData.JButtonDelete.setEnabled(true);
				ProcessAction.currentId = (int) JTable.getValueAt(JTable.getSelectedRow(), 0);
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
			public void actionPerformed(ActionEvent e) {
				processAction.addUpdateAction(name, ProcessAction.ADD);
			}
		});

		popupMenu.add(mntmAdd);

		mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.addUpdateAction(name, ProcessAction.UPDATE);
			}
		});
		popupMenu.add(mntmUpdate);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.deleteAction(name);
			}
		});
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		popupMenu.add(mntmDelete);

		mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAction.refresh(name);
			}
		});
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		popupMenu.add(mntmRefresh);

		JTable.setComponentPopupMenu(popupMenu);
		rowSorter = new TableRowSorter<CustomTableModel>();
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
	public static void setModel(CustomTableModel customTableModel) {
		JTable.setModel(customTableModel);
		AbstractJPanel.customTableModel = customTableModel;
	}

	public static CustomTableModel getModal() {
		return customTableModel;
	}

	/**
	 * METHOD NÀY PHẢI ĐƯỢC KẾ THỪA ĐỂ VIẾT LẠI<br>
	 * <i><span style="color: red">Chú ý:</span> Method này là tĩnh (static) nên
	 * khi kế thừa phải chú ý.</i>
	 */
	public static void loadTable() {

	}

	/**
	 * METHOD NÀY DÙNG ĐỂ SET FILTER CHO TABLE VÀ CHÚ Ý VÀO CLASS PROCESS ACTION
	 * ĐÃ XỬ LÝ PHẦN NÀY RỒI<br>
	 * NÊN CHÚNG TA KHÔNG QUAN TÂM NHIỀU LẮM TỚI PHẦN NÀY<br>
	 * <i><span style="color: red">Chú ý:</span> Method này là tĩnh (static) nên
	 * khi nếu có kế thừa phải chú ý.</i>
	 * 
	 * @param field
	 * @param row
	 * @param isDate
	 */
	public static void tableRowSort(String value, int row) {
		rowSorter.setModel(getModal());
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + value, row));
		JTable.setRowSorter(rowSorter);
	}

	/**
	 * METHOD NÀY DÙNG ĐỂ SET FILTER CHO TABLE VÀ FILTER THEO ĐOẠN KIỂU NGÀY
	 * <i><span style="color: red">Chú ý:</span> Method này là tĩnh (static) nên
	 * khi nếu có kế thừa phải chú ý.</i>
	 * 
	 * @param value
	 * @param row
	 */
	public static void tableRowSort(List<Date> value, int row) {
		rowSorter.setModel(getModal());
		rowSorter.setRowFilter(RowFilter.dateFilter(ComparisonType.AFTER, value.get(0), row));
		rowSorter.setRowFilter(RowFilter.dateFilter(ComparisonType.BEFORE, value.get(1), row));
		JTable.setRowSorter(rowSorter);
		//TODO: khi có dữ liệu thì test method này
	}
}

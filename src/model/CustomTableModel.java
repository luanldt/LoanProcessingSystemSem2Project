package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Lần sửa chữa thứ 2
 * 
 * @author nguyenminhluan
 *
 */
public class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<String> columnsName = new ArrayList<String>();
	private List<Object[]> dataRow = new ArrayList<Object[]>();

	/**
	 * Thêm tên column vào trong model
	 * 
	 * @param columnName
	 */
	public void addColumn(String columnName) {
		this.columnsName.add(columnName);
	}

	public void addRow(Object[] rows) {
		this.dataRow.add(rows);
	}

	public String[] getColumns() {
		return (String[]) columnsName.toArray(new String[columnsName.size()]);
	}

	@Override
	public int getRowCount() {
		return dataRow.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnsName.get(column);
	}

	@Override
	public int getColumnCount() {
		return columnsName.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println(dataRow.size());
		return dataRow.get(rowIndex)[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}

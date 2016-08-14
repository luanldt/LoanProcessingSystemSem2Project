package model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * Lần sửa chữa thứ 2
 * 
 * @author nguyenminhluan
 *
 */
public class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnsName = {};
	private Vector<Object[]> dataRow = new Vector<Object[]>();

	/**
	 * Thêm tên column vào trong model
	 * 
	 * @param columnName
	 */
	public void addColumn(String[] columnsName) {
		this.columnsName = columnsName;
	}

	public void addRow(Object[] rows) {
		this.dataRow.add(rows);
	}

	public String[] getColumns() {
		return this.columnsName;
	}

	@Override
	public int getRowCount() {
		return dataRow.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnsName[column];
	}

	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return dataRow.get(rowIndex)[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}

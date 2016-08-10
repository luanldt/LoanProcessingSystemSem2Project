package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> columnNames = new ArrayList<String>();
	private ArrayList<Object[]> data = new ArrayList<Object[]>();

	public void addColumn(String columnName) {
		columnNames.add(columnName);
	}

	public void addRow(Object[] rows) {
		data.add(rows);
	}

	public String[] getColums() {
		String[] s = new String[getColumnCount()];
		return columnNames.toArray(s);
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}

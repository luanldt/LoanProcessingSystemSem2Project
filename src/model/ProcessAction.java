package model;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.hibernate.metamodel.source.binder.JpaCallbackClass;

import dao.LoanTypesDAO;
import factory.AbstractJPanel;
import main.JDialogAddLoanTypes;
import main.JDialogFilterLoanTypes;
import main.JPanelLoanType;

public class ProcessAction {

	public static final int ADD = 0;
	public static final int UPDATE = 1;
	public static int currentId; // Biến này dùng để nhận giá trị của dòng hiện
																// tại trên table được chọn

	// tableName là tên của jInternal frame đã được set từ trước để lúc truyền vào
	// thì phương thức biết là
	// nút button đang đứng ở jinternal frame nào
	public void addUpdateAction(String name, int order) {
		switch (name) {
		case "LoanTypes":
			if (order == ADD) {
				new JDialogAddLoanTypes().setVisible(true);
			} else {
				new JDialogAddLoanTypes().isUpdate(new LoanTypesDAO().find(currentId)).setVisible(true);
			}
			break;
		}
	}

	public void deleteAction(String name) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Comfirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			switch (name) {
			case "LoanTypes":
				try {
					LoanTypesDAO loanTypesDAO = new LoanTypesDAO();
					loanTypesDAO.delete(loanTypesDAO.find(currentId));
					JOptionPane.showMessageDialog(null, "Delete success 1 row!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can't delete row!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				refresh(name);
				break;
			}
		}
	}

	public void filter(String name) {
		switch (name) {
		case "LoanTypes":
			JDialogFilterLoanTypes jDialogFilterLoanTypes = new JDialogFilterLoanTypes();
			jDialogFilterLoanTypes.loadComboBox(((CustomTableModel) JPanelLoanType.JTable.getModel()).getColums());
			jDialogFilterLoanTypes.setVisible(true);
			break;
		}
	}

	public void refresh(String name) {
		switch (name) {
		case "LoanTypes":
			JPanelLoanType.loadTable();
			JPanelLoanType.JTable.setRowSorter(null);
			break;
		}
	}

}

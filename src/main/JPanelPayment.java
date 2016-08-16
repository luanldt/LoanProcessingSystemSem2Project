package main;

import dao.PaymentDAO;
import entities.Payment;
import factory.AbstractJPanel;
import model.CustomTableModel;

public class JPanelPayment extends AbstractJPanel {

	private static final long serialVersionUID = 1L;

	public JPanelPayment() {
		super(JPanelPayment.class);
		loadTable();
	}

	@Override
	public void loadTable() {
		CustomTableModel customTableModel = new CustomTableModel();
		String[] columns = { "PaymentID", "contractID", "PaymentType", "PaymentDate", "duePeriod", "PaymentAmount",
				"FineRate", "FineAmount", "staffID", "paid" };
		customTableModel.addColumn(columns);

		for (Payment payment : new PaymentDAO().findAll()) {
			customTableModel.addRow(new Object[] { payment.getPaymentId(), payment.getContracts().getContractId(),
					payment.getPaymentType(), payment.getPaymentDate(), payment.getDuePeriod(),
					payment.getPaymentAmount(), payment.getFineRate(), payment.getFineAmount(),
					payment.getStaffs().getStaffId(), payment.isPaid() });
		}
		setModel(customTableModel);
	}

}

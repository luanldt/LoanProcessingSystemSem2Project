package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import dao.ContractsDAO;
import dao.CustomersDAO;
import dao.DepartmentDAO;
import dao.LoanTypesDAO;
import dao.PaymentDAO;
import dao.StaffsDAO;
import entities.Staffs;
import model.JTabbedPaneCloseButton;
import model.MakeIcon;
import model.MenuTreeCellRenderer;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class JFrameMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel ContentPane;
	private JTree JTreeMenu;
	private JTabbedPaneCloseButton JTabbedPaneMain;
	private JPanel JPanelTop;
	private JButton JButtonLogout;
	private JMenuBar menuBar;
	private JMenu mnAbout;
	private JSplitPane JSplitPane;
	private JPanel JPanelTreeMenu;
	private JPanel JPanelMain;
	private JLabel JLabelHello;
	private JPanel JPanelBottom;

	public String usernameLogin;
	// Noi de cac class jpanel add nao
	JPanelLoanType jPanelLoanType;
	JPanelStaff jPanelStaff;
	JPanelDepartment jPanelDepartment;
	JPanelContract jPanelContract;
	JPanelCustomer jPanelCustomers;
	JPanelPayment jPanelPayment;
	private JTextField JTextFieldSearch;
	private JButton JButtonSearch;
	private JButton JButtonAdd;
	public static JButton JButtonUpdate;
	public static JButton JButtonDelete;
	private JButton JButtonRefresh;
	private JPanel panel_3;
	private String name = "";
	public static int currentId = -1;
	private static final int UPDATE = 1;
	private static final int ADD = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					JFrameMain frame = new JFrameMain();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameMain() {
		setName("LoanProcessingSystem");
		setIconImage(MakeIcon.getImage("tittleMain", 64, 64));
		setTitle("Loan Processing System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		ContentPane = new JPanel();
		ContentPane.setName("ContentPane");
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		this.ContentPane.setLayout(new BorderLayout(0, 0));

		this.JPanelMain = new JPanel();
		this.JPanelMain.setName("JPanelMain");
		this.ContentPane.add(this.JPanelMain);

		JPanelTop = new JPanel();
		JPanelTop.setName("JPanelTop");

		JButtonLogout = new JButton("Logout");
		JButtonLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_JButtonLogout_actionPerformed(e);
			}
		});
		JButtonLogout.setIcon(MakeIcon.getIcon("logout", MakeIcon.ICON_24));
		JButtonLogout.setMargin(new Insets(0, 0, 0, 0));
		JButtonLogout.setForeground(new Color(255, 0, 0));
		JButtonLogout.setFont(new Font("Algerian", Font.BOLD, 20));
		JButtonLogout.setName("JButtonLogout");

		JLabelHello = new JLabel("Hello Luận");
		JLabelHello.setForeground(Color.BLUE);
		JLabelHello.setFont(new Font("Lucida Grande", Font.PLAIN, 17));

		JTextFieldSearch = new JTextField();
		JTextFieldSearch.setColumns(10);

		JButtonSearch = new JButton("Search");
		JButtonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchData(JTextFieldSearch.getText());
			}
		});

		JSplitPane = new JSplitPane();
		JSplitPane.setName("JSplitPane");
		JSplitPane.setOneTouchExpandable(true);
		JSplitPane.setResizeWeight(0.2);

		JTabbedPaneMain = new JTabbedPaneCloseButton();
		JTabbedPaneMain.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				do_JTabbedPaneMain_stateChanged(arg0);
			}
		});
		JTabbedPaneMain.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				// Mỗi lần thêm 1 bảng thì thêm 1 if vào chỗ này để kiểm tra//
				if (e.getChild() instanceof JPanelLoanType) {
					jPanelLoanType = null;
				}
				/*
				 * Neu nhu da nhan vao roi ma tat thi thi set set cho gia tri ve
				 * null tuc la nhu chua tung load gia tri len
				 * 
				 */
				if (e.getChild() instanceof JPanelStaff) {
					jPanelStaff = null;
				}

				if (e.getChild() instanceof JPanelDepartment) {
					jPanelDepartment = null;
				}

				if (e.getChild() instanceof JPanelContract) {
					jPanelContract = null;
				}

				if (e.getChild() instanceof JPanelCustomer) {
					jPanelCustomers = null;
				}

				if (e.getChild() instanceof JPanelPayment) {
					jPanelPayment = null;
				}

				JFrameMain.currentId = -1;

				name = "";

				if (JTabbedPaneMain.getTabCount() > 0) {
					JTabbedPaneMain.setSelectedIndex(JTabbedPaneMain.getTabCount() - 1);
					name = JTabbedPaneMain.getTitleAt(JTabbedPaneMain.getTabCount() - 1);
				}

				checkEnableButton();

			}
		});
		JSplitPane.setRightComponent(JTabbedPaneMain);
		JTabbedPaneMain.setBorder(new LineBorder(new Color(30, 144, 255)));
		JTabbedPaneMain.setBackground(SystemColor.menu);

		JPanelTreeMenu = new JPanel();
		JPanelTreeMenu.setBackground(SystemColor.menu);
		JPanelTreeMenu.setName("JPanelTreeMenu");
		JSplitPane.setLeftComponent(JPanelTreeMenu);
		JPanelTreeMenu.setLayout(new BorderLayout(0, 0));

		JTreeMenu = new JTree();
		JTreeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JTreeMenu.getPathForLocation(e.getX(), e.getY()) != null) {
					callTable((DefaultMutableTreeNode) JTreeMenu.getPathForLocation(e.getX(), e.getY())
							.getLastPathComponent());
				}
			}
		});
		JPanelTreeMenu.add(JTreeMenu);
		JTreeMenu.setBackground(SystemColor.menu);
		JTreeMenu.setBorder(new LineBorder(new Color(30, 144, 255)));
		JTreeMenu.setCellRenderer(new MenuTreeCellRenderer());
		JTreeMenu.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Menu") {
			private static final long serialVersionUID = 1L;
		}));
		JTreeMenu.setFont(new Font("Dialog", Font.PLAIN, 19));
		GroupLayout gl_JPanelMain = new GroupLayout(this.JPanelMain);
		gl_JPanelMain.setHorizontalGroup(gl_JPanelMain.createParallelGroup(Alignment.LEADING)
				.addComponent(this.JPanelTop, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
				.addComponent(this.JSplitPane, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE));
		gl_JPanelMain
				.setVerticalGroup(gl_JPanelMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_JPanelMain.createSequentialGroup()
								.addComponent(this.JPanelTop, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.JSplitPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
								.addGap(19)));

		panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButtonAdd = new JButton("Add");
		panel_3.add(JButtonAdd);
		JButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processAddUpdate(JFrameMain.ADD);
			}
		});

		JButtonUpdate = new JButton("Update");
		panel_3.add(JButtonUpdate);
		JButtonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processAddUpdate(JFrameMain.UPDATE);
			}
		});
		JButtonUpdate.setEnabled(false);

		JButtonDelete = new JButton("Delete");
		panel_3.add(JButtonDelete);
		JButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processDelete();
			}
		});
		JButtonDelete.setEnabled(false);

		JButtonRefresh = new JButton("Refresh");
		panel_3.add(JButtonRefresh);
		JButtonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processRefresh();
			}
		});
		GroupLayout gl_JPanelTop = new GroupLayout(JPanelTop);
		gl_JPanelTop
				.setHorizontalGroup(
						gl_JPanelTop.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_JPanelTop.createSequentialGroup().addGap(177)
										.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 432,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(JTextFieldSearch, GroupLayout.PREFERRED_SIZE, 320,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2)
										.addComponent(JButtonSearch).addGap(8).addComponent(JLabelHello,
												GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(JButtonLogout)));
		gl_JPanelTop.setVerticalGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING).addGroup(gl_JPanelTop
				.createSequentialGroup()
				.addGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_JPanelTop
						.createParallelGroup(Alignment.LEADING)
						.addComponent(JLabelHello, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(JButtonLogout, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_JPanelTop.createSequentialGroup().addContainerGap().addComponent(JButtonSearch))
						.addGroup(gl_JPanelTop.createSequentialGroup().addGap(1).addComponent(JTextFieldSearch,
								GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
						.addGroup(gl_JPanelTop.createSequentialGroup().addGap(1).addComponent(panel_3,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		JPanelTop.setLayout(gl_JPanelTop);
		this.JPanelMain.setLayout(gl_JPanelMain);

		this.JPanelBottom = new JPanel();
		this.JPanelBottom.setName("JPanelBottom");
		this.ContentPane.add(this.JPanelBottom, BorderLayout.SOUTH);

		pack();

		// Gọi khởi tạo jtree menu
		this.loadMenu();
		// Gọi khởi tạo các jpanel
		this.loadJPanel();

		checkEnableButton();

		setExtendedState(MAXIMIZED_BOTH);
	}

	public void loadMenu() {
		/* Load Item lên JTree */
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu");

		/* Danh mục */
		DefaultMutableTreeNode list = new DefaultMutableTreeNode("List");
		DefaultMutableTreeNode categoryLoanTypes = new DefaultMutableTreeNode("Loan Types");
		DefaultMutableTreeNode categoryCustomers = new DefaultMutableTreeNode("Customers");
		DefaultMutableTreeNode categoryDepartments = new DefaultMutableTreeNode("Departments");
		DefaultMutableTreeNode categoryStaffs = new DefaultMutableTreeNode("Staffs");

		list.add(categoryLoanTypes);
		list.add(categoryCustomers);
		list.add(categoryDepartments);
		list.add(categoryStaffs);

		/* Chứng từ */
		DefaultMutableTreeNode voucher = new DefaultMutableTreeNode("Voucher");
		DefaultMutableTreeNode voucherContracts = new DefaultMutableTreeNode("Contracts");
		DefaultMutableTreeNode voucherPayments = new DefaultMutableTreeNode("Payments");

		voucher.add(voucherContracts);
		voucher.add(voucherPayments);

		/* Báo cáo */
		DefaultMutableTreeNode report = new DefaultMutableTreeNode("Report");

		root.add(list);
		root.add(voucher);
		root.add(report);
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);

		JTreeMenu.setModel(defaultTreeModel);
	}

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * // / CODE GỌI CÁC JPANEL VÀ ADD VÀO MAIN RA NÊN KHÔNG CHẠM TỚI TRỪ KHI
	 * THÊM / MỚI VÀO
	 * /////////////////////////////////////////////////////////////////////////
	 * // /
	 */
	public void callTable(DefaultMutableTreeNode node) {
		name = node.getUserObject().toString();
		switch (name) {
		case "Loan Types":
			if (jPanelLoanType == null) {
				jPanelLoanType = new JPanelLoanType();
			}
			this.addAndSelect(node, jPanelLoanType);
			checkEnableButton();
			break;
		case "Staffs":
			if (jPanelStaff == null) {
				jPanelStaff = new JPanelStaff();
			}
			this.addAndSelect(node, jPanelStaff);
			checkEnableButton();
			break;
		case "Departments":
			if (jPanelDepartment == null) {
				jPanelDepartment = new JPanelDepartment();
			}
			this.addAndSelect(node, jPanelDepartment);
			checkEnableButton();
			break;
		case "Contracts":
			if (jPanelContract == null) {
				jPanelContract = new JPanelContract();
			}
			this.addAndSelect(node, jPanelContract);
			checkEnableButton();
			break;
		case "Customers":
			if (jPanelCustomers == null) {
				jPanelCustomers = new JPanelCustomer();
			}
			this.addAndSelect(node, jPanelCustomers);
			checkEnableButton();
			break;
		case "Payments":
			if (jPanelPayment == null) {
				jPanelPayment = new JPanelPayment();
			}
			this.addAndSelect(node, jPanelPayment);
			checkEnableButton();
			break;
		}

	}
	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * // / // END GỌI CÁC JPANEL ADD VÀO MAIN
	 * /////////////////////////////////////////////////////////////////////////
	 * //
	 */

	// TẠO MỚI TẤT CẢ CÁC JPANEL SẴN NHƯNG KHÔNG ADD VÀO
	private void loadJPanel() {
		jPanelLoanType = new JPanelLoanType();
		jPanelStaff = new JPanelStaff();
		jPanelDepartment = new JPanelDepartment();
		jPanelContract = new JPanelContract();
		jPanelCustomers = new JPanelCustomer();
		jPanelPayment = new JPanelPayment();
	}

	// ADD VÀ HIGHTLIGHT(SELECTED TAB)
	private void addAndSelect(DefaultMutableTreeNode node, JPanel jpanel) {
		if (JTabbedPaneMain.indexOfTab(node.getUserObject().toString()) != -1) {
			JTabbedPaneMain.setSelectedComponent(jpanel);
		} else {
			JTabbedPaneMain.addTab(node.getUserObject().toString(), jpanel);
			JTabbedPaneMain.setSelectedComponent(jpanel);
		}
	}

	/* THẬT RA LÀ NGẠI CODE TRONG CLASS DÀI QUÁ MỚI TÁCH RA THÔI (:v) */
	/* CODE ADD UPDATE VÀ CÁC LỰA CHỌN ADD UPDATE */
	private void processAddUpdate(int order) {
		// Nhờ vào tên của các jpanel nên có thể dễ dàng gọi các jdialog add ra
		// dễ
		// dàng.
		switch (name) {
		case "Loan Types":
			if (order == JFrameMain.ADD) {
				new JDialogAddLoanTypes().setVisible(true);
			} else {
				new JDialogAddLoanTypes().isUpdate(new LoanTypesDAO().find(currentId)).setVisible(true);
			}
			break;
		case "Staffs":
			if (order == JFrameMain.ADD) {
				new JDialogModifyStaff().setVisible(true);
			} else {
				new JDialogModifyStaff().isUpdate(new StaffsDAO().find(currentId)).setVisible(true);
			}
			break;
		case "Departments":
			if (order == JFrameMain.ADD) {
				new JDialogModifyDepartments().setVisible(true);
			} else {
				new JDialogModifyDepartments().isUpdate(new DepartmentDAO().find(currentId)).setVisible(true);
			}
			break;
		case "Contracts":
			if (order == JFrameMain.ADD) {
				new JDialogModifyContract().setVisible(true);
			} else {
				new JDialogModifyContract().isUpdate(new ContractsDAO().find(currentId)).setVisible(true);
			}
			break;
		}
		processRefresh();
	}
	/* END CODE ADD UPDATE */

	/* START CODE DELETE */
	private void processDelete() {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Comfirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			switch (name) {
			case "Loan Types":
				try {
					new LoanTypesDAO().delete(new LoanTypesDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete loan types success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete loan types error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Staffs":
				try {
					new StaffsDAO().delete(new StaffsDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete staff success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete staff error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Departments":
				try {
					new DepartmentDAO().delete(new DepartmentDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete departments success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete departments error!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Customers":
				try {
					new CustomersDAO().delete(new CustomersDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete customer success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete customer error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "Contracts":
				try {
					new ContractsDAO().delete(new ContractsDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete contract success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete contract error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Payments":
				try {
					new PaymentDAO().delete(new PaymentDAO().find(currentId));
					JOptionPane.showMessageDialog(null, "Delete customer success!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Delete customer error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			processRefresh();
		}

	}
	/* END CODE DELETE */

	/* START CODE SET ENABLE BUTTON ACTION WITH DATA */
	private void checkEnableButton() {
		if (name.isEmpty()) {
			JButtonAdd.setEnabled(false);
			JButtonRefresh.setEnabled(false);
			JTextFieldSearch.setEnabled(false);
			JButtonSearch.setEnabled(false);
		} else {
			JTextFieldSearch.setEnabled(true);
			JButtonSearch.setEnabled(true);
			JButtonAdd.setEnabled(true);
			JButtonRefresh.setEnabled(true);
		}
		if (currentId == -1) {
			JButtonDelete.setEnabled(false);
			JButtonUpdate.setEnabled(false);
		} else {
			JButtonDelete.setEnabled(true);
			JButtonUpdate.setEnabled(true);
		}
	}
	/* END CODE SET ENABLE BUTTON ACTION WITH DATA */

	/* START CODE SEARCH DATA */
	private void searchData(String sValue) {
		switch (name) {
		case "Loan Types":
			jPanelLoanType.filter(sValue);
			break;
		case "Staffs":
			jPanelStaff.filter(sValue);
			break;
		case "Departments":
			jPanelDepartment.filter(sValue);
			break;
		case "Contracts":
			jPanelContract.filter(sValue);
			break;
		case "Customers":
			jPanelCustomers.filter(sValue);
			break;
		case "Payments":
			jPanelPayment.filter(sValue);
			break;
		}
	}
	/* END CODE SEARCH DATA */

	/* START CODE REFRESH */
	private void processRefresh() {
		switch (name) {
		case "Loan Types":
			jPanelLoanType.loadTable();
			break;
		case "Staffs":
			jPanelStaff.loadTable();
			break;
		case "Departments":
			jPanelDepartment.loadTable();
			break;
		case "Contracts":
			jPanelContract.loadTable();
			break;
		case "Customers":
			jPanelCustomers.loadTable();
			break;
		case "Payments":
			jPanelPayment.loadTable();
			break;
		}
	}
	/* END CODE REFRESH */

	/* Viết phần quyền vô hàm này, tạo if else hay switch case gì đó */
	public void assignMenu(String username) {
		StaffsDAO staffsDAO = new StaffsDAO();
		Staffs staffs = staffsDAO.findUsername(username);
		JLabelHello.setText("Hello " + staffs.getUsername());
	}

	protected void do_JButtonLogout_actionPerformed(ActionEvent e) {
		JFrameLogin jFrameLogin = new JFrameLogin();
		jFrameLogin.setVisible(true);
		this.dispose();
	}

	protected void do_JTabbedPaneMain_stateChanged(ChangeEvent arg0) {
		int tabSelected = JTabbedPaneMain.getSelectedIndex();
		if (tabSelected != -1) {
			this.name = JTabbedPaneMain.getTitleAt(tabSelected);
		}
		JFrameMain.currentId = -1;
		checkEnableButton();
	}
}

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import moderator.JPanelApproveDeleting;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
	JPanelApproveDeleting jPanelApproveDeleting;
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
	private JLabel currentUser;
	private JLabel lblUser;
	private JMenu mnPermission;
	private JMenuItem mntmUser;
	private JMenu mnModeratorTools;
	private JButton btnAssign;
	private JMenu mnApproveDeleting;
	private JMenuItem mntmLoanTypes;
	private JMenuItem mntmCustomers;
	private JMenuItem mntmDepartments;
	private JMenuItem mntmStaffs;
	private JSeparator separator;
	private JMenuItem mntmContracts;
	private JMenuItem mntmPayments;
	private JButton btnApprove;
	private JButton btnCancel;
	public static String mntmAprrove;

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
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		this.mnModeratorTools = new JMenu("Moderator Tools");
		this.mnModeratorTools.setName("mnModeratorTools");
		this.menuBar.add(this.mnModeratorTools);

		this.mnApproveDeleting = new JMenu("Approve Deleting");
		this.mnApproveDeleting.setName("mnApproveDeleting");
		this.mnModeratorTools.add(this.mnApproveDeleting);

		this.mntmLoanTypes = new JMenuItem("Loan types");
		this.mntmLoanTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmApproveDeleting_actionPerformed(arg0);
			}
		});
		this.mntmLoanTypes.setName("mntmLoanTypes");
		this.mnApproveDeleting.add(this.mntmLoanTypes);

		this.mntmCustomers = new JMenuItem("Customers");
		this.mntmCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmApproveDeleting_actionPerformed(e);
			}
		});
		this.mntmCustomers.setName("mntmCustomers");
		this.mnApproveDeleting.add(this.mntmCustomers);

		this.mntmDepartments = new JMenuItem("Departments");
		this.mntmDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmApproveDeleting_actionPerformed(e);
			}
		});
		this.mntmDepartments.setName("mntmDepartments");
		this.mnApproveDeleting.add(this.mntmDepartments);

		this.mntmStaffs = new JMenuItem("Staffs");
		this.mntmStaffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmApproveDeleting_actionPerformed(e);
			}
		});
		this.mntmStaffs.setName("mntmStaffs");
		this.mnApproveDeleting.add(this.mntmStaffs);

		this.separator = new JSeparator();
		this.separator.setName("separator");
		this.mnApproveDeleting.add(this.separator);

		this.mntmContracts = new JMenuItem("Contracts");
		this.mntmContracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmApproveDeleting_actionPerformed(e);
			}
		});
		this.mntmContracts.setName("mntmContracts");
		this.mnApproveDeleting.add(this.mntmContracts);

		this.mntmPayments = new JMenuItem("Payments");
		this.mntmPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmApproveDeleting_actionPerformed(e);
			}
		});
		this.mntmPayments.setName("mntmPayments");
		this.mnApproveDeleting.add(this.mntmPayments);

		this.mnPermission = new JMenu("Permission");
		this.mnPermission.setName("mnPermission");
		this.menuBar.add(this.mnPermission);

		this.mntmUser = new JMenuItem("User");
		this.mntmUser.setName("mntmUser");
		this.mnPermission.add(this.mntmUser);
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
		JTextFieldSearch.addKeyListener(new JTextFieldSearchKeyListener());
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
			@Override
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

				if (e.getChild() instanceof JPanelApproveDeleting) {
					jPanelApproveDeleting = null;
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
										.addGap(2).addComponent(JButtonSearch).addGap(105)
										.addComponent(JLabelHello, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(JButtonLogout,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(60)));
		gl_JPanelTop.setVerticalGroup(gl_JPanelTop.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(gl_JPanelTop.createSequentialGroup().addGroup(gl_JPanelTop
						.createParallelGroup(Alignment.TRAILING).addGroup(gl_JPanelTop
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_JPanelTop.createParallelGroup(Alignment.BASELINE)
										.addComponent(JLabelHello, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(JButtonLogout, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_JPanelTop.createSequentialGroup().addContainerGap()
										.addComponent(JButtonSearch)))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				.addGroup(gl_JPanelTop.createSequentialGroup().addComponent(JTextFieldSearch,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(20)));
		this.btnAssign = new JButton("Assign");
		this.btnAssign.setName("btnAssign");
		this.panel_3.add(this.btnAssign);

		this.btnApprove = new JButton("Approve");
		this.btnApprove.setName("btnApprove");
		this.panel_3.add(this.btnApprove);

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.setName("btnCancel");
		this.panel_3.add(this.btnCancel);

		this.btnAssign.setVisible(false);
		this.btnApprove.setVisible(false);
		this.btnCancel.setVisible(false);
		JPanelTop.setLayout(gl_JPanelTop);
		this.JPanelMain.setLayout(gl_JPanelMain);

		this.JPanelBottom = new JPanel();
		this.JPanelBottom.setName("JPanelBottom");
		this.ContentPane.add(this.JPanelBottom, BorderLayout.SOUTH);

		currentUser = new JLabel("Currently logged as:");
		currentUser.setHorizontalAlignment(SwingConstants.CENTER);
		currentUser.setName("currentUser");
		JPanelBottom.add(currentUser);

		lblUser = new JLabel("");
		lblUser.setName("lblUser");
		JPanelBottom.add(lblUser);

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
		case "Customers":
			if (order == JFrameMain.ADD) {
				new JDialogModifyCustomer().setVisible(true);
			} else {
				new JDialogModifyCustomer().isUpdate(new CustomersDAO().find(currentId)).setVisible(true);
			}
			break;
		case "Payments":
			if (order == JFrameMain.ADD) {
				new JDialogModifyPayment().setVisible(true);
			} else {
				new JDialogModifyPayment().isUpdate(new PaymentDAO().find(currentId)).setVisible(true);
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
		JLabelHello.setText("Hello " + staffs.getStaffName());
		lblUser.setText(staffs.getUsername());
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

	protected void do_mntmApproveDeleting_actionPerformed(ActionEvent e) {
		String tabName = ((JMenuItem) e.getSource()).getName();
		jPanelApproveDeleting = new JPanelApproveDeleting(tabName);
		JTabbedPaneMain.addTab(tabName, jPanelApproveDeleting);
		JTabbedPaneMain.setSelectedComponent(jPanelApproveDeleting);
	}

	// Search at runtime
	private class JTextFieldSearchKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent arg0) {
			searchData(JTextFieldSearch.getText());
		}
	}
}

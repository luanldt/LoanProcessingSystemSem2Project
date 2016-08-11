package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import dao.StaffsDAO;
import entities.Staffs;
import model.JTabbedPaneCloseButton;
import model.MakeIcon;
import model.MenuTreeCellRenderer;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel JLabelHello;
	private JMenuBar menuBar;
	private JMenu mnAbout;
	private JSplitPane JSplitPane;
	private JPanel JPanelTreeMenu;
	private JPanel JPanelMain;
	private JPanel JPanelBottom;

	public String usernameLogin;
	// Noi de cac class jpanel add nao
	JPanelLoanType jPanelLoanType;
	JPanelStaff jPanelStaff;
	JPanelDepartment jPanelDepartment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
			public void actionPerformed(ActionEvent e) {
				do_JButtonLogout_actionPerformed(e);
			}
		});
		JButtonLogout.setIcon(MakeIcon.getIcon("logout", MakeIcon.ICON_24));
		JButtonLogout.setMargin(new Insets(0, 0, 0, 0));
		JButtonLogout.setForeground(new Color(255, 0, 0));
		JButtonLogout.setFont(new Font("Algerian", Font.BOLD, 20));
		JButtonLogout.setName("JButtonLogout");

		JLabelHello = new JLabel("Hello, this is Loan Processing System");
		JLabelHello.setForeground(new Color(139, 69, 19));
		JLabelHello.setFont(new Font("Algerian", Font.BOLD, 20));
		JLabelHello.setName("JLabelHello");
		GroupLayout gl_JPanelTop = new GroupLayout(JPanelTop);
		gl_JPanelTop.setHorizontalGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_JPanelTop.createSequentialGroup()
						.addComponent(JLabelHello, GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE).addGap(18)
						.addComponent(JButtonLogout, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)));
		gl_JPanelTop.setVerticalGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelTop.createSequentialGroup()
						.addGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_JPanelTop.createSequentialGroup().addGap(1).addComponent(JButtonLogout,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addComponent(JLabelHello, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		JPanelTop.setLayout(gl_JPanelTop);

		JSplitPane = new JSplitPane();
		JSplitPane.setName("JSplitPane");
		JSplitPane.setOneTouchExpandable(true);
		JSplitPane.setResizeWeight(0.2);

		JTabbedPaneMain = new JTabbedPaneCloseButton();
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
		JPanelTreeMenu.add(JTreeMenu);
		JTreeMenu.setBackground(SystemColor.menu);
		JTreeMenu.setBorder(new LineBorder(new Color(30, 144, 255)));
		JTreeMenu.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (JTreeMenu.isSelectionEmpty()) {
					return;
				} else {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) JTreeMenu.getSelectionPath()
							.getLastPathComponent();
					callTable(node);
				}
			}
		});
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
		this.JPanelMain.setLayout(gl_JPanelMain);

		this.JPanelBottom = new JPanel();
		this.JPanelBottom.setName("JPanelBottom");
		this.ContentPane.add(this.JPanelBottom, BorderLayout.SOUTH);

		pack();

		loadMenu();
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
	 * Gọi gọi các table theo các giá trị truyền vào khi người dùng click vào
	 * các node trên jtree
	 */
	public void callTable(DefaultMutableTreeNode node) {
		switch (node.getUserObject().toString()) {
		case "Loan Types":
			// START CODE CHUẨN VÀ ĐẦY ĐỦ//
			if (jPanelLoanType == null) {
				jPanelLoanType = new JPanelLoanType();
				JTabbedPaneMain.addTab(node.getUserObject().toString(), jPanelLoanType);
				jPanelLoanType.add(new JPanelActionData(), BorderLayout.SOUTH);
				JTabbedPaneMain.setSelectedComponent(jPanelLoanType);
			} else {
				JTabbedPaneMain.setSelectedComponent(jPanelLoanType);
			}
			break;
		// END CODE CHUẨN VÀ ĐẦY ĐỦ//
		////////////////////////////////////////////////////////////////////
		// KHI MUỐN THÊM 1 JPANEL VÀO THÌ CHỈ CẦN VIẾT GIỐNG NHƯ ĐOẠN
		// CODE CHUẨN KIA NHƯNG THAY LÀ TÊN CỦA JPANEL ĐÓ LÀ XONG
		////////////////////////////////////////////////////////////////////
		case "Staffs":
			// START CODE CHUẨN VÀ ĐẦY ĐỦ//
			if (jPanelStaff == null) {
				jPanelStaff = new JPanelStaff();
				JTabbedPaneMain.addTab(node.getUserObject().toString(), jPanelStaff);
				jPanelStaff.add(new JPanelActionData(), BorderLayout.SOUTH);
				JTabbedPaneMain.setSelectedComponent(jPanelStaff);
			} else {
				JTabbedPaneMain.setSelectedComponent(jPanelStaff);
			}
			break;
		case "Departments":
			if (jPanelDepartment == null) {
				jPanelDepartment = new JPanelDepartment();
				JTabbedPaneMain.addTab(node.getUserObject().toString(), jPanelDepartment);
				jPanelDepartment.add(new JPanelActionData(), BorderLayout.SOUTH);
				JTabbedPaneMain.setSelectedComponent(jPanelDepartment);
			} else {
				JTabbedPaneMain.setSelectedComponent(jPanelDepartment);
			}
		}
	}

	/*Viết phần quyền vô hàm này, tạo if else hay switch case gì đó*/
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
}

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import javafx.scene.control.Tooltip;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.JTabbedPaneCloseButton;
import model.MakeIcon;
import model.MenuTreeCellRenderer;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

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
	private JLabel JLabelName;
	private JMenu mnAbout;
	private JSplitPane splitPane;
	private JPanel panel;
	// Noi de cac class jpanel add nao
	JPanelLoanType jPanelLoanType;
	private JPanel panel_1;
	private JPanel panel_2;

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

		this.panel_1 = new JPanel();
		this.panel_1.setName("panel_1");
		this.ContentPane.add(this.panel_1);

		JPanelTop = new JPanel();
		JPanelTop.setName("JPanelTop");

		JButtonLogout = new JButton("Logout");
		JButtonLogout.setIcon(MakeIcon.getIcon("logout", MakeIcon.ICON_24));
		JButtonLogout.setMargin(new Insets(0, 0, 0, 0));
		JButtonLogout.setForeground(new Color(0, 128, 0));
		JButtonLogout.setFont(new Font("Algerian", Font.BOLD, 20));
		JButtonLogout.setName("JButtonLogout");

		JLabelHello = new JLabel("Hello, this is Loan Processing System");
		JLabelHello.setIcon(new ImageIcon("E:\\JavaSR\\ProjectLoanProcessingSystemSem2\\icon\\hello.png"));
		JLabelHello.setForeground(new Color(139, 69, 19));
		JLabelHello.setFont(new Font("Algerian", Font.BOLD, 20));
		JLabelHello.setName("JLabelHello");

		JLabelName = new JLabel("Hello Luận");
		JLabelName.setForeground(Color.BLUE);
		JLabelName.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		GroupLayout gl_JPanelTop = new GroupLayout(JPanelTop);
		gl_JPanelTop.setHorizontalGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelTop.createSequentialGroup()
						.addComponent(this.JLabelHello, GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE).addGap(169)
						.addComponent(this.JLabelName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.JButtonLogout)));
		gl_JPanelTop.setVerticalGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING).addGroup(gl_JPanelTop
				.createSequentialGroup()
				.addGroup(gl_JPanelTop.createParallelGroup(Alignment.LEADING)
						.addComponent(this.JLabelHello, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_JPanelTop.createSequentialGroup().addGap(1)
								.addGroup(gl_JPanelTop.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.JButtonLogout, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.JLabelName, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_JPanelTop.linkSize(SwingConstants.VERTICAL, new Component[] { this.JButtonLogout, this.JLabelName });
		JPanelTop.setLayout(gl_JPanelTop);

		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.2);

		JTabbedPaneMain = new JTabbedPaneCloseButton();
		JTabbedPaneMain.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				// Mỗi lần thêm 1 bảng thì thêm 1 if vào chỗ này để kiểm tra//
				if (e.getChild() instanceof JPanelLoanType) {
					jPanelLoanType = null;
				}
				// Nó sẽ thực hiện việc hủy bỏ jpanel mình tạo ra khi nhấn close
			}
		});
		splitPane.setRightComponent(JTabbedPaneMain);
		JTabbedPaneMain.setBorder(new LineBorder(new Color(30, 144, 255)));
		JTabbedPaneMain.setBackground(UIManager.getColor("InternalFrame.background"));

		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JTreeMenu = new JTree();
		panel.add(JTreeMenu);
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
		GroupLayout gl_panel_1 = new GroupLayout(this.panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(this.JPanelTop, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
				.addComponent(this.splitPane, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(this.JPanelTop, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.splitPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
					.addGap(19))
		);
		this.panel_1.setLayout(gl_panel_1);

		this.panel_2 = new JPanel();
		this.panel_2.setName("panel_2");
		this.ContentPane.add(this.panel_2, BorderLayout.SOUTH);

		

		pack();

		loadMenu();
		System.out.println(this.getSize());
	}

	public void loadMenu() {
		/* Load Item lên JTree */
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu");

		/* Danh mục */
		DefaultMutableTreeNode list = new DefaultMutableTreeNode("List");
		DefaultMutableTreeNode categoryLoanType = new DefaultMutableTreeNode("Loan Type");
		DefaultMutableTreeNode categoryCustomer = new DefaultMutableTreeNode("Customer");
		DefaultMutableTreeNode categoryDepartment = new DefaultMutableTreeNode("Department");
		DefaultMutableTreeNode categoryStaff = new DefaultMutableTreeNode("Staff");

		list.add(categoryLoanType);
		list.add(categoryCustomer);
		list.add(categoryDepartment);
		list.add(categoryStaff);

		/* Chứng từ */
		DefaultMutableTreeNode voucher = new DefaultMutableTreeNode("Voucher");
		DefaultMutableTreeNode voucherContract = new DefaultMutableTreeNode("Contract");
		DefaultMutableTreeNode voucherPayment = new DefaultMutableTreeNode("Payment");

		voucher.add(voucherContract);
		voucher.add(voucherPayment);

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
		case "Loan Type":
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
		}
	}
}

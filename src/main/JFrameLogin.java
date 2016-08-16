package main;

import java.awt.EventQueue;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import dao.StaffsDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*Hàm login, có kèm sẵn phân quyền, chỉ cần viết thêm nội dung phân quyền vào hàm assignMenu
 * Sau khi nhập password, có thể ấn Enter để đăng nhập thay vì click chuột vào button Login
 * 
 * @author SarahRuby*/
@SuppressWarnings("serial")
public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JPanel JPanel;
	private JLabel JLabelUsername;
	private JLabel JLabelPassword;
	private JTextField JTextFieldUsername;
	private JPasswordField JPasswordFieldPassword;
	private JButton JButtonLogin;
	private JButton JButtonCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					 UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
					JFrameLogin jFrameLogin = new JFrameLogin();
					jFrameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\JavaSR\\Icon\\loginsystem.png"));
		setResizable(false);
		setTitle("Login System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel = new JPanel();
		JPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		JPanel.setBounds(29, 32, 322, 164);
		JPanel.setName("JPanel");
		contentPane.add(JPanel);

		JLabelUsername = new JLabel("Username:");
		JLabelUsername.setBounds(48, 33, 87, 16);
		JLabelUsername.setForeground(new Color(0, 128, 0));
		JLabelUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLabelUsername.setName("JLabelUsername");

		JLabelPassword = new JLabel("Password:");
		JLabelPassword.setBounds(48, 67, 87, 16);
		JLabelPassword.setForeground(new Color(0, 128, 0));
		JLabelPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLabelPassword.setName("JLabelPassword");

		JTextFieldUsername = new JTextField();
		JTextFieldUsername.setBounds(139, 27, 148, 22);
		JTextFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		JTextFieldUsername.setForeground(new Color(128, 0, 0));
		JTextFieldUsername.setName("JTextFieldUsername");
		JTextFieldUsername.setColumns(10);

		JPasswordFieldPassword = new JPasswordField();
		JPasswordFieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				do_JPasswordFieldPassword_keyReleased(arg0);
			}
		});
		JPasswordFieldPassword.setBounds(139, 67, 148, 22);
		JPasswordFieldPassword.setForeground(new Color(128, 0, 0));
		JPasswordFieldPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		JPasswordFieldPassword.setName("JPasswordFieldPassword");

		JButtonLogin = new JButton("Login");
		JButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_JButtonLogin_actionPerformed(e);
			}
		});
		JButtonLogin.setBounds(48, 107, 108, 25);
		JPanel.setLayout(null);
		JButtonLogin.setForeground(new Color(0, 0, 128));
		JButtonLogin.setFont(new Font("Algerian", Font.BOLD, 13));
		JButtonLogin.setName("JButtonLogin");
		JPanel.add(JButtonLogin);
		JPanel.add(JLabelUsername);
		JPanel.add(JTextFieldUsername);
		JPanel.add(JLabelPassword);
		JPanel.add(JPasswordFieldPassword);

		JButtonCancel = new JButton("Cancel");
		JButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_JButtonCancel_actionPerformed(e);
			}
		});
		JButtonCancel.setForeground(new Color(0, 0, 128));
		JButtonCancel.setFont(new Font("Algerian", Font.BOLD, 13));
		JButtonCancel.setBounds(179, 107, 108, 25);
		JButtonCancel.setName("JButtonCancel");
		JPanel.add(JButtonCancel);
	}

	protected void do_JButtonLogin_actionPerformed(ActionEvent e) {
		try {
			StaffsDAO staffsDAO = new StaffsDAO();
			String username = JTextFieldUsername.getText();
			String password = new String(JPasswordFieldPassword.getPassword());
			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(null, "Account or password can't be blank!");
			} else if (staffsDAO.login(username, password) == null) {
				JOptionPane.showMessageDialog(null, "Account or password incorrect!");
			} else {
				JFrameMain jFrameMain = new JFrameMain();
				jFrameMain.usernameLogin = username;
				jFrameMain.assignMenu(username);
				jFrameMain.setVisible(true);
				setVisible(false);
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}

	protected void do_JPasswordFieldPassword_keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			do_JButtonLogin_actionPerformed(null);
		}
	}

	protected void do_JButtonCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}

}

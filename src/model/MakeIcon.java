package model;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Sử dụng class này để tạo ra các icon và các image nằm trong thư mục icon của
 * project Có 2 method và cách dùng thỉ cần truyền vào chuỗi tên của image nằm
 * trong mục icon và kích thước muốn xài
 * 
 * @author LuậnĐẹpTrai
 *
 */
public class MakeIcon {
	
	public static final int ICON_16 = 16;
	public static final int ICON_20 = 20;
	public static final int ICON_24 = 24;
	public static final int ICON_32 = 32;
	public static final int ICON_48 = 48;
	public static final int ICON_64 = 64;
	public static final int ICON_128 = 128;
	public static final int ICON_256 = 256;
	
	public static Icon getIcon(String iconName, int size) {
		try {
			return scaleImage(new ImageIcon(ImageIO.read(new File("icon/" + iconName + ".png"))), size, size);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Icon getIcon(String iconName, int width, int height) {
		try {
			return scaleImage(new ImageIcon(ImageIO.read(new File("icon/" + iconName + ".png"))), width, height);
		} catch (Exception e) {
			return null;
		}
	}

	public static Image getImage(String imageName, int size) {
		try {
			return ((ImageIcon) MakeIcon.getIcon(imageName, size)).getImage();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Image getImage(String imageName, int width, int height) {
		try {
			return ((ImageIcon) MakeIcon.getIcon(imageName, width, height)).getImage();
		} catch (Exception e) {
			return null;
		}
	}

	private static ImageIcon scaleImage(ImageIcon icon, int width, int height) {
		return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
}

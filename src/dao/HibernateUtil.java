package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import helper.EncryptPasswordWithPBKDF2WithHmacSHA1;

public class HibernateUtil {
	static String hbusr = "hibernate.connection.username";
	static String hbpwd = "hibernate.connection.password";
	static String hburl = "hibernate.connection.url";
	static String hbcatalog = "hibernate.default_catalog";

	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {
		try {
			Properties properties = new Properties();
			File filepath = new File("config/hibernate.properties");
			properties.load(new FileInputStream(filepath));
			String password = EncryptPasswordWithPBKDF2WithHmacSHA1.decPassword((properties.getProperty(hbpwd)));
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
					.setProperty(hbpwd, password).setProperty(hbusr, properties.getProperty(hbusr))
					.setProperty(hburl, properties.getProperty(hburl))
					.setProperty(hbcatalog, properties.getProperty(hbcatalog));
			return configuration.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void setSessionFactory(String username, String password, String url, String database) {
		String encPassword = String.valueOf(EncryptPasswordWithPBKDF2WithHmacSHA1.encPassword(password));
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:sqlserver://" + url);
		properties.setProperty("hibernate.connection.username", username);
		properties.setProperty("hibernate.connection.password", encPassword);
		properties.setProperty("hibernate.default_catalog", database);
		try {
			File filepath = new File("config/hibernate.properties");
			if (!filepath.exists()) {
				filepath.getParentFile().mkdir();
			}
			properties.store(new FileOutputStream(filepath, false),
					"Hibernate settings for application, do not modify");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

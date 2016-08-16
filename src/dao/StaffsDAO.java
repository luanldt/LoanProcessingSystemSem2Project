package dao;

import org.hibernate.Query;

import entities.Staffs;
import helper.EncryptPasswordWithPBKDF2WithHmacSHA1;

public class StaffsDAO extends AbstractModel<Staffs> {
	public StaffsDAO() {
		super(Staffs.class);
	}

	public String login(String username, String password) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			Query query = sessionFactory.getCurrentSession()
					.createQuery("select st from Staffs st where st.username = :username")
					.setString("username", username);
			if (EncryptPasswordWithPBKDF2WithHmacSHA1.validatePassword(password, ((Staffs) query.uniqueResult()).getPassword())) {
				return "dungcmnr";
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Staffs findUsername(String username) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
				sessionFactory.getCurrentSession().getTransaction().begin();
			}
			return (Staffs) sessionFactory.getCurrentSession()
					.createQuery("select st from Staffs st where st.username = :username")
					.setString("username", username).uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
}

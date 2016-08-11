package dao;

import org.hibernate.Query;
import entities.Staffs;

public class StaffsDAO extends AbstractModel<Staffs> {
	public StaffsDAO() {
		super(Staffs.class);
	}

	public Staffs login(String username, String password) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			Query query = sessionFactory.getCurrentSession()
					.createQuery("select st from Staffs st where st.username = :username and st.password = :password");
			query.setString("username", username);
			query.setString("password", password);
			return (Staffs) query.uniqueResult();
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

package dao;

import org.hibernate.Query;

import entities.Staffs;
import helper.EncryptPasswordWithPBKDF2WithHmacSHA1;

public class StaffsDAO extends AbstractModel<Staffs> {
	public StaffsDAO() {
		super(Staffs.class);
	}

	public String getStaffNameByID(int staffId) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (String) sessionFactory.getCurrentSession()
					.createQuery("select s.staffName from Staffs s where s.staffId = :staffId")
					.setInteger("staffId", staffId).uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean login(String username, String password) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			Query query = sessionFactory.getCurrentSession()
					.createQuery("select st from Staffs st where st.username = :username");
			query.setString("username", username);
			Staffs staff = (Staffs) query.uniqueResult();
			return EncryptPasswordWithPBKDF2WithHmacSHA1.validatePassword(password, staff.getPassword());
		} catch (Exception e) {
			return false;
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

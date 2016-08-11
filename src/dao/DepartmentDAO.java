package dao;

import entities.Department;

public class DepartmentDAO extends AbstractModel<Department> {
	public DepartmentDAO() {
		super(Department.class);
	}

	public Department findByName(String name) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (Department) sessionFactory.getCurrentSession()
					.createQuery("select d from Department d where d.departmentName = :name").setString("name", name)
					.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
}

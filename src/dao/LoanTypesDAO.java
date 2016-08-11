package dao;

import entities.LoanTypes;

public class LoanTypesDAO extends AbstractModel<LoanTypes> {
	public LoanTypesDAO() {
		super(LoanTypes.class);
	}
	
	public LoanTypes findByName(String name) {
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (LoanTypes) sessionFactory.getCurrentSession().createQuery("select l from LoanTypes l where l.loanTypeName = :name").setString("name", name).uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
}

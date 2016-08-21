package dao;

import entities.LoanTypes;

public class LoanTypesDAO extends AbstractModel<LoanTypes> {
	public LoanTypesDAO() {
		super(LoanTypes.class);
	}
	
	public String getLoanTypeNameByID(int loanTypeId){
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (String) sessionFactory.getCurrentSession()
					.createQuery("select l.loanTypeName from LoanTypes l where l.loanTypeId = :loanTypeId")
					.setInteger("loanTypeId", loanTypeId)
					.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public long getLoanRateByID(int loanTypeId){
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive())
				sessionFactory.getCurrentSession().getTransaction().begin();
			return (long) sessionFactory.getCurrentSession()
					.createQuery("select l.loanRate from LoanTypes l where l.loanTypeId = :loanTypeId")
					.setInteger("loanTypeId", loanTypeId)
					.uniqueResult();
		} catch (Exception e) {
			return 0;
		}
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

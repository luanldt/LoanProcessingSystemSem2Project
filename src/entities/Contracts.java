package entities;
// Generated Aug 11, 2016 1:42:11 PM by Hibernate Tools 5.1.0.Alpha1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contracts generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Contracts", catalog = "vzsoft_chicken_team")
public class Contracts implements java.io.Serializable {

	private int contractId;
	private Customers customers;
	private LoanTypes loanTypes;
	private Staffs staffs;
	private Date contractDate;
	private int maturityPeriod;
	private int paidTimes;
	private int loanTerm;
	private BigDecimal initialAmount;
	private BigDecimal remainAmount;
	private Date dueDate;
	private Date initiateDate;
	private BigDecimal loanMax;
	private String createLog;
	private String notes;
	private boolean isArchive;
	private Set<Payment> payments = new HashSet<Payment>(0);

	public Contracts() {
	}

	public Contracts(int contractId, Customers customers, LoanTypes loanTypes, Staffs staffs, Date contractDate,
			int maturityPeriod, int paidTimes, int loanTerm, BigDecimal initialAmount, BigDecimal remainAmount,
			Date dueDate, Date initiateDate, String createLog, String notes, boolean isArchive) {
		this.contractId = contractId;
		this.customers = customers;
		this.loanTypes = loanTypes;
		this.staffs = staffs;
		this.contractDate = contractDate;
		this.maturityPeriod = maturityPeriod;
		this.paidTimes = paidTimes;
		this.loanTerm = loanTerm;
		this.initialAmount = initialAmount;
		this.remainAmount = remainAmount;
		this.dueDate = dueDate;
		this.initiateDate = initiateDate;
		this.createLog = createLog;
		this.notes = notes;
		this.isArchive = isArchive;
	}

	public Contracts(int contractId, Customers customers, LoanTypes loanTypes, Staffs staffs, Date contractDate,
			int maturityPeriod, int paidTimes, int loanTerm, BigDecimal initialAmount, BigDecimal remainAmount,
			Date dueDate, Date initiateDate, BigDecimal loanMax, String createLog, String notes, boolean isArchive,
			Set<Payment> payments) {
		this.contractId = contractId;
		this.customers = customers;
		this.loanTypes = loanTypes;
		this.staffs = staffs;
		this.contractDate = contractDate;
		this.maturityPeriod = maturityPeriod;
		this.paidTimes = paidTimes;
		this.loanTerm = loanTerm;
		this.initialAmount = initialAmount;
		this.remainAmount = remainAmount;
		this.dueDate = dueDate;
		this.initiateDate = initiateDate;
		this.loanMax = loanMax;
		this.createLog = createLog;
		this.notes = notes;
		this.isArchive = isArchive;
		this.payments = payments;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contractId", unique = true, nullable = false)
	public int getContractId() {
		return this.contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", nullable = false)
	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loanTypeId", nullable = false)
	public LoanTypes getLoanTypes() {
		return this.loanTypes;
	}

	public void setLoanTypes(LoanTypes loanTypes) {
		this.loanTypes = loanTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffId", nullable = false)
	public Staffs getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contractDate", nullable = false, length = 23)
	public Date getContractDate() {
		return this.contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	@Column(name = "maturityPeriod", nullable = false)
	public int getMaturityPeriod() {
		return this.maturityPeriod;
	}

	public void setMaturityPeriod(int maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	@Column(name = "paidTimes", nullable = false)
	public int getPaidTimes() {
		return this.paidTimes;
	}

	public void setPaidTimes(int paidTimes) {
		this.paidTimes = paidTimes;
	}

	@Column(name = "loanTerm", nullable = false)
	public int getLoanTerm() {
		return this.loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	@Column(name = "InitialAmount", nullable = false, scale = 4)
	public BigDecimal getInitialAmount() {
		return this.initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	@Column(name = "remainAmount", nullable = false, scale = 4)
	public BigDecimal getRemainAmount() {
		return this.remainAmount;
	}

	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getInitiateDate() {
		return this.initiateDate;
	}

	public void setInitiateDate(Date initiateDate) {
		this.initiateDate = initiateDate;
	}

	@Column(name = "loanMax", scale = 4)
	public BigDecimal getLoanMax() {
		return this.loanMax;
	}

	public void setLoanMax(BigDecimal loanMax) {
		this.loanMax = loanMax;
	}

	@Column(name = "createLog", nullable = false, length = 30)
	public String getCreateLog() {
		return this.createLog;
	}

	public void setCreateLog(String createLog) {
		this.createLog = createLog;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(name = "isArchive", nullable = false)
	public boolean isIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contracts")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}

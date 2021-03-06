package entities;
// Generated Aug 11, 2016 1:42:11 PM by Hibernate Tools 5.1.0.Alpha1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Customers generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Customers", catalog = "vzsoft_chicken_team")
public class Customers implements java.io.Serializable {

	private int customerId;
	private String customerName;
	private String address;
	private String phoneNumber;
	private String email;
	private String accountNumber;
	private String identityCardNo;
	private String notes;
	private BigDecimal salary;
	private String createLog;
	private boolean isArchive;
	private Set<Contracts> contractses = new HashSet<Contracts>(0);

	public Customers() {
	}

	public Customers(int customerId, String customerName, String address, String phoneNumber, String email,
			String accountNumber, String identityCardNo, String notes, String createLog, boolean isArchive) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accountNumber = accountNumber;
		this.identityCardNo = identityCardNo;
		this.notes = notes;
		this.createLog = createLog;
		this.isArchive = isArchive;
	}

	public Customers(int customerId, String customerName, String address, String phoneNumber, String email,
			String accountNumber, String identityCardNo, String notes, BigDecimal salary, String createLog,
			boolean isArchive, Set<Contracts> contractses) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accountNumber = accountNumber;
		this.identityCardNo = identityCardNo;
		this.notes = notes;
		this.salary = salary;
		this.createLog = createLog;
		this.isArchive = isArchive;
		this.contractses = contractses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId", unique = true, nullable = false)
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name = "customerName", nullable = false, length = 100)
	@NotEmpty(message = "Customer name can not be empty!")
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@NotEmpty(message = "Address can not be empty!")
	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotEmpty(message = "Phone number can not be empty!")
	@Column(name = "phoneNumber", nullable = false, length = 20)
	@Pattern(regexp = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$", message = "Phone number invalid")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotEmpty(message = "Email can not be empty!")
	@Email(message = "Email is invalid.")
	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Account number can not be empty!")
	@Column(name = "accountNumber", nullable = false, length = 20)
	@Pattern(regexp = "\\d{12,15}", message = "Account number not invalid")
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@NotEmpty(message = "Identity card no can not be empty!")
	@Column(name = "identityCardNo", nullable = false, length = 20)
	@Pattern(regexp = "\\d{9,15}", message = "Iddentity Card number not invalid.")
	public String getIdentityCardNo() {
		return this.identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	@NotEmpty(message = "Notes can not be empty!")
	@Column(name = "notes", nullable = false, length = 1073741823)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Range(min=0, message = "Salary can't smaller than 0")
	@Column(name = "salary", scale = 4)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Column(name = "createLog", nullable = false, length = 30)
	public String getCreateLog() {
		return this.createLog;
	}

	public void setCreateLog(String createLog) {
		this.createLog = createLog;
	}

	@Column(name = "isArchive", nullable = false)
	public boolean isIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	public Set<Contracts> getContractses() {
		return this.contractses;
	}

	public void setContractses(Set<Contracts> contractses) {
		this.contractses = contractses;
	}

}

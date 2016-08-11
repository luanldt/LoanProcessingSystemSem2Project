package entities;
// Generated Aug 11, 2016 1:42:11 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Staffs generated by hbm2java
 */
@Entity
@Table(name = "Staffs", catalog = "vzsoft_chicken_team")
public class Staffs implements java.io.Serializable {

	private int staffId;
	private Department department;
	private String staffName;
	private String username;
	private boolean isAdmin;
	private int role;
	private String createLog;
	private boolean isArchive;
	private String password;
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Contracts> contractses = new HashSet<Contracts>(0);

	public Staffs() {
	}

	public Staffs(int staffId, Department department, String staffName, boolean isAdmin, int role, String createLog,
			boolean isArchive, String password) {
		this.staffId = staffId;
		this.department = department;
		this.staffName = staffName;
		this.isAdmin = isAdmin;
		this.role = role;
		this.createLog = createLog;
		this.isArchive = isArchive;
		this.password = password;
	}

	public Staffs(int staffId, Department department, String staffName, boolean isAdmin, int role, String createLog,
			boolean isArchive, String password, Set<Payment> payments, Set<Contracts> contractses) {
		this.staffId = staffId;
		this.department = department;
		this.staffName = staffName;
		this.isAdmin = isAdmin;
		this.role = role;
		this.createLog = createLog;
		this.isArchive = isArchive;
		this.password = password;
		this.payments = payments;
		this.contractses = contractses;
	}

	@Id

	@Column(name = "staffId", unique = true, nullable = false)
	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentID", nullable = false)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "staffName", nullable = false, length = 100)
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "isAdmin", nullable = false)
	public boolean isIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "role", nullable = false)
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
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

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Contracts> getContractses() {
		return this.contractses;
	}

	public void setContractses(Set<Contracts> contractses) {
		this.contractses = contractses;
	}

}

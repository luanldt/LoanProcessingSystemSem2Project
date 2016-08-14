package entities;
// Generated Aug 11, 2016 1:42:11 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogInfor generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LogInfor", catalog = "vzsoft_chicken_team")
public class LogInfor implements java.io.Serializable {

	private int ident;
	private String createLog;
	private String modifyLog;

	public LogInfor() {
	}

	public LogInfor(int ident, String createLog, String modifyLog) {
		this.ident = ident;
		this.createLog = createLog;
		this.modifyLog = modifyLog;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Ident", unique = true, nullable = false)
	public int getIdent() {
		return this.ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	@Column(name = "CreateLog", nullable = false, length = 30)
	public String getCreateLog() {
		return this.createLog;
	}

	public void setCreateLog(String createLog) {
		this.createLog = createLog;
	}

	@Column(name = "ModifyLog", nullable = false, length = 30)
	public String getModifyLog() {
		return this.modifyLog;
	}

	public void setModifyLog(String modifyLog) {
		this.modifyLog = modifyLog;
	}

}
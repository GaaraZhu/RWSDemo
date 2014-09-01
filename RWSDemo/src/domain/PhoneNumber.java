package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "phoneNumber")
public class PhoneNumber implements Serializable {
	private static final long serialVersionUID = 1117556057700117015L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String areaCode;

	@Column(name = "NUM")
	private String number;

	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employee owner;

	@Version
	private long version;

	private PhoneNumber() {
	}

	public PhoneNumber(String type, String areaCode, String number) {
		this();
		setType(type);
		setAreaCode(areaCode);
		setNumber(number);
	}

	public long getVersion() {
		return version;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getId() {
		return this.id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String pNumber) {
		this.number = pNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Employee getOwner() {
		return this.owner;
	}

	public void setOwner(Employee employee) {
		this.owner = employee;
		/**
		 * this.owner = employee; if (employee == null) { this.id = 0; } else {
		 * this.id = employee.getId(); }
		 */
	}

	@Override
	public String toString() {
		return "PhoneNumber(" + getId() + ": " + getAreaCode() + "-"
				+ getNumber() + ")";
	}
}

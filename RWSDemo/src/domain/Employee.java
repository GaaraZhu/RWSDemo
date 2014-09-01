package domain;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 299555566217698138L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	@OneToMany(mappedBy = "owner", cascade = ALL, fetch = FetchType.LAZY)
	private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

	@OneToOne(cascade = ALL, fetch = FetchType.EAGER)
	private Address address;

	@Version
	private long version;

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int empId) {
		this.id = empId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public long getVersion() {
		return version;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumberList) {
		this.phoneNumbers = phoneNumberList;
	}

	public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().add(phoneNumber);
		phoneNumber.setOwner(this);
		return phoneNumber;
	}

	public PhoneNumber addPhoneNumber(String type, String areaCode,
			String number) {
		PhoneNumber phoneNumber = new PhoneNumber(type, areaCode, number);
		return addPhoneNumber(phoneNumber);
	}

	public PhoneNumber removePhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().remove(phoneNumber);
		phoneNumber.setOwner(null);
		return phoneNumber;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public String toString() {
		return "Employee(" + getId() + ": " + getLastName() + ", "
				+ getFirstName() + ")";
	}

}

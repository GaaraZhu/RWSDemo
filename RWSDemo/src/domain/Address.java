package domain;

import java.io.Serializable; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "address")
public class Address implements Serializable {
	private static final long serialVersionUID = 6715878993874634402L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String city;

	private String country;
	private String province;
	private String postalCode;
	private String street;
	
	@Version
	private long version;

	public Address() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public int getId() {
		return this.id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String pCode) {
		this.postalCode = pCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address(" + getId() + ": " + getCity() + ", " + getCountry()
				+ ")";
	}
}

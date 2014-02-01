package ch.kup.flomi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address extends TrackedEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;

	private String salutation;
	private String title;

	private String lastName;
	private String firstName;
	private String address;
	private String address2;
	private String zip;
	private String city;
	private String country;
	private String email;
	private String phone;

	private String mobilePhone;
	private String fax;
	private String birthday;
	private String carNumber;

	public String getAddress() {
		return address;
	}

	public String getAddress2() {
		return address2;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public String getSalutation() {
		return salutation;
	}

	public String getTitle() {
		return title;
	}

	public String getZip() {
		return zip;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}

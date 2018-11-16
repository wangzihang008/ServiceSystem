package com.fdmgroup.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vendor_id")
	private int id;
	private int verification;
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	private String status;
	@Column(name="vendor_first_name")
	private String vendorFirstName;
	@Column(name="vendor_middle_name")
	private String vendorMiddleName;
	@Column(name="vendor_last_name")
	private String vendorLastName;
	@Column(name="store_name")
	private String storeName;
	@Column(name="store_url", unique = true, nullable = false)
	private String storeUrl;
	@OneToOne(mappedBy = "vendor", fetch = FetchType.EAGER)
    private Customer customer;
	
	public Vendor() {
		super();
	}
	
	public Vendor(int verification, String address, String phone_number, String status, String vendor_first_name,
			String vendor_middle_name, String vendor_last_name, String store_name, String store_url) {
		super();
		this.verification = verification;
		this.address = address;
		this.phoneNumber = phone_number;
		this.status = status;
		this.vendorFirstName = vendor_first_name;
		this.vendorMiddleName = vendor_middle_name;
		this.vendorLastName = vendor_last_name;
		this.storeName = store_name;
		this.storeUrl = store_url;
	}

	public int getId() {
		return id;
	}
	public void setId(int vendor_id) {
		this.id = vendor_id;
	}
	public int getVerification() {
		return verification;
	}
	public void setVerification(int verification) {
		this.verification = verification;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phone_number) {
		this.phoneNumber = phone_number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendorFirstName() {
		return vendorFirstName;
	}
	public void setVendorFirstName(String vendor_first_name) {
		this.vendorFirstName = vendor_first_name;
	}
	public String getVendorMiddleName() {
		return vendorMiddleName;
	}
	public void setVendorMiddleName(String vendor_middle_name) {
		this.vendorMiddleName = vendor_middle_name;
	}
	public String getVendorLastName() {
		return vendorLastName;
	}
	public void setVendorLastName(String vendor_last_name) {
		this.vendorLastName = vendor_last_name;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String store_name) {
		this.storeName = store_name;
	}
	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String store_url) {
		this.storeUrl = store_url;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result + ((storeUrl == null) ? 0 : storeUrl.hashCode());
		result = prime * result + ((vendorFirstName == null) ? 0 : vendorFirstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((vendorLastName == null) ? 0 : vendorLastName.hashCode());
		result = prime * result + ((vendorMiddleName == null) ? 0 : vendorMiddleName.hashCode());
		result = prime * result + verification;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (storeUrl == null) {
			if (other.storeUrl != null)
				return false;
		} else if (!storeUrl.equals(other.storeUrl))
			return false;
		if (vendorFirstName == null) {
			if (other.vendorFirstName != null)
				return false;
		} else if (!vendorFirstName.equals(other.vendorFirstName))
			return false;
		if (id != other.id)
			return false;
		if (vendorLastName == null) {
			if (other.vendorLastName != null)
				return false;
		} else if (!vendorLastName.equals(other.vendorLastName))
			return false;
		if (vendorMiddleName == null) {
			if (other.vendorMiddleName != null)
				return false;
		} else if (!vendorMiddleName.equals(other.vendorMiddleName))
			return false;
		if (verification != other.verification)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", verification=" + verification + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", status=" + status + ", vendorFirstName=" + vendorFirstName
				+ ", vendorMiddleName=" + vendorMiddleName + ", vendorLastName=" + vendorLastName
				+ ", storeName=" + storeName + ", storeUrl=" + storeUrl + ", customer=" + customer + "]";
	}
	
	public boolean isInfoMissing() {
		return "".equals(address) || "".equals(phoneNumber) || "".equals(storeName) || "".equals(vendorFirstName) ||
				"".equals(vendorLastName);
	}
}

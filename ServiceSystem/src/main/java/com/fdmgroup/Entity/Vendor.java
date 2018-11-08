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
	@TableGenerator(name = "vendor_gen", 
		table = "vendor",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, 
		generator = "vendor_gen")
	private int vendor_id;
	private int verification;
	private String address;
	private String phone_number;
	private String status;
	private String vendor_first_name;
	private String vendor_middle_name;
	private String vendor_last_name;
	private String store_name;
	@Column(unique = true, nullable = false)
	private String store_url;
	@OneToOne(mappedBy = "vendor", fetch = FetchType.EAGER)
    private Customer customer;
	
	public Vendor() {
		super();
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendor_first_name() {
		return vendor_first_name;
	}
	public void setVendor_first_name(String vendor_first_name) {
		this.vendor_first_name = vendor_first_name;
	}
	public String getVendor_middle_name() {
		return vendor_middle_name;
	}
	public void setVendor_middle_name(String vendor_middle_name) {
		this.vendor_middle_name = vendor_middle_name;
	}
	public String getVendor_last_name() {
		return vendor_last_name;
	}
	public void setVendor_last_name(String vendor_last_name) {
		this.vendor_last_name = vendor_last_name;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_url() {
		return store_url;
	}
	public void setStore_url(String store_url) {
		this.store_url = store_url;
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
		result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((store_name == null) ? 0 : store_name.hashCode());
		result = prime * result + ((store_url == null) ? 0 : store_url.hashCode());
		result = prime * result + ((vendor_first_name == null) ? 0 : vendor_first_name.hashCode());
		result = prime * result + vendor_id;
		result = prime * result + ((vendor_last_name == null) ? 0 : vendor_last_name.hashCode());
		result = prime * result + ((vendor_middle_name == null) ? 0 : vendor_middle_name.hashCode());
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
		if (phone_number == null) {
			if (other.phone_number != null)
				return false;
		} else if (!phone_number.equals(other.phone_number))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (store_name == null) {
			if (other.store_name != null)
				return false;
		} else if (!store_name.equals(other.store_name))
			return false;
		if (store_url == null) {
			if (other.store_url != null)
				return false;
		} else if (!store_url.equals(other.store_url))
			return false;
		if (vendor_first_name == null) {
			if (other.vendor_first_name != null)
				return false;
		} else if (!vendor_first_name.equals(other.vendor_first_name))
			return false;
		if (vendor_id != other.vendor_id)
			return false;
		if (vendor_last_name == null) {
			if (other.vendor_last_name != null)
				return false;
		} else if (!vendor_last_name.equals(other.vendor_last_name))
			return false;
		if (vendor_middle_name == null) {
			if (other.vendor_middle_name != null)
				return false;
		} else if (!vendor_middle_name.equals(other.vendor_middle_name))
			return false;
		if (verification != other.verification)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", verification=" + verification + ", address=" + address
				+ ", phone_number=" + phone_number + ", status=" + status + ", vendor_first_name=" + vendor_first_name
				+ ", vendor_middle_name=" + vendor_middle_name + ", vendor_last_name=" + vendor_last_name
				+ ", store_name=" + store_name + ", store_url=" + store_url + ", customer=" + customer + "]";
	}
	
	
}

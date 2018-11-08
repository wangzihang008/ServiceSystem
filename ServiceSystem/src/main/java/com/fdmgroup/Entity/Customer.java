package com.fdmgroup.Entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "service_website_customer")
public class Customer {
	@Id
	@TableGenerator(name = "customer_gen", 
		table = "customer",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, 
		generator = "customer_gen")
	private long customer_id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	private String status;
	private Calendar create_date_time;
	private Calendar last_log_date_time;
	private Calendar last_updated_time;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_favourite_vendor", 
    	joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"), 
    	inverseJoinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id"))
	private List<Vendor> favouriteVendors;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	public Customer() {
		super();
	}
	
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Calendar getCreate_date_time() {
		return create_date_time;
	}
	public void setCreate_date_time(Calendar create_date_time) {
		this.create_date_time = create_date_time;
	}
	public Calendar getLast_log_date_time() {
		return last_log_date_time;
	}
	public void setLast_log_date_time(Calendar last_log_date_time) {
		this.last_log_date_time = last_log_date_time;
	}
	public Calendar getLast_updated_time() {
		return last_updated_time;
	}
	public void setLast_updated_time(Calendar last_updated_time) {
		this.last_updated_time = last_updated_time;
	}
	public List<Vendor> getFavouriteVendors() {
		return favouriteVendors;
	}
	public void setFavouriteVendors(List<Vendor> favouriteVendors) {
		this.favouriteVendors = favouriteVendors;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create_date_time == null) ? 0 : create_date_time.hashCode());
		result = prime * result + (int) (customer_id ^ (customer_id >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((favouriteVendors == null) ? 0 : favouriteVendors.hashCode());
		result = prime * result + ((last_log_date_time == null) ? 0 : last_log_date_time.hashCode());
		result = prime * result + ((last_updated_time == null) ? 0 : last_updated_time.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
		Customer other = (Customer) obj;
		if (create_date_time == null) {
			if (other.create_date_time != null)
				return false;
		} else if (!create_date_time.equals(other.create_date_time))
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favouriteVendors == null) {
			if (other.favouriteVendors != null)
				return false;
		} else if (!favouriteVendors.equals(other.favouriteVendors))
			return false;
		if (last_log_date_time == null) {
			if (other.last_log_date_time != null)
				return false;
		} else if (!last_log_date_time.equals(other.last_log_date_time))
			return false;
		if (last_updated_time == null) {
			if (other.last_updated_time != null)
				return false;
		} else if (!last_updated_time.equals(other.last_updated_time))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", username=" + username + ", password=" + password + ", email="
				+ email + ", status=" + status + ", create_date_time=" + create_date_time + ", last_log_date_time="
				+ last_log_date_time + ", last_updated_time=" + last_updated_time + "]";
	}
	
	
	
	
}

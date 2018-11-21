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
import javax.persistence.JoinColumn;

@Entity
@Table(name = "service_website_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private long id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	private String status;
	@Column(name="create_date_time")
	private Calendar createDateTime;
	@Column(name="last_log_date_time")
	private Calendar lastLogDateTime;
	@Column(name="last_updated_time")
	private Calendar lastUpdatedTime;
	
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
	
	public Customer(String username, String password, String email, String status, Calendar create_date_time,
			Calendar last_log_date_time, Calendar last_updated_time) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
		this.createDateTime = create_date_time;
		this.lastLogDateTime = last_log_date_time;
		this.lastUpdatedTime = last_updated_time;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Calendar getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Calendar create_date_time) {
		this.createDateTime = create_date_time;
	}
	public Calendar getLastLogDateTime() {
		return lastLogDateTime;
	}
	public void setLastLogDateTime(Calendar last_log_date_time) {
		this.lastLogDateTime = last_log_date_time;
	}
	public Calendar getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Calendar last_updated_time) {
		this.lastUpdatedTime = last_updated_time;
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
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((favouriteVendors == null) ? 0 : favouriteVendors.hashCode());
		result = prime * result + ((lastLogDateTime == null) ? 0 : lastLogDateTime.hashCode());
		result = prime * result + ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
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
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (id != other.id)
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
		if (lastLogDateTime == null) {
			if (other.lastLogDateTime != null)
				return false;
		} else if (!lastLogDateTime.equals(other.lastLogDateTime))
			return false;
		if (lastUpdatedTime == null) {
			if (other.lastUpdatedTime != null)
				return false;
		} else if (!lastUpdatedTime.equals(other.lastUpdatedTime))
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
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", email="
				+ email + ", status=" + status + ", createDateTime=" + createDateTime + ", lastLogDateTime="
				+ lastLogDateTime + ", lastUpdatedTime=" + lastUpdatedTime + "]";
	}
	

}

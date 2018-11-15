package com.fdmgroup.Entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private long id;
	@Column(name="appointment_date_time")
	private Calendar appointmentDateTime;
	@Column(name="last_updated_date_time")
	private Calendar lastUpdatedDateTime;
	private String status;
	@ManyToOne
    @JoinColumn(name = "service_id")
	private Service service;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public long getId() {
		return id;
	}
	public void setId(long order_id) {
		this.id = order_id;
	}
	public Calendar getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(Calendar appointment_date_time) {
		this.appointmentDateTime = appointment_date_time;
	}
	public Calendar getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}
	public void setLastUpdatedDateTime(Calendar last_updated_date_time) {
		this.lastUpdatedDateTime = last_updated_date_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Order() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentDateTime == null) ? 0 : appointmentDateTime.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((lastUpdatedDateTime == null) ? 0 : lastUpdatedDateTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (appointmentDateTime == null) {
			if (other.appointmentDateTime != null)
				return false;
		} else if (!appointmentDateTime.equals(other.appointmentDateTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (lastUpdatedDateTime == null) {
			if (other.lastUpdatedDateTime != null)
				return false;
		} else if (!lastUpdatedDateTime.equals(other.lastUpdatedDateTime))
			return false;
		if (id != other.id)
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", appointmentDateTime=" + appointmentDateTime
				+ ", lastUpdatedDateTime=" + lastUpdatedDateTime + ", status=" + status + ", service=" + service
				+ ", customer=" + customer + "]";
	}
	
}

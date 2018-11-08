package com.fdmgroup.Entity;

import java.util.Calendar;

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
	@TableGenerator(name = "order_gen", 
		table = "order",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, 
		generator = "order_gen")
	private long order_id;
	private Calendar appointment_date_time;
	private Calendar last_updated_date_time;
	private String status;
	@ManyToOne
    @JoinColumn(name = "service_id")
	private Service service;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public Calendar getAppointment_date_time() {
		return appointment_date_time;
	}
	public void setAppointment_date_time(Calendar appointment_date_time) {
		this.appointment_date_time = appointment_date_time;
	}
	public Calendar getLast_updated_date_time() {
		return last_updated_date_time;
	}
	public void setLast_updated_date_time(Calendar last_updated_date_time) {
		this.last_updated_date_time = last_updated_date_time;
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
		result = prime * result + ((appointment_date_time == null) ? 0 : appointment_date_time.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((last_updated_date_time == null) ? 0 : last_updated_date_time.hashCode());
		result = prime * result + (int) (order_id ^ (order_id >>> 32));
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
		if (appointment_date_time == null) {
			if (other.appointment_date_time != null)
				return false;
		} else if (!appointment_date_time.equals(other.appointment_date_time))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (last_updated_date_time == null) {
			if (other.last_updated_date_time != null)
				return false;
		} else if (!last_updated_date_time.equals(other.last_updated_date_time))
			return false;
		if (order_id != other.order_id)
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
		return "Order [order_id=" + order_id + ", appointment_date_time=" + appointment_date_time
				+ ", last_updated_date_time=" + last_updated_date_time + ", status=" + status + ", service=" + service
				+ ", customer=" + customer + "]";
	}
	
}

package com.mindtree.dto;



public class CustomerDto {
    private String customerName;
    private String email;
    private Long mobileNumber;
	
    
    
	public CustomerDto(String customerName, String email, Long mobileNumber) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
    
}

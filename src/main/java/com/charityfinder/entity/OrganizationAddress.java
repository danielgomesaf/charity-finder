package com.charityfinder.entity;

import org.springframework.util.StringUtils;

public class OrganizationAddress {

	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private Country country;
	
	private String postal;
	
	private String state;
	
	public OrganizationAddress() {}

	public OrganizationAddress(String addressLine1, String addressLine2, String city, String postal, String state) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postal = postal;
		this.state = state;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCompleteAddress() {
		String completeAddress = "";
		
		if (!StringUtils.isEmpty(addressLine1)) {
			completeAddress += addressLine1 + " ";
		}
		
		if (!StringUtils.isEmpty(addressLine2)) {
			completeAddress += addressLine2 + " ";
		}
		
		if (!StringUtils.isEmpty(city)) {
			completeAddress += city + ", ";
		}
		
		if (!StringUtils.isEmpty(postal)) {
			completeAddress += postal + ", ";
		}
		
		if (!StringUtils.isEmpty(state)) {
			completeAddress += state + ", ";
		}
		
		if (country != null && !StringUtils.isEmpty(country.getName())) {
			completeAddress += country.getName() + ", " + country.getIso3166CountryCode();
		}
		
		return completeAddress;
	}

	@Override
	public String toString() {
		return "OrganizationAddress [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", postal=" + postal + ", state=" + state + "]";
	}
}

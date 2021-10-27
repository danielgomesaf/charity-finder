package com.charityfinder.controller.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.charityfinder.entity.Country;

@XmlRootElement(name = "country")
public class CountryDto {

	private String name;
	
	private String iso3166CountryCode;
	
	public CountryDto() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso3166CountryCode() {
		return iso3166CountryCode;
	}

	public void setIso3166CountryCode(String iso3166CountryCode) {
		this.iso3166CountryCode = iso3166CountryCode;
	}
	
	public Country createEntity() {
		Country country = new Country();
		country.setName(this.getName());
		country.setIso3166CountryCode(this.getIso3166CountryCode());
		
		return country;
	}

	@Override
	public String toString() {
		return "CountryDto [name=" + name + ", iso3166CountryCode=" + iso3166CountryCode + "]";
	}
	
	
}

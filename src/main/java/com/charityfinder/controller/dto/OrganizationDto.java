package com.charityfinder.controller.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.charityfinder.entity.Country;
import com.charityfinder.entity.Organization;
import com.charityfinder.entity.OrganizationAddress;

@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationDto {

	private long id;
	
	private String name;
	
	private String logoUrl;
	
	private String url;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String country;
	
	private String iso3166CountryCode;
	
	private String postal;
	
	private String state;
	
	@XmlElementWrapper(name = "countries")
    @XmlElement(name = "country")
	private List<CountryDto> countries;
	
	public OrganizationDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIso3166CountryCode() {
		return iso3166CountryCode;
	}

	public void setIso3166CountryCode(String iso3166CountryCode) {
		this.iso3166CountryCode = iso3166CountryCode;
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

	public List<CountryDto> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDto> countries) {
		this.countries = countries;
	}
	
	public Organization createEntity() {
		Organization organization = new Organization();
		organization.setId(this.getId());
		organization.setName(this.getName());
		organization.setLogoUrl(this.getLogoUrl());
		organization.setUrl(this.url);
		
		OrganizationAddress organizationAddress = new OrganizationAddress();
		organizationAddress.setAddressLine1(this.getAddressLine1());
		organizationAddress.setAddressLine2(this.getAddressLine2());
		organizationAddress.setCity(this.getCity());
		organizationAddress.setPostal(this.getPostal());
		organizationAddress.setState(this.getState());
		
		Country organizationHomeCountry = new Country();
		organizationHomeCountry.setIso3166CountryCode(this.getIso3166CountryCode());
		organizationHomeCountry.setName(this.getCountry());
		organizationAddress.setCountry(organizationHomeCountry);
		
		organization.setOrganizationAddress(organizationAddress);
		
		return organization;
	}
}

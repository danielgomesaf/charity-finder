package com.charityfinder.entity;

import java.util.List;

public class Organization {

	private long id;

	private String name;

	private List<Country> countries;
	
	private String logoUrl;
	
	private String url;
	
	private OrganizationAddress organizationAddress;
	
	public Organization() {}

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

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	public OrganizationAddress getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(OrganizationAddress organizationAddress) {
		this.organizationAddress = organizationAddress;
	}
}

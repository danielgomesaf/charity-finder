package com.charityfinder.entity;

public class Country {

	private String iso3166CountryCode;
	
	private String name;
	
	public Country() {}

	public Country(String iso3166CountryCode, String name) {
		this.iso3166CountryCode = iso3166CountryCode;
		this.name = name;
	}

	public String getIso3166CountryCode() {
		return iso3166CountryCode;
	}

	public void setIso3166CountryCode(String iso3166CountryCode) {
		this.iso3166CountryCode = iso3166CountryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [iso3166CountryCode=" + iso3166CountryCode + ", name=" + name + "]";
	}
}

package com.charityfinder.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.charityfinder.config.OrganizationListWrapper;
import com.charityfinder.entity.Country;
import com.charityfinder.entity.Organization;
import com.charityfinder.entity.OrganizationAddress;
import com.charityfinder.utils.OrganizationSearchOptionsUtils;

@Repository
public class OrganizationImpl implements OrganizationDAO {
	
	@Autowired
	private OrganizationListWrapper organizationListWrapper;
	
	@Override
	public List<Organization> searchOrganizationsBy(String searchBy, String searchContent) {
		if (searchContent == null || "".equals(searchContent)) {
			return new ArrayList<>();
		}
		
		switch (searchBy) {
			case OrganizationSearchOptionsUtils.SEARCH_BY_NAME:
				return getOrganizationsByName(searchContent);
			
			case OrganizationSearchOptionsUtils.SEARCH_BY_HOME_COUNTRY:
				return getOrganizationsByHomeCountry(searchContent);
			
			case OrganizationSearchOptionsUtils.SEARCH_BY_SERVED_COUNTRIES:
				return getOrganizationsByServedCountries(searchContent);
			
			default:
				return getOrganizationsByName(searchContent);
		}
	}

	private List<Organization> getOrganizationsByName(String searchContent) {
		List<Organization> organizations = organizationListWrapper.getOrganizations();
		
		return organizations
			.stream()
			.filter(org -> org.getName().toLowerCase().contains(searchContent.toLowerCase()))
			.collect(Collectors.toList());
	}
	
	private List<Organization> getOrganizationsByHomeCountry(String searchContent) {
		List<Organization> organizations = organizationListWrapper.getOrganizations();
		
		List<Organization> organizationsByHomeCountry = organizations
			.stream()
			.filter(org -> {
				OrganizationAddress organizationAddress = org.getOrganizationAddress();
				Country homeCountry = organizationAddress.getCountry();
				
				return homeCountry.getName().toLowerCase().contains(searchContent.toLowerCase());
			})
			.collect(Collectors.toList());
		
		return organizationsByHomeCountry;
	}
	
	private List<Organization> getOrganizationsByServedCountries(String searchContent) {
		List<Organization> organizations = organizationListWrapper.getOrganizations();
		
		return organizations
			.stream()
			.filter(org -> anySearchedCountriesFoundInOrganization(searchContent, org))
			.collect(Collectors.toList());
	}

	private boolean anySearchedCountriesFoundInOrganization(String searchContent, Organization org) {
		String[] searchedCountries = searchContent.split(",");
		
		String country = Arrays.asList(searchedCountries)
			.stream()
			.filter(searchedCountry -> organizationHasAnySearchedCountry(org, searchedCountry))
			.findAny()
			.orElse(null);
		
		return country != null;
	}

	private boolean organizationHasAnySearchedCountry(Organization org, String searchedCountry) {
		List<Country> countries = org.getCountries();
		
		Country countryFound = countries
			.stream()
			.filter(c -> c.getName().toLowerCase().equals(searchedCountry.toLowerCase()))
			.findAny()
			.orElse(null);
		
		return countryFound != null;
	}
}

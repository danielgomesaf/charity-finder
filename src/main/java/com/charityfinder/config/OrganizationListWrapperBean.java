package com.charityfinder.config;

import java.util.List;

import com.charityfinder.entity.Organization;

public class OrganizationListWrapperBean implements OrganizationListWrapper {

	private List<Organization> organizations;
	
	public OrganizationListWrapperBean() {
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	
	public List<Organization> getOrganizations() {
		return organizations;
	}
}

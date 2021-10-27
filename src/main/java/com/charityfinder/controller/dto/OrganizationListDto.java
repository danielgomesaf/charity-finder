package com.charityfinder.controller.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organizations")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationListDto {
	
	private boolean hasNext;
	
	private String nextOrgId;
	
	@XmlElement(name = "organization")
	private List<OrganizationDto> organizations;

	public OrganizationListDto() {
	}
	
	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public String getNextOrgId() {
		return nextOrgId;
	}

	public void setNextOrgId(String nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public List<OrganizationDto> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationDto> organizations) {
		this.organizations = organizations;
	}
}
	
package com.charityfinder.service;

import java.util.List;

import com.charityfinder.entity.Organization;

public interface OrganizationService {

	List<Organization> searchOrganizationsBy(String searchBy, String searchContent);
}

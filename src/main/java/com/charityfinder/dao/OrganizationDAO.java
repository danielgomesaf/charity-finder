package com.charityfinder.dao;

import java.util.List;

import com.charityfinder.entity.Organization;

public interface OrganizationDAO {

	List<Organization> searchOrganizationsBy(String searchBy, String searchContent);
}

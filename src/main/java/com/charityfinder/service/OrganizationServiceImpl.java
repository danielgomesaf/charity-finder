package com.charityfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charityfinder.dao.OrganizationDAO;
import com.charityfinder.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Override
	public List<Organization> searchOrganizationsBy(String searchBy, String searchContent) {
		return organizationDAO.searchOrganizationsBy(searchBy, searchContent);
	}
}

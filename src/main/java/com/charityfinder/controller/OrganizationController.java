package com.charityfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charityfinder.entity.Organization;
import com.charityfinder.service.OrganizationService;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@GetMapping("/search-organizations")
	public String searchOrganizations(Model model) {
		return "search-organizations";
	}
	
	@GetMapping("/search")
	public String searchOrganizationsBy(
			@RequestParam(value = "searchBy", required = false) String searchBy,
			@RequestParam(value = "searchContent", required = false) String searchContent,
			Model model) {
		
		List<Organization> organizations = organizationService.searchOrganizationsBy(searchBy, searchContent);
		
		model.addAttribute("organizations", organizations);
		
		model.addAttribute("anyOrganizationsFound", !organizations.isEmpty());
		
		model.addAttribute("searchBy", searchBy);
		
		model.addAttribute("searchContent", searchContent);
		
		return "list-organizations";
	}
}

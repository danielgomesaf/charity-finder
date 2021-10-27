package com.charityfinder.controller.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "download")
public class DownloadLinkOrganizationsXML {

	private String url;

	public DownloadLinkOrganizationsXML() {
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

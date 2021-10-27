package com.charityfinder.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.charityfinder.controller.dto.CountryDto;
import com.charityfinder.controller.dto.DownloadLinkOrganizationsXML;
import com.charityfinder.controller.dto.OrganizationDto;
import com.charityfinder.controller.dto.OrganizationListDto;
import com.charityfinder.entity.Country;
import com.charityfinder.entity.Organization;
import com.charityfinder.utils.GlobalGivingAccessProperties;

@Configuration
@EnableWebMvc
@ComponentScan("com.charityfinder")
public class CharityFinderAppConfig implements WebMvcConfigurer  {

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public OrganizationListWrapper organizationListWrapper() throws Exception {
		OrganizationListWrapperBean organizationListWrapper = new OrganizationListWrapperBean();
		
		String downloadLinkToXMLWithOrganizationsUrl = getDownloadLinkToXMLWithOrganizationsUrl();
		
		File organizationXml = createOrganizationXmlFile(downloadLinkToXMLWithOrganizationsUrl);
		
		OrganizationListDto organizationListDto = extractXmlContentIntoObject(organizationXml);
		
		List<Organization> organizations = getOrganizations(organizationListDto);
		
		organizationListWrapper.setOrganizations(organizations);
		
		return organizationListWrapper;
	}
	
	private String getDownloadLinkToXMLWithOrganizationsUrl() {
		ResponseEntity<DownloadLinkOrganizationsXML> response = createRestTemplate()
				.exchange(createGetDownloadLinkOrganizationsXML(), HttpMethod.GET, 
						new HttpEntity<DownloadLinkOrganizationsXML>(createHeaders()), 
						DownloadLinkOrganizationsXML.class);
		
		DownloadLinkOrganizationsXML downloadLinkOrganizationsXML = response.getBody();
		
		return downloadLinkOrganizationsXML.getUrl();
	}
	
	private RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(createXmlConverter());
		
		return restTemplate;
	}

	private List<HttpMessageConverter<?>> createXmlConverter() {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		
		return converters;
	}
	
	private String createGetDownloadLinkOrganizationsXML() {
		String baseUrl = GlobalGivingAccessProperties.BASE_URL;
		String getDownloadLinkOrganizationsXMLUrl = GlobalGivingAccessProperties.GET_DOWNLOAD_LINK_XML_ORGANIZATIONS;
		String apiKey = GlobalGivingAccessProperties.API_KEY;
		
		return baseUrl + getDownloadLinkOrganizationsXMLUrl + apiKey;
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		
		return headers;
	}

	private File createOrganizationXmlFile(String downloadLinkToXMLWithOrganizationsUrl)
			throws IOException, MalformedURLException, FileNotFoundException {
		ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(downloadLinkToXMLWithOrganizationsUrl).openStream());
		
		File organizationXml = new File("organization.xml");
		organizationXml.createNewFile();
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(organizationXml)) {
			FileChannel fileChannel = fileOutputStream.getChannel();
			
			fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		}
		
		return organizationXml;
	}
	
	private OrganizationListDto extractXmlContentIntoObject(File organizationXml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(OrganizationListDto.class);
		
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		OrganizationListDto organizationListDto = (OrganizationListDto) jaxbUnmarshaller.unmarshal(organizationXml);
		
		return organizationListDto;
	}
	
	private List<Organization> getOrganizations(OrganizationListDto organizationListDto) {
		List<Organization> organizations = organizationListDto.getOrganizations()
				.stream()
				.map(orgDto -> getOrganization(orgDto))
				.collect(Collectors.toList());
		
		return organizations;
	}

	private Organization getOrganization(OrganizationDto orgDto) {
		List<CountryDto> countriesDto = orgDto.getCountries();
		
		List<Country> countries = countriesDto
			.stream()
			.map(countryDto -> countryDto.createEntity())
			.collect(Collectors.toList());
		
		Organization org = orgDto.createEntity();
		org.setCountries(countries);
		
		return org;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}

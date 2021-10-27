<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.charityfinder.utils.OrganizationSearchOptionsUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/cards.css" />
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.0.0/isotope.pkgd.min.js"></script>

<title>Charity Finder</title>
</head>
<body>
	<section class="card_grid">
		<form:form action="search" method="GET">
			<input id = "searchByHidden" type="hidden" value="${searchBy}" />
			<ul class="flex-outer">
				<li>
					<p>Search Organization By:</p>
					<ul class="flex-container longhand">
						<li class="flex-item">
							<input type="radio" id="name" name="searchBy" value="<%=OrganizationSearchOptionsUtils.SEARCH_BY_NAME%>">
							<label for="name">Name</label>
	            		</li>
						<li class="flex-item">
							<input type="radio" id="homeCountry" name="searchBy" value="<%=OrganizationSearchOptionsUtils.SEARCH_BY_HOME_COUNTRY%>">
							<label for="home-country">Home Country</label>
						</li>
						<li class="flex-item">
							<input type="radio" id="served-countries" name="searchBy" value="<%=OrganizationSearchOptionsUtils.SEARCH_BY_SERVED_COUNTRIES%>">
							<label for="served-countries">Served Countries (Separated by comma)</label>
						</li>
					</ul> 
				</li>
				<li>
					<input type="text" id="searchContent" name="searchContent" placeholder="Search here" value="${searchContent}"></li>
				<li>
					<button type="submit">Search</button>
				</li>
			</ul>
		</form:form>
		<c:if test="${anyOrganizationsFound}">
			<div class="card_container">
				<c:forEach var="organization" items="${organizations}">
					<div class="mobile_card_style">
						<h4>${organization.name}</h4>
						<a href="#"></a>
					</div>

					<div class="business_card food-drink retail omaha lincoln">
						<div class="card_image">
							<img src="${organization.logoUrl}" />
						</div>
						
						<div class="line"></div>
						<div class="business_info">
							<h4>${organization.name}</h4>
							<p>
								${organization.organizationAddress.addressLine1}
								${organization.organizationAddress.addressLine2}, 
								${organization.organizationAddress.city}, 
								${organization.organizationAddress.postal}, 
								${organization.organizationAddress.state}, 
								${organization.organizationAddress.country.name},
								${organization.organizationAddress.country.iso3166CountryCode}
							</p>
						</div>
						<a href="${organization.url}" target="_blank"></a>
					</div>
				</c:forEach>
			</div>
		</c:if>
		
		<div class="footer-div">
			<ul class="footer">
				<li><a href="https://www.facebook.com/daniel.gomes.184/" class="fa fa-facebook"></a></li>
				<li><a href="https://www.instagram.com/danielgomesaf/" class="fa fa-instagram"></a></li>
				<li><a href="https://www.linkedin.com/in/danielgomes28/" class="fa fa-linkedin"></a></li>
				<li><a href="https://github.com/danielgomesaf" class="fa fa-github"></a></li>
			</ul>
		</div>
	</section>

	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/cards.js"></script>
</body>
</html>
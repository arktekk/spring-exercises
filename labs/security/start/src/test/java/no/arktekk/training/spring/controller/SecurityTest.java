package no.arktekk.training.spring.controller;

import org.junit.Test;

public class SecurityTest {

	/**
	 * TODO: In web.xml: Configure a filter of type
	 * 'org.springframework.web.filter.DelegatingFilterProxy' and name it
	 * 'springSecurityFilterChain'. Map this filter to all requests.
	 * 
	 * TODO: In applicationContext.xml: Add an authentication provider. Hint:
	 * <code>
	 * 		<security:authentication-manager>	
	 * 		  <security:authentication-provider>
	 * 		    <security:user-service properties="/WEB-INF/users.properties" />
	 * 		  </security:authentication-provider>
	 * 		</security:authentication-manager>
	 * </code>
	 * 
	 * TODO: In users.properties configure two users. One with ROLE_USER and one
	 * with ROLE_AUCTIONEER. Hint: username=password,ROLE_USER
	 * 
	 * TODO: In applicationContext.xml: Add a <security:http> tag containing a
	 * login configuration <security:form-login login-page="/login/login.jsp"/>.
	 * 
	 * TODO: In applicationContext.xml: Configure intercept urls inside
	 * <security:http>. Rules: To access the application (except the login
	 * screen) you need to be logged in. To access the auction details you need
	 * to have the role ROLE_AUCTIONEER.
	 * 
	 * TODO: Run AuctionApp and browse to http://localhost:8080 to explore if
	 * security now works.
	 * 
	 * TODO: Bonus. Add an access denied page. Add a logout success page.
	 */
	@Test
	public void step() {
		// No tests... :)
	}

}
package it.paolomolteni.loginapp.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paolomolteni.loginapp.model.User;

@Component
public class MyUserService {
	
	/**
	 * 
	 */
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * @return
	 */
	public User getUserLogged() {
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
		KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
       
        User user =new User(accessToken.getPreferredUsername(), accessToken.getEmail(), accessToken.getGivenName(), accessToken.getFamilyName(), accessToken.getIssuer());
        
        Set<String> userRealRole = accessToken.getRealmAccess().getRoles();
        Set<String> userResourceRole = accessToken.getResourceAccess("login-app").getRoles();
        
        user.addRoles(userResourceRole);
        user.addRoles(userRealRole);
        
        return user;
	}

}

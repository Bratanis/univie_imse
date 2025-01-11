package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class will combine all of the different UserDetailService Objects 
 * and will allow the users to be granted their respective privileges 
 * just by logging in. 
 * We allow the addition of an arbitrary number of UserDetailsService for the sake
 * of the OCP
 * Currently we will allow an in-memmory admin user and all of the members
 * from the database (as regular users)
 */
@Service
public class CompositeUserDetailsService implements UserDetailsService{
	private final List<UserDetailsService> services;

	public CompositeUserDetailsService(List<UserDetailsService> services) {
        this.services = services; // Spring will inject all available UserDetailsService beans
    }
	
	public List<UserDetailsService> getServices() {
		return services;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDetailsService service : services) {
            try {
                return service.loadUserByUsername(username);
            } catch (UsernameNotFoundException ignored) {
                // Method will just try the next service
            }
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}

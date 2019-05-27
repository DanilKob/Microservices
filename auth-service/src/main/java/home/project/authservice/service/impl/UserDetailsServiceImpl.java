package home.project.authservice.service.impl;

import home.project.authservice.repository.UserDetailsRepository;
import home.project.authservice.service.constant.ServiceBeanNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.CUSTOM_USER_DETAILS_SERVICE)
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.loadUserByUsername(s);
        return userDetails;
    }
}

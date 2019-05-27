package home.project.authservice.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsRepository {
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}

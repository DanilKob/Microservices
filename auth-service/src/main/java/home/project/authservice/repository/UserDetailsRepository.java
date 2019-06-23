package home.project.authservice.repository;

import home.project.authservice.entity.UserDetailsEntity;
import home.project.authservice.exceptions.DuplicateUserNameException;
import home.project.authservice.exceptions.UserRepositoryException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsRepository {
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    UserDetailsEntity addUser(UserDetailsEntity userDetailsEntity) throws UserRepositoryException, DuplicateUserNameException;
}

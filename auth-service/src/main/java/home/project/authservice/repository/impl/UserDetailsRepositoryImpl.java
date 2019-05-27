package home.project.authservice.repository.impl;

import home.project.authservice.entity.UserDetailsEntity;
import home.project.authservice.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    private static final String SELECT_USER_DETAILS_BY_USER_NAME =
            "select * from ( " +
            "   select * from users_scheme.users " +
            "   where " +
            "   username = ? " +
            ") as selected_username\n" +
            "join users_scheme.user_roles\n" +
            "on users_scheme.user_roles.username = selected_username.username";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTHORITY = "authority";

    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User.UserBuilder builder = User.builder();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_DETAILS_BY_USER_NAME);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDetailsEntity userDetailsEntity = resultSetToUserDetailsEntity(resultSet);
            builder
                    .username(s)
                    //.password(passwordEncoder.encode(userDetailsEntity.getPassword()))
                    .password(userDetailsEntity.getPassword())
                    .authorities(authoritiesListToArray(userDetailsEntity.getAuthorities()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("SQL Exception");
        }
        return builder.build();
    }

    private UserDetailsEntity resultSetToUserDetailsEntity(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new UsernameNotFoundException("No user found");
        }
        String username = resultSet.getString(USERNAME);
        String paswoord = resultSet.getString(PASSWORD);
        List<String> authorities = new LinkedList<>();
        authorities.add(resultSet.getString(AUTHORITY));
        while(resultSet.next()) {
            username = resultSet.getString(USERNAME);
            paswoord = resultSet.getString(PASSWORD);
            authorities.add(resultSet.getString(AUTHORITY));
        }
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUsername(username);
        userDetailsEntity.setPassword(paswoord);
        userDetailsEntity.setAuthorities(authorities);
        return userDetailsEntity;
    }

    private String[] authoritiesListToArray(List<String> authorities){
        return authorities.toArray(new String[authorities.size()]);
    }
}

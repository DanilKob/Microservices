package home.project.authservice.repository.impl;

import home.project.authservice.entity.UserDetailsEntity;
import home.project.authservice.exceptions.DuplicateUserNameException;
import home.project.authservice.exceptions.UserRepositoryException;
import home.project.authservice.repository.UserDetailsRepository;
import home.project.crossserviceapi.auth.entity.FullName;
import home.project.crossserviceapi.auth.entity.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.stream.Collectors;

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

    private static final String INSERT_USER =
            "insert into users_scheme.users " +
                    "(username, password, first_name, middle_name, last_name, enabled) " +
                    "values " +
                    "(?, ?, ?,?,?, ?) " +
                    "ON CONFLICT ON CONSTRAINT users_pkey " +
                    "DO NOTHING ";

    private static final String INSERT_AUTHORITIES =
            "insert into users_scheme.user_roles " +
                    "(username, authority) " +
                    "values " +
                    "(?, ?)";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTHORITY = "authority";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String LAST_NAME = "last_name";
    public static final String EXTERNAL_DATA = "external_data";

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
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_DETAILS_BY_USER_NAME);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDetailsEntity userDetailsEntity = resultSetToUserDetailsEntity(resultSet);
            /*
            builder
                    .username(s)
                    //.password(passwordEncoder.encode(userDetailsEntity.getPassword()))
                    .password(userDetailsEntity.getPassword())
                    .authorities(authoritiesListToArray(userDetailsEntity.getAuthorities()))
                    .build();
                    */
            List<String> authoritiesAsStringList = userDetailsEntity.getAuthorities();
            List<SimpleGrantedAuthority> grantedAuthorities =
                    authoritiesAsStringList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            String username = userDetailsEntity.getUsername();
            String password = userDetailsEntity.getPassword();
            FullName fullName = userDetailsEntity.getFullName();
            String externalData = userDetailsEntity.getExternalData();
            return new UserDetailsPrincipal(username, password, grantedAuthorities, externalData, fullName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("SQL Exception");
        }
    }

    @Override
    public UserDetailsEntity addUser(UserDetailsEntity userDetailsEntity) throws UserRepositoryException, DuplicateUserNameException {
        try (Connection connection = dataSource.getConnection()) {
            String username = userDetailsEntity.getUsername();
            String password = userDetailsEntity.getPassword();
            FullName fullName = userDetailsEntity.getFullName();
            boolean enabled = userDetailsEntity.isEnabled();
            List<String> authorities = userDetailsEntity.getAuthorities();
            connection.setAutoCommit(false);
            PreparedStatement insertUserStatement = connection.prepareStatement(INSERT_USER);
            PreparedStatement insertAuthorityStatement = connection.prepareStatement(INSERT_AUTHORITIES);
            insertUserStatement.setString(1, username);
            insertUserStatement.setString(2, password);
            insertUserStatement.setString(3, fullName.getFirstName());
            insertUserStatement.setString(4, fullName.getMiddleName());
            insertUserStatement.setString(5, fullName.getLastName());
            insertUserStatement.setBoolean(6, enabled);
            int updatedRows = insertUserStatement.executeUpdate();
            if (updatedRows == 0) {
                throw new DuplicateUserNameException();
            }

            for (String authority : authorities) {
                insertAuthorityStatement.setString(1, username);
                insertAuthorityStatement.setString(2, authority);
                insertAuthorityStatement.addBatch();
            }
            insertAuthorityStatement.executeBatch();

            connection.commit();

            return userDetailsEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    private UserDetailsEntity resultSetToUserDetailsEntity(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new UsernameNotFoundException("No user found");
        }
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String middleName = resultSet.getString(MIDDLE_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String externalData = resultSet.getString(EXTERNAL_DATA);
        FullName fullName = new FullName(firstName, middleName, lastName);
        List<String> authorities = new LinkedList<>();
        authorities.add(resultSet.getString(AUTHORITY));
        while (resultSet.next()) {
            authorities.add(resultSet.getString(AUTHORITY));
        }
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUsername(username);
        userDetailsEntity.setPassword(password);
        userDetailsEntity.setAuthorities(authorities);
        userDetailsEntity.setFullName(fullName);
        userDetailsEntity.setExternalData(externalData);
        return userDetailsEntity;
    }

    private String[] authoritiesListToArray(List<String> authorities) {
        return authorities.toArray(new String[authorities.size()]);
    }
}

package home.project.crossserviceapi.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.project.crossserviceapi.auth.entity.FullName;
import home.project.crossserviceapi.auth.entity.UserDetailsPrincipal;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthServerPrincipalExtractor implements PrincipalExtractor {

    private static final String USER_KEY = "user";

    private static final String FIRST_NAME = "firstName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String LAST_NAME = "lastName";
    private static final String EXTERNAL_DATA = "external_data";
    public static final String USERNAME = "username";
    public static final String AUTHORITIES = "authorities";
    public static final String PASSWORD = "password";
    public static final String AUTHORITY = "authority";
    public static final String ENABLED = "enabled";
    public static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
    public static final String ACCOUNT_NON_LOCKED = "accountNonLocked";
    public static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";

    @Override
    public Object extractPrincipal(Map<String, Object> map) {

        if (map.containsKey(USER_KEY)) {
            Object response = map.get(USER_KEY);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.convertValue(
                    response, new TypeReference<Map<String, Object>>() {
                    }
            );
            String userName = (String) responseMap.get(USERNAME);
            String password = ""; // password should be protected
            String externalData = (String) responseMap.get(EXTERNAL_DATA);
            String firstName = (String) responseMap.get(FIRST_NAME);
            String middleName = (String) responseMap.get(MIDDLE_NAME);
            String lastName = (String) responseMap.get(LAST_NAME);
            FullName fullName = new FullName(firstName, middleName, lastName);

            boolean enabled = (Boolean) responseMap.get(ENABLED);
            boolean accountNonExpired = (Boolean) responseMap.get(ACCOUNT_NON_EXPIRED);
            boolean accountNonLocked = (Boolean) responseMap.get(ACCOUNT_NON_LOCKED);
            boolean credentialsNonExpired = (Boolean) responseMap.get(CREDENTIALS_NON_EXPIRED);


            Collection<Map<String, Object>> authorities = (Collection<Map<String, Object>>) responseMap.get(AUTHORITIES);
            List<String> authoritiesList = new LinkedList<>();
            for (Map<String, Object> authorityMap : authorities) {
                String authorityName = (String) authorityMap.get(AUTHORITY);
                authoritiesList.add(authorityName);
            }

            List<SimpleGrantedAuthority> grantedAuthorities = authoritiesList
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            UserDetailsPrincipal userDetailsPrincipal = new UserDetailsPrincipal(
                    userName, password,
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                    grantedAuthorities, externalData, fullName
            );
            return userDetailsPrincipal;
        }

        return null;
    }

}

package home.project.authservice.controller;

import home.project.crossserviceapi.auth.entity.UserDetailsPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoController {

    private static final String USER = "user";
    private static final String AUTHORITIES = "authorities";

    @GetMapping(value = {"auth/user"}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        UserDetailsPrincipal principal = (UserDetailsPrincipal)user.getUserAuthentication()
                .getPrincipal();
        userInfo.put(
                USER,
                principal
                );
        userInfo.put(
                AUTHORITIES,
                AuthorityUtils.authorityListToSet(
                        user.getUserAuthentication().getAuthorities()
                )
        );
        return userInfo;
    }
}

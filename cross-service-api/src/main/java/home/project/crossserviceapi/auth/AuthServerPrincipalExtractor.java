package home.project.crossserviceapi.auth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class AuthServerPrincipalExtractor implements PrincipalExtractor {
    private PrincipalKeysFacade principalKeysFacade;

    public AuthServerPrincipalExtractor(PrincipalKeysFacade principalKeysFacade) {
        this.principalKeysFacade = principalKeysFacade;
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        for (String key : principalKeysFacade.getPrincipalKeys()) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }

}

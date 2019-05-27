package home.project.authservice.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
/*
    Implements AuthorizationServerConfigurer to setup our authorization server
 */
public class AuthServerConfigurations
        implements AuthorizationServerConfigurer {


    public static final String CLIENT_ID = "resource-server-api";
    public static final String SECRET = "pass";

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    @Primary
    public TokenStore tokenStore() {
        JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
        return tokenStore;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
    }

    /*
        Register and configure application that can use our authorization server
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.jdbc(dataSource);
        /*
        client.inMemory()
                // client application id
                .withClient(CLIENT_ID)
                // client application password
                .secret(passwordEncoder.encode(SECRET))
                .scopes("webclient","mobileclient")
                // grants that be returned in response
                .authorizedGrantTypes(
                        "password",
                        "authorization_code",
                        "refresh_token"
                );
        */

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) throws Exception {
        // Change default mapping url to custom one
        /*
        endpoint.pathMapping("/oauth/token", "/oauth2/token");
        endpoint.pathMapping("/oauth/authorize", "/oauth2/authorize");
        */
        endpoint.authenticationManager(authenticationManager);
        endpoint.userDetailsService(userDetailsService);
        endpoint.tokenStore(tokenStore());
    }
}

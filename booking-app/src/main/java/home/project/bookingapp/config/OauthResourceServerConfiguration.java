package home.project.bookingapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class OauthResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user")
                .hasAnyAuthority("USER")
                .anyRequest()
                .authenticated()
                .antMatchers("/admin")
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated();
    }
}

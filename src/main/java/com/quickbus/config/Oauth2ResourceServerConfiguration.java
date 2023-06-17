package com.quickbus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true) //secure definition
public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * Manage resource server.
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
//    private static final String SECURED_PATTERN = "/api/**";
    /**
     * Manage endpoints.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/","/showFile/**","/v1/showFile/**","/v1/upload", "/user-register/**","/forget-password/**", "/oauth/authorize**", "/login**", "/error**")
                    .permitAll()
                .antMatchers("/v1/bus").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/v1/travel").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/v1/ticket").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/v1/log").hasAnyAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .permitAll()
        ;



//        http.cors()
//                .and()
//                .csrf()
//                .disable()
//                .antMatcher("/**")
//                .authorizeRequests()
//                    .antMatchers("/",  "/oauth/authorize**", "/ar/**", "/api/ar/**", "/login**", "/error**")
//                    .permitAll()
//                .and()
//                .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
////                .and()
////                    .formLogin()
////                    .permitAll()
//        ;






//        http.requestMatchers()
//                .antMatchers("/api/v1/**")
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/api/v1/**", "/oauth/authorize**", "/login**", "/error**")
//                    .permitAll()
//                .and()
//                .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .formLogin()
//                    .permitAll()
//        ;
    }
}

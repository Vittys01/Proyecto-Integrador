package com.digital.booking.application.config;

import com.digital.booking.application.security.JwtInterceptor;
import com.digital.booking.application.security.SecurityCommonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtInterceptor jwtInterceptor;
    private final SecurityCommonService securityCommonService;

    public SecurityConfig(JwtInterceptor jwtInterceptor, SecurityCommonService securityCommonService) {
        this.jwtInterceptor = jwtInterceptor;
        this.securityCommonService = securityCommonService;
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( HttpMethod.POST, "/user/login").permitAll()
                .antMatchers( HttpMethod.POST, "/user/register").permitAll()
                .antMatchers(HttpMethod.GET, "/category").permitAll()
                .antMatchers(HttpMethod.GET, "/city").permitAll()
                .antMatchers(HttpMethod.GET, "/rental/**").permitAll()
                .antMatchers(HttpMethod.GET, "/characteristic/**").permitAll()
                .and()
                .csrf()
                .disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/category").authenticated()
                .antMatchers(HttpMethod.DELETE,"/category").authenticated()
                .antMatchers(HttpMethod.PATCH,"/category").authenticated()
                .antMatchers(HttpMethod.POST, "/booking").authenticated()
                .antMatchers(HttpMethod.GET, "/booking/user").authenticated()
                .antMatchers(HttpMethod.GET, "/user/account").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtInterceptor, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityCommonService)
                .passwordEncoder(passwordEncoder());
    }
}

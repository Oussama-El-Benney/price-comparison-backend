package org.sid.pricecomparisonbackend.secrservice;

import org.sid.pricecomparisonbackend.secrservice.filters.JwtAuthenticationFilter;
import org.sid.pricecomparisonbackend.secrservice.filters.JwtAuthorizationFilter;
import org.sid.pricecomparisonbackend.secrservice.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsServiceImpl userDetailsService;

  public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().disable();

    http.authorizeRequests().antMatchers("/login/**", "/refreshToken/**").permitAll();
//    http.formLogin();
//    http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("USER");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
    http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

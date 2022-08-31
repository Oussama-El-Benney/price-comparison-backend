package org.sid.pricecomparisonbackend.secrservice.service;

import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {

  private AccountService accountService;

  public UserDetailsServiceImpl(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = accountService.loadUserByUsername(username);
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    appUser.getAppRoles().forEach(r -> {
      authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
    });
    return new User(appUser.getUsername(), appUser.getPassword(), authorities);
  }
}

package org.sid.pricecomparisonbackend.secrservice.service;

import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.repo.AppRoleRepository;
import org.sid.pricecomparisonbackend.secrservice.repo.AppUserRepository;

import java.util.List;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
  private AppUserRepository appUserRepository;
  private AppRoleRepository appRoleRepository;
  private PasswordEncoder passwordEncoder;

  public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
    this.appUserRepository = appUserRepository;
    this.appRoleRepository = appRoleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AppUser addNewUser(AppUser appUser) {
    String pw = appUser.getPassword();
    appUser.setPassword(passwordEncoder.encode(pw));
    return appUserRepository.save(appUser);
  }

  @Override
  public AppRole addNewRole(AppRole appRole) {

    return appRoleRepository.save(appRole);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
AppUser appUser = appUserRepository.findByUsername(username);
AppRole appRole =appRoleRepository.findByRoleName(roleName);
appUser.getAppRoles().add(appRole);
  }

  @Override
  public AppUser loadUserByUsername(String username) {

    return appUserRepository.findByUsername(username);
  }

  @Override
  public List<AppUser> listUsers() {

    return appUserRepository.findAll();
  }
}

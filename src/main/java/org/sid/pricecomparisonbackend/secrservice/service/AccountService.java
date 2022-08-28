package org.sid.pricecomparisonbackend.secrservice.service;

import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;

import java.util.List;

public interface AccountService {
  AppUser addNewUser(AppUser appUser);
  AppRole addNewRole(AppRole appRole);
  void addRoleToUser(String username, String roleName);
  AppUser loadUserByUsername(String username);
  List<AppUser> listUsers();
}

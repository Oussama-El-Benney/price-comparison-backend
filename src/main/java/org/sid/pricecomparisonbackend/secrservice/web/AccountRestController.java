package org.sid.pricecomparisonbackend.secrservice.web;

import lombok.Data;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
  private AccountService accountService;

  public AccountRestController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(path = "/users")
  public List<AppUser> appUsers() {
    return accountService.listUsers();
  }

  @PostMapping(path = "/users")
  public AppUser saveUser(@RequestBody AppUser appUser) {
    return accountService.addNewUser(appUser);
  }

  @PostMapping(path = "/roles")
  public AppRole saveRole(@RequestBody AppRole appRole) {
    return accountService.addNewRole(appRole);
  }

  @PostMapping(path = "/addRoleToUser")
  public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
    accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
  }
}

@Data
class RoleUserForm {
  private String username;
  private String roleName;

}

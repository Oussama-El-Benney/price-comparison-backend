package org.sid.pricecomparisonbackend.secrservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sid.pricecomparisonbackend.secrservice.JWTUtil;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
  private AccountService accountService;

  public AccountRestController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(path = "/users")
//  @PostAuthorize("hasAuthority('CLIENT')")
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

    @GetMapping(path = "/refreshToken")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String auhToken = request.getHeader(JWTUtil.AUTH_HEADER);
    if (auhToken != null && auhToken.startsWith(JWTUtil.PREFIX)) {
      try {
        String jwt = auhToken.substring(JWTUtil.PREFIX.length());
        Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        String username = decodedJWT.getSubject();
        AppUser appUser = accountService.loadUserByUsername(username);
        String jwtAccessToken = JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", appUser.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String, String> idToken = new HashMap<>();
        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwt);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
      } catch (Exception e) {
        response.setHeader("error-message", e.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
      }
    } else {
      throw new RuntimeException("Refresh Token Required!!!");
    }
  }

  @GetMapping(path = "/profile")
  public AppUser profile(Principal principal){
    return accountService.loadUserByUsername(principal.getName());
  }
}

@Data
class RoleUserForm {
  private String username;
  private String roleName;

}

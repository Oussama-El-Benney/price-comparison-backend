package org.sid.pricecomparisonbackend.secrservice.repo;


import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
  AppRole findByRoleName(String roleName);
}

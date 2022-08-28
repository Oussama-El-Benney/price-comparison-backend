package org.sid.pricecomparisonbackend.secrservice.repo;


import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
  AppUser findByUsername(String username);
}

package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Favorites;
import org.sid.pricecomparisonbackend.entities.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites,Long> {
}

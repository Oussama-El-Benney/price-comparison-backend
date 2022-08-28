package org.sid.pricecomparisonbackend.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@DiscriminatorValue("CLIENT")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Client extends Person{
  private int telNumber;
  private String adresse;


//
  @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
  private Favorites favorites;
}
